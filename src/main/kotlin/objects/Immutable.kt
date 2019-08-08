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

package objects

import utils.red

// https://livebook.manning.com/#!/book/kotlin-in-action/chapter-4/239
// It is preferred to use immutable data classes in Kotlin, and make
// copies in order to modify

fun main(args: Array<String>) {
  println("-- person entry objs".red())
  var p1 = PersonEntry("nazmul", 40, "n@fl.com")
  // the following won't work ... immutable
  // p1.age = 43
  var p2 = p1.copy(age = 43)
  println(p1)
  println(p2)

  println("-- address book".red())
  var addressBook = AddressBook()
  addressBook.list.add(p1)
  addressBook.list.add(p2)
  addressBook.prettyPrint()
}

data class AddressBook(var list: MutableList<PersonEntry> = mutableListOf()) {
  fun prettyPrint() {
    for (item in list) {
      println(item)
    }
  }
}

data class PersonEntry(val name: String, val age: Int, val email: String?) {
  fun hasEmail(): Boolean {
    return !email.isNullOrEmpty()
  }
}