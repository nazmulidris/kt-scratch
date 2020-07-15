/*
 * Copyright 2020 Nazmul Idris All rights reserved.
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

package dsl_hard

import utils.log
import utils.red
import java.text.SimpleDateFormat
import java.util.*

fun main() {
  "-- dsl_hard --".red().log()
  val p1 = person {
    name = "john doe"
    dateOfBirth = "01/02/2010"
    address {
      street = "Some Drive"
      number = 123
      city = "Sunnyvale"
    }
    address {
      street = "Another Avenue"
      number = 1600
      city = "Mountain View"
    }
  }

  val a1 = address {
    street = "Some Drive"
    number = 123
    city = "Mountain View"
  }

  println(p1)
  println(a1)
}

// Person related functions and classes.

data class Person(val name: String,
                  val dateOfBirth: Date,
                  val addresses: List<Address>
)

class PersonBuilder {
  // name property.
  var name = ""

  // dateOfBirth property (w/ backing field).
  private var _dateOfBirth: Date = Date()
  var dateOfBirth: String
    get() = _dateOfBirth.toString()
    set(value) {
      _dateOfBirth = SimpleDateFormat("MM/dd/yyyy").parse(value)
    }

  // address function (for addresses property).
  var _addresses = mutableListOf<Address>()

  fun address(block: AddressBuilder.() -> Unit) {
    _addresses.add(AddressBuilder().apply(block).build())
  }

  // Build immutable Person object.
  fun build() = Person(name, _dateOfBirth, _addresses)
}

fun person(block: PersonBuilder.() -> Unit): Person {
  return PersonBuilder().apply(block).build()
}

// Address related functions and classes.

data class Address(val street: String,
                   val number: Int,
                   val city: String
)

class AddressBuilder {
  var street = ""
  var number = 0
  var city = ""
  fun build() = Address(street, number, city)
}

fun address(block: AddressBuilder.() -> Unit) =
    AddressBuilder().apply(block).build()