<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->

- [Goal](#goal)
- [Learnings](#learnings)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

# Goal

Be able to create an internal DSL to create a simple address book, eg:

```kotlin
AddressBook {

  name "Personal"

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

- [x] [Lambdas w/ receivers](https://tinyurl.com/y4el4y3a)
- [ ] Operator overloading
- [ ] DSL

  - [x] [Tutorial Part 1](https://tinyurl.com/y2eddjfd)
  - [x] [Tutorial Part 2](https://tinyurl.com/y3h8m97n)
  - [x] [Socket example](https://tinyurl.com/y69k28rh)

- Online repl examples:
  - [High order function, lambda ex](https://pl.kotl.in/1fP6ePqVL)
  - [Simple DSL ex](https://pl.kotl.in/-lC_hc_Ar)
