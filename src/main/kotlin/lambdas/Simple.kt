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

// Lambdas & Higher order functions

fun main(args: Array<String>) {

    // Lambdas
    val f = {a: Int, b: Int -> a + b}
    println("f(1, 3) = ${f(1,3)}")

    val f2 = f
    println("f2(1, 3) = ${f2(1,3)}")

    val f4 = ::f3
    println("f4(1, 3) = ${f4(1,3)}")

    val f5 = {a: Int, b: Int -> a * b}(2,2)
    println("f5 = $f5")

    // Higher order functions
    log(f, 20, 20)
    log(::f3, 10, 10)

}

// Higher order function
fun log(fp1: (Int, Int)->Int, p1: Int, p2: Int) {
    println(fp1(p1,p2))
}

fun f3(p1: Int, p2: Int): Int {
    return p1 * p2
}