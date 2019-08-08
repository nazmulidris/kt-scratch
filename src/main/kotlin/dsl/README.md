<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->

- [Goal](#goal)
- [Learnings](#learnings)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

# Goal

Be able to create an internal DSL to create a simple address book, eg:

```kotlin
AddressBook {

  Person {
    name "John Doe"
    email "jd@dom.com"
    age 20
  }

  Person {
    name "Jane Doe"
    email "jad@dom.com"
    age 25
  }

}
```

# Learnings

- [ ] Lambdas w/ receivers: https://livebook.manning.com/book/kotlin-in-action/chapter-5/299
- [ ] Operator overloading
- [ ] DSL
