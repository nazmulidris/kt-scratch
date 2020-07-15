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

package basics

fun main(args: Array<String>) {
  var pt = Point(1, 1)
  pt = -pt
  pt.prettyPrint()
  pt++
  pt.prettyPrint()
  pt + 12
  pt.prettyPrint()
  println("in range 0 ... 1000 ${pt in PointRange(0, 1000)}")

  val (x, y) = pt
  println("x=$x, y=$y")
}

data class Point(var x: Int, var y: Int) {
  operator fun unaryMinus(): Point {
    return Point(-x, -y)
  }

  operator fun inc(): Point {
    x += 1
    y += 1
    return this
  }

  operator fun plus(i: Int) {
    x += i
    y += i
  }

  fun prettyPrint() {
    println(this)
  }
}

class PointRange(val start: Int, val endInclusive: Int) {
  operator fun contains(pt: Point): Boolean {
    val sum = pt.x + pt.y
    return sum in (start + 1)..(endInclusive - 1)
  }
}