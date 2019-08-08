/*
 * Copyright 2017 Nazmul Idris. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package properties

import utils.red

/**
 * Delegates.lazy() is a function that returns a delegate that implements a lazy property:
 * the first call to get() executes the lambda expression passed to lazy() as an argument
 * and remembers the result, subsequent calls to get() simply return the remembered result.
 * If you want thread safety, use blockingLazy() instead: it guarantees that the values will
 * be computed only in one thread, and that all threads will see the same value.
 */

data class Person2(val name: String, val age: Int)

// more info
// http://www.baeldung.com/kotlin-delegated-properties

// complex lazy property delegate
class PersonSample(name: String, age: Int) {
  val objRef: Person2 by lazy {
    println("lazy computed")
    Person2(name, age)
  }
}

// simple lazy property delegate
class StringSample {
  val objRef: String by lazy {
    println("lazy computed")
    "SomeString"
  }
}

fun main(args: Array<String>) {
  println("-- PersonSample obj created".red())

  val sample = PersonSample("naz", 43)
  println("lazy = ${sample.objRef}")
  println("lazy = ${sample.objRef}")

  println("-- StringSample obj created".red())
  val sample2 = StringSample()
  println("lazy = ${sample2.objRef}")
  println("lazy = ${sample2.objRef}")
}