/*
 * Copyright 2018 Nazmul Idris All rights reserved.
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

package lambdas

import com.importre.crayon.blue

// Convert a value in miles to inches, feet, and yards using higher order functions

enum class ConvertTo { Inches, Feet, Yards }

/** Returns a function lambda of type `(Int) -> Int` */
fun pickFunction(value: ConvertTo): (Int) -> Int {
  return when (value) {
    ConvertTo.Inches -> ::toInches
    ConvertTo.Yards  -> ::toYards
    else             -> ::toFeet
  }
}

fun toInches(value: Int) = value * 63360

fun toYards(value: Int) = value * 1760

fun toFeet(value: Int) = value * 5280

fun main(args: Array<String>) {

  val miles = 100
  println("$miles miles".blue())

  for (key in ConvertTo.values()) {
    print("- ConvertTo.$key = ")
    println(pickFunction(key)(10))
  }

}