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

/**
 * "Callable References" or "Feature Literals", i.e. an ability to pass
 * named functions or properties as values. Users often ask
 * "I have a foo() function, how do I pass it as an argument?".
 * The answer is: "you prefix it with a `::`".
 */

fun main(args: Array<String>) {
  val numbers = listOf(1, 2, 3)
  println(numbers.filter(::isOdd))

  val names = listOf("maret", "nazmul", "dexter")
  println(names.filter(::matchesMyName))
}

fun isOdd(x: Int) = x % 2 != 0

fun matchesMyName(x: String): Boolean = x.equals("nazmul")