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

// https://livebook.manning.com/#!/book/kotlin-in-action/chapter-4/214

// In Kotlin == and .equals are the same!
fun main(args: Array<String>){

    var s1 = "one"
    var s2 = "two"
    var s3 = "two"

    println("s1.equals(s2) : ${s1.equals(s2)}")
    println("s2.equals(s3) : ${s2.equals(s3)}")
    println("s2 == s3 : ${s2 == s3}")
    println("s1 == s2 : ${s1 == s2}")

}