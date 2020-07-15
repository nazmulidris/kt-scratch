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

package dsl

import dsl.ColorConsoleContext.Companion.colorConsole
import java.text.SimpleDateFormat
import java.util.*

fun main() {
  colorConsole {//this: ColorConsoleContext
    printLine {//this: MutableList<String>
      span(Colors.Purple, "word1")
      span("word2")
      span(Colors.Blue, "word3")
    }
    printLine {//this: MutableList<String>
      span(Colors.Green, "word1")
      span(Colors.Purple, "word2")
    }
    println(
        line {//this: MutableList<String>, it: ColorConsoleContext
          add(Colors.Green("word1"))
          add(Colors.Blue("word2"))
        })
  }
}

class ColorConsoleContext {
  companion object {
    fun colorConsole(block: ColorConsoleContext.() -> Unit) {
      ColorConsoleContext().apply(block)
    }
  }

  fun printLine(block: MutableList<String>.() -> Unit) {
    println(line {
      block(this)
    })
  }

  fun line(block: MutableList<String>.(ColorConsoleContext) -> Unit): String {
    val messageFragments = mutableListOf<String>()
    block(messageFragments, this)
    val timestamp = SimpleDateFormat("hh:mm:sa").format(Date())
    return messageFragments.joinToString(separator = ", ", prefix = "$timestamp: ")
  }

  /**
   * Appends all arguments to the given [MutableList].
   */
  fun MutableList<String>.span(color: Colors, text: String): MutableList<String> {
    add(color.ansiCode + text + Colors.ANSI_RESET.ansiCode)
    return this
  }

  /**
   * Appends all arguments to the given [MutableList].
   */
  fun MutableList<String>.span(text: String): MutableList<String> {
    add(text + Colors.ANSI_RESET.ansiCode)
    return this
  }
}

enum class Colors(val ansiCode: String) {
  ANSI_RESET("\u001B[0m"),
  Black("\u001B[30m"),
  Red("\u001B[31m"),
  Green("\u001B[32m"),
  Yellow("\u001B[33m"),
  Blue("\u001B[34m"),
  Purple("\u001B[35m"),
  Cyan("\u001B[36m"),
  White("\u001B[37m");

  operator fun invoke(content: String): String {
    return "${ansiCode}$content${ANSI_RESET.ansiCode}"
  }

  operator fun invoke(content: StringBuilder): StringBuilder {
    return StringBuilder("${ansiCode}$content${ANSI_RESET.ansiCode}")
  }
}