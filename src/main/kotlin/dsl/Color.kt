/*
 * Copyright 2020 Nazmul Idris. All rights reserved.
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

package dsl

fun main() {
  console {
    line {
      span(Green, "msg1")
      span(Red, "msg2")
      span(Blue, "msg3")
    }.println()
    line {
      span(Green, "msg1")
      span(Red, "msg2")
      span(Blue, "msg3")
    }.println()
  }
}

private fun String.println() = println(this)

fun console(block: ConsoleLogContext.() -> Unit) {
  ConsoleLogContext().apply(block)
}

class ConsoleLogContext {
  fun line(block: MutableList<String>.() -> Unit): String {
    val sb = mutableListOf<String>()
    sb.apply(block)
    return sb.toString()
  }

  /**
   * Appends all arguments to the given [MutableList].
   */
  fun MutableList<String>.span(color: String, text: String): MutableList<String> {
    add(buildString {
      append(color)
      append(text)
    })
    return this
  }

  val Reset = "\u001B[0m";
  val Black = "\u001B[30";
  val Red = "\u001B[31";
  val Green = "\u001B[32";
  val Yellow = "\u001B[33";
  val Blue = "\u001B[34";
  val Purple = "\u001B[35";
  val Cyan = "\u001B[36";
  val White = "\u001B[37";
}