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
import kotlin.math.absoluteValue

fun main(args: Array<String>) {

  var a: String = "notnullable"
  //a = null
  printNullableStr(a)

  var b: String? = "nullable"
  printNullableStr(b)
  b = null
  printNullableStr(b)

  processList()

  processList2()

  processList3()
}

fun printNullableStr(str: String?) {
  //print(::str.name)
  println(if (str == null) "empty string" else "string.length=${str.length}")
}

// http://beust.com/weblog/2015/10/30/exploring-the-kotlin-standard-library/
fun processList() {
  println("== ${::processList.name} ==".red())
  val listWithNulls: List<String?> = listOf("a", "b", null, "c", null, "d")
  println("length of list: ${listWithNulls.size}")
  for (item in listWithNulls) {
    item?.let { println(it) }
  }
}

// http://kotlinlang.org/docs/reference/null-safety.html
fun processList2() {
  println("== ${::processList2.name} ==".red())
  val listWithNulls: List<Int?> = listOf(1, 2, null, 3, 4, 5, null, null)
  println("length of list: ${listWithNulls.size}, list: $listWithNulls")
  for (item in listWithNulls) {
    // elvis operator
    val i = item?.absoluteValue ?: -1
    println(i)
  }
}

fun processList3() {
  println("== ${::processList3.name} ==".red())
  val listWithNulls: List<Int?> = listOf(1, 2, null, 3, 4, 5, null, null)
  println("length of list: ${listWithNulls.size}, list: $listWithNulls")
  listWithNulls.filterNotNull().forEach { println(it) }
}