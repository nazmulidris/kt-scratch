/*
 * Copyright 2019 Nazmul Idris. All rights reserved.
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

package dsl_easy

import utils.log
import utils.red

fun main() {
  "-- dsl_easy --".red().log()
  val p1 = person {
    name = "john doe"
    address {
      street = "Stewart Drive"
      number = 123
      city = "Mountain View"
    }
    age = 20
  }

  println(p1)
}

// Person related functions and classes.

data class Person(var name: String? = null,
                  var age: Int? = null,
                  var address: Address? = null
)

fun person(block: Person.() -> Unit): Person {
  val person = Person()
  person.block()
  return person
  // return Person().apply(block)
}

// Address related functions and classes.

data class Address(var street: String? = null,
                   var number: Int? = null,
                   var city: String? = null
)

fun Person.address(block: Address.() -> Unit) {
  address = Address().apply(block)
}
