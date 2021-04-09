<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->

- [kotlinlearning](#kotlinlearning)
  - [Importing this project into Jetbrains IntelliJ IDEA](#importing-this-project-into-jetbrains-intellij-idea)
  - [Upgrading to the latest gradle wrapper](#upgrading-to-the-latest-gradle-wrapper)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

# kotlinlearning

This is a project with sample code that I created while I was learning Kotlin. They are based on the
samples from
[Kotlin in Action book](https://livebook.manning.com/#!/book/kotlin-in-action/chapter-2/121) and
from the
[try.kotlinlang.org](https://try.kotlinlang.org/#/Examples/Hello,%20world!/Simplest%20version/Simplest%20version.kt)
website.

## Importing this project into Jetbrains IntelliJ IDEA

- This project was created using Jetbrains Idea as a Gradle and Kotlin project
  ([more info](https://www.jetbrains.com/help/idea/getting-started-with-gradle.html)).
  - When you import this project into Idea as a Gradle project, make sure not to check "Offline
    work" (which if checked, won't allow the gradle dependencies to be downloaded).
  - As of Jun 24 2018, Java 10 doesn't work w/ this gradle distribution, so you can use Java 8.

## Upgrading to the latest gradle wrapper

The gradle wrapper can get out of date pretty quickly for this project, so its best to update it to
the latest release whenever you need to. This
[SO answer](https://stackoverflow.com/a/36385128/2085356) shows how to do this from the command
line. Here's the gist of it (just insert the `latest-gradle-version` prior to runing the following
command in your terminal.

```shell script
./gradlew wrapper --gradle-version <latest-gradle-version> --distribution-type all
```
