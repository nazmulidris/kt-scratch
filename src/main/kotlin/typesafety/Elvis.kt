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

package typesafety

import utils.red

fun main(args: Array<String>) {
  sample1()
  sample2() // note this throws an exception
}

// Elvis and type
data class User(val name: String, val age: Int) {
  fun isEqual(other: Any?): Boolean {
    val otherPerson: User = other as? User ?: return false
    return age == otherPerson.age && name.equals(otherPerson.name)
  }
}

fun sample1() {
  println("sample1".red())
  val u1 = User("naz", 43)
  val u2 = User("maret", 28)
  val num = 1
  println("$u1 is equal to $u2 : ${u1.isEqual(u2)}")
  println("$u1 is equal to $num : ${u1.isEqual(num)}")
}

// Elvis
fun sample2() {
  println("sample2".red())
  val p1 = Person("Jack",
                  Company("Google", Address("Googleplex", "MV", 94040, "US")))
  printShippingLabel(p1)
  val p2 = Person("Jill", null)
  printShippingLabel(p2)
}

class Address(
    val street: String,
    val city: String,
    val zipcode: Int,
    val country: String
)

class Company(val name: String, val address: Address?)
class Person(val name: String, val company: Company?)

fun printShippingLabel(person: Person) {
  val address = person.company?.address
                ?: throw IllegalArgumentException("No address found")
  with(address) {
    println("$street\n$city, $zipcode, $country")
  }
}