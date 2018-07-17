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

package typesafety

import utils.red

// https://livebook.manning.com/#!/book/kotlin-in-action/chapter-6/138

fun main(args: Array<String>){
    println("-- unsafe".red())
    unsafeSendMessageTo("maret@fl.com")
    unsafeSendMessageTo(null)

    println("-- safe".red())
    sendMessageTo("n@fl.com")
    sendMessageTo(null)
}


// using ?.let
fun sendMessageTo(email: String?){
    email?.let{ sendEmailTo(it) }
}

fun sendEmailTo(email: String){
    println("sending email to $email")
}

// NOT using ?.let
fun unsafeSendMessageTo(email:String?){
    email.let{ unsafeSendEmailTo(it) }
}

fun unsafeSendEmailTo(email: String?){
    println(if (email.isNullOrBlank()) "empty" else email!!)
}
