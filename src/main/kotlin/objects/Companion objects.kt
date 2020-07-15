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

package objects

// https://livebook.manning.com/#!/book/kotlin-in-action/chapter-4/300

import java.util.*

fun main(args: Array<String>) {
  // Note the following statement won't work:
  // val instance2 = XYZResource("123")
  val instance1 = XYZResource.create()
  println(instance1)

  // unnamed companion objects are all called "Companion"
  val instance2 = XYZResource.Companion.create()
  println(instance2)

  println(XYZResource.extfunc())
}

// Class has a private constructor, so it can't be created externally
data class XYZResource private constructor(var id: String) {

  // Equivalent of static methods / factory pattern
  companion object {
    fun create(): XYZResource {
      return XYZResource("nazmul" + Random().nextInt(100).toString())
    }
  }

}

// Extension function on companion object
fun XYZResource.Companion.extfunc(): String {
  return "extension function"
}