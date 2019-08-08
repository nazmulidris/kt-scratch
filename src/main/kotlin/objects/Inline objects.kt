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

// https://livebook.manning.com/#!/book/kotlin-in-action/chapter-4/262

fun main(args: Array<String>) {

  // Creating a class and object at the same time (aka singleton)
  val obj = object {
    val x = 1
    val y = 2
    fun total(): Int {
      return x + y
    }

    fun introspect() {
      when (total()) {
        1      -> println("one")
        is Int -> println("is an Int")
        else   -> println("any num other than one")
      }
    }
  }

  println("obj.total=${obj.total()}")
  obj.introspect()

}