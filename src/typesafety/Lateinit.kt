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

import utils.green
import java.util.*

// More about latelinit
// https://livebook.manning.com/#!/book/kotlin-in-action/chapter-6/120

// Kotlin try is an expression that can return values
// https://kotlinlang.org/docs/reference/exceptions.html

fun main(args: Array<String>) {
    var app = Application()
    with(app) {
        println(try {getServiceInfo()} catch (e: Throwable) {e})
        onCreate()
        onStart()
        onStop()
        onDestroy()
        println(getServiceInfo())
    }
}

class Application {
    val id: Int = (100..110).random()
    lateinit var mService: MyService // var not initialized

    // this throws an exception if the mService var hasn't been initialized yet
    fun getServiceInfo(): String {
        return mService.toString()
    }

    fun onCreate() {
        mService = MyService(id, this) // var initialized here
    }

    fun onStart() {
        println(mService.doTheThing())
    }

    fun onStop() {

    }

    fun onDestroy() {

    }

}

class MyService(val app_id: Int, val context: Application) {
    fun doTheThing(): String = "[app_id:$app_id]: done!"
    override fun toString(): String {
        return "$app_id.MyService".green()
    }
}

// utility function that generates a random number, from a range of Int
fun ClosedRange<Int>.random() = Random().nextInt(endInclusive - start) + start