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

package typesafety

import utils.red

// https://livebook.manning.com/#!/book/kotlin-in-action/chapter-6/132

fun main(args: Array<String>) {
  println(::processUserInput.name.red())
  println(processUserInput("hola"))
  println(processUserInput(""))
  println(processUserInput(" "))
  println(processUserInput(null))

  println(::processUserInput2.name.red())
  println(processUserInput2("nola"))
  println(processUserInput2(""))
  println(processUserInput2(" "))
  println(processUserInput2(null))
}

fun processUserInput(prompt: String?): String {
  // extension function on String? isNullOrBlank() ... no need for safe call
  if (prompt.isNullOrBlank()) return "empty" else return prompt
}

fun processUserInput2(prompt: String?): String {
  if (prompt.isEmpty()) return "empty" else return prompt!!
}

// My own version of the extension function String?.isNullOrBlank
// Note - In Java, this is always not-null, because it references the
// instance of a class you’re in. In Kotlin, that’s no longer the case:
// in an extension function for a nullable type, this can be null.
fun String?.isEmpty(): Boolean {
  if (this == null) return true
  else if (this.isBlank()) return true
  return false
}