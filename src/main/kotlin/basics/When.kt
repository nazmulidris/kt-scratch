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

package basics

fun main(args: Array<String>) {

  val id = "nazmul"
  println(
      when (id) {
        "nazmul" -> "admin"
        else     -> "user"
      })

  val code = 11
  when (code) {
    in 1..10  -> println("small")
    in 11..20 -> println("medium")
    in 21..30 -> println("large")
  }

  // The following does not compile since the items in the when statement
  // have must be Int, or in a range, or is a specific type
  //when (code){
  //    isEven(code) -> println("even")
  //    else -> println("odd")
  //}

  when {
    isEvenNumber(code) -> println("is even")
    else               -> println("is odd")
  }

  val somevalue: Any = 12f
  when (somevalue) {
    is Int    -> print("somevalue is an Int")
    is String -> print("somevalue is a String")
    else      -> print("somevalue is not an Int or String")
  }

}

fun isEvenNumber(num: Int): Boolean {
  return num % 2 == 0
}