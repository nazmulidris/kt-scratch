<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->

- [Introduction](#introduction)
- [Non-capturing lambdas and their (implementation) instances](#non-capturing-lambdas-and-their-implementation-instances)
- [Capturing lambdas](#capturing-lambdas)
- [Problems w/ using non-capturing lambdas](#problems-w-using-non-capturing-lambdas)
- [Solution - use anonymous classes to implement the SAM / functional interface](#solution---use-anonymous-classes-to-implement-the-sam--functional-interface)
- [Beware IDE automatic conversions](#beware-ide-automatic-conversions)
- [Sample code demonstrating this](#sample-code-demonstrating-this)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

# Introduction

This project explores the issues that can arise when using lambdas that touch Java SAM (single abstract method)
interfaces. Here's an example of the issues that can arise.

- [The Java SAM](https://github.com/JetBrains/intellij-community/blob/master/platform/util/src/com/intellij/openapi/Disposable.java)
- [Fix](https://github.com/JetBrains/kotlin/pull/3556)
- [Problem](https://youtrack.jetbrains.com/issue/KT-32158#focus=Comments-27-4267010.0-0)
- [More insights into the issue (Java side)](https://blog.codefx.org/java/instances-non-capturing-lambdas/)
  - [How lambdas are created in Java](https://blog.codefx.org/java/dev/lambdas-java-peek-hood/)
- [More insights into the issue (Kotlin -> Java side)](https://medium.com/@krossovochkin/kotlin-java-interop-function-references-and-sam-conversions-3d0cd36f7967)

# Non-capturing lambdas and their (implementation) instances

When an expression does not capture any variables it's called a non-capturing lambda. Here's an example.

```java
public static Future<Integer> createWithDefaultResult() {
	ImmediateFuture<Integer> immediateFuture = () -> 0;
	return immediateFuture;
}
```

So the following code would print `1` and not `2` as you might expect:

```java
public static void main(String[] args) {
	Set<Future<?>> futures = new HashSet<>();
	futures.add(FutureFactory.createWithDefaultResult());
	futures.add(FutureFactory.createWithDefaultResult());
	System.out.println(futures.size());
}
```

The JVM reuses the implementation of these non-capturing lamdas, since there's no state change.

# Capturing lambdas

Contrast this with a lambda expression which captures some variables. A straight forward evaluation of such an
expression is to create a class which has the captured variables as fields. Each single evaluation must then create a
new instance which stores the captured variables in its fields. These instances are obviously not generally equal.
Here's an example.

```kotlin
public static Future<Integer> createWithResult(Integer result) {
	ImmediateFuture<Integer> immediateFuture = () -> result;
	return immediateFuture;
}
```

# Problems w/ using non-capturing lambdas

Non-capturing lambda instance reuse can cause issues when hooking up w/ these SAM / functional interfaces / lambdas from
Kotlin code (or Java code)! Here's an example of this issue:

```kotlin
Disposer.register(parentDisposable, Disposable {
    synchronized(APPLICATION_LOCK) {
        if (--ourProjectCount <= 0) {
            disposeApplicationEnvironment()
            }
        }
    }
})
```

The intent behind this code is to register a
[Disposable](https://github.com/JetBrains/intellij-community/blob/master/platform/util/src/com/intellij/openapi/Disposable.java)
with the IntelliJ platform so that it can be cleaned up. Each disposable registered needs to be a unique object, since
that's how the disposer mechanism works. However, due to the fact that this implementation is simply reused this won't
work.

# Solution - use anonymous classes to implement the SAM / functional interface

The solution is to simply replace the non-capturing lambda w/ a different expression (anonymous class implementing the
functional interface / SAM). The anonymous inner class guarantees the creation of new instances, while the non-capturing
lambda does not.

Here's the resolution of the issue by turning this lambda into an anonymous class (implementing `Disposable` interface):

```kotlin
Disposer.register(parentDisposable, object : Disposable {
    override fun dispose() {
        synchronized(APPLICATION_LOCK) {
            if (--ourProjectCount <= 0) {
                disposeApplicationEnvironment()
            }
        }
    }
})
```

Note the different semantics of the following:

- an anonymous class (`object: Disposable ...`) - guarantees the creation of new instances
- a (non-capturing) lambda expression (`Disposable {...}`) - does not guarantee the creation of new instances

# Beware IDE automatic conversions

This is especially unsettling because many IDEs allow the automatic conversion from anonymous interface implementations
to lambda expressions and vice versa. With the subtle differences between the two this seemingly purely syntactic
conversion can introduce subtle behavior changes.

# Sample code demonstrating this

To see this in action, check out the code in this [GitHub repo](https://github.com/nazmulidris/kt-scratch)

1. The Java code is in the Java module in the package: `java_interop_sam_gotchas`.
2. The Kotlin code is in the package: `java_interop_sam_gotchas_kt`.

Note: This Gradle project supports both Kotlin and Java. In order for this IDEA project to use both Kotlin and Java, I
had to create a new Gradle project in IDEA, and select both Kotlin and Java support for the same project, and then it
worked.
