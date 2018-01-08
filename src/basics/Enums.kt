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

import utils.red

enum class Headers(val id: String){
    TYPE("Type"),
    DESC("Description"),
    XACT("Transaction Date"),
    POST("Post Date")
}

fun main(args: Array<String>){
    println("item \t item.toStr() \t item.id".red())
    for (item in Headers.values()){
        println("$item \t ${item.toString()} \t\t\t ${item.id}")
    }
}