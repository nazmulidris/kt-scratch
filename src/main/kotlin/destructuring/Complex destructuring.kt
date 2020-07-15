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

package destructuring

import utils.green
import utils.red
import utils.yellow

fun main(args: Array<String>) {
  simple_destructuring()
  complex_destructuring()
}

// Complex - using a Map

fun complex_destructuring() {

  println(("Complex").red())

  val p1_map: HashMap<String, String> = hashMapOf(
      "name" to "nazmul", "age" to "43", "email" to "n@fl.com")

  val p2_map = hashMapOf(
      "name" to "maret", "age" to "28", "email" to "m@fl.com")

  val people = listOf(p1_map, p2_map)

  people.forEach {
    for ((key, value) in it) {
      print(" ${key.yellow()} ${value.green()} ")
    }
    println()
  }

}

// Simple - using a List of Person objects

fun simple_destructuring() {

  println(("Simple").red())

  val p1 = Person("nazmul", 43)
  val p2 = Person("maret", 28)
  val people = listOf(p1, p2)

  for (person in people) {
    val (name, age) = person
    println("name=$name, age=$age")
  }

  for ((name, age) in people) {
    println("name=$name, age=$age")
  }

  val (result, status) = people.getPerson("maret")
  println("status: $status, person: $result")

  val (first, second) = people.getPerson(43)
  println("status: $first, person: $second")

}

data class Person(val name: String, val age: Int)
enum class ResultCode { OK, ERR }
data class Result(val result: Person?, val status: ResultCode)

private fun List<Person>.getPerson(name: String): Result {
  val result = this.find { it.name == name }
  return Result(
      result,
      if (result == null) ResultCode.ERR else ResultCode.OK
  )
}

private fun List<Person>.getPerson(age: Int): Pair<ResultCode, Person?> {
  val result = this.find { it.age == age }
  return Pair(
      first = if (result == null) ResultCode.ERR else ResultCode.OK,
      second = result
  )
}