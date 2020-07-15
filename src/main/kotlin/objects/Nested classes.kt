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

// https://livebook.manning.com/#!/book/kotlin-in-action/chapter-4/86

fun main(args: Array<String>) {
  val clickable: Clickable = Button("btn_1")
  clickable.click()
  clickable.somethingCool()
}

interface Clickable {
  val res_id: String
  fun click()
  fun somethingCool() {
    println("$res_id: I am clickable")
  }
}

class Button(override val res_id: String) : Clickable {
  override fun click() {
    println("Button was clicked!")
  }

  class ButtonState : State {
    override fun saveState() {
      // note the following line won't work ... nested classes in KT
      // don't have access to their outer class members
      // println("saving $res_id")
      println("saving state")
    }

    override fun restoreState() {
      println("restoring state")
    }

  }
}

interface State {
  fun saveState()
  fun restoreState()
}