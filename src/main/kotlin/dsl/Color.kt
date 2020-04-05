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
      add(it.Green("msg1"))
      add(Blue("msg2"))
    }.println()
    printLine {
      span(Green, "msg1")
      span(Red, "msg2")
      span(Blue, "msg3")
    }
  }
}

private fun String.println() = println(this)

fun console(block: ConsoleLogContext.() -> Unit) {
  ConsoleLogContext().apply(block)
}

class ConsoleLogContext {
  fun printLine(block: MutableList<String>.() -> Unit) {
    val sb = mutableListOf<String>()
    block(sb)
    println(sb)
  }

  fun line(block: MutableList<String>.(ConsoleLogContext) -> Unit): String {
    val sb = mutableListOf<String>()
    block(sb, this)
    return sb.toString()
  }

  /**
   * Appends all arguments to the given [MutableList].
   */
  fun MutableList<String>.span(color: Color, text: String): MutableList<String> {
    add(buildString {
      append(color)
      append(text)
    })
    return this
  }

  val Black = Color("\u001B[30")
  val Red = Color("\u001B[31")
  val Green = Color("\u001B[32")
  val Yellow = Color("\u001B[33")
  val Blue = Color("\u001B[34")
  val Purple = Color("\u001B[35")
  val Cyan = Color("\u001B[36")
  val White = Color("\u001B[37")
}

data class Color(val ansiColorCode: String) {
  private val RESET = "\u001B[0m";
  override fun toString(): String = ansiColorCode
  operator fun invoke(msg: String): String = "$ansiColorCode$msg$RESET"
}