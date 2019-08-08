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

package destructuring

/**
 *  Kotlin Standard Library provide component functions for Map.Entry
 */

fun main(args: Array<String>) {
//     val map = hashMapOf<String, Int>()
//     map.put("one", 1)
//     map.put("two", 2)

  val map = mapOf("one" to 1,
                  "two" to 2)

  for ((key, value) in map) {
    println("$key = $value")
  }
}