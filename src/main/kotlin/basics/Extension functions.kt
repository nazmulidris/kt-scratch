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

fun main(args: Array<String>) {
  staticStuff()
  example1()
}

fun example1() {
  fun MutableList<Int>.swap(idx1: Int, idx2: Int) {
    val tmp = this[idx1]
    this[idx1] = this[idx2]
    this[idx2] = tmp
  }

  val list = mutableListOf<Int>(1, 2, 3)
  println("list ... $list")
  list.swap(0, list.size - 1)
  println("swap() ... $list")

  fun MutableList<Int>.switch() {
    swap(0, size - 1)
  }
  list.switch()
  println("switch() ... $list")

  fun MutableList<Int>.prettyPrint() {
    for (index in this.indices) {
      println("item[$index]=${this[index]}")
    }
  }
  list.prettyPrint()

}

fun staticStuff() {
  open class C

  class D : C()

  fun C.foo() = "c"

  fun D.foo() = "d"

  fun printFoo(c: C) {
    println(c.foo())
  }

  printFoo(D())

}