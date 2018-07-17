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
 * This example introduces a concept that we call destructuring declarations.
 * It creates multiple variable at once. Anything can be on the right-hand
 * side of a destructuring declaration, as long as the required number of component
 * functions can be called on it.
 * See http://kotlinlang.org/docs/reference/multi-declarations.html#multi-declarations
 */

fun main(args: Array<String>) {
    val pair = Pair(1, "one")

    val (num, name) = pair

    println("num = $num, name = $name")
    println("c1 = ${pair.component1()}, c2 = ${pair.component2()}")
}

// The Pair class is implemented like so:
// class Pair<K, V>(val first: K, val second: V) {
//     operator fun component1(): K {
//         return first
//     }

//     operator fun component2(): V {
//         return second
//     }
// }