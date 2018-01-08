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

package objects

// https://livebook.manning.com/#!/book/kotlin-in-action/chapter-4/330

import utils.red
import java.util.*

fun main(args: Array<String>) {
    eg1()
    eg2()
}

fun eg1() {
    println("example1".red())

    fun <T> ArrayList<T>.prettyPrint() {
        println("array = ${this.joinToString(prefix = "{ ", postfix = " }", separator = " | ")}")
    }

    val array: ArrayList<Int> = arrayListOf(5, 43, 58858, 2, 3)
    array.prettyPrint()

    // SAM conversion for Java class Comparator
    // val comparator = Comparator<Int>{x, y -> x-y }

    // This is the object expression version of the SAM eg above
    val comparator = object : Comparator<Int> {
        override fun compare(x: Int, y: Int): Int {
            return x - y
        }
    }
    // lambda version of object expression above
    // val comparator = Comparator<Int> { x, y -> x - y }
    Collections.sort(array, comparator)
    array.prettyPrint()
    array.sortDescending()
    array.prettyPrint()

    val obj = object {
        var x = 0
        var y = 0
        fun sum() = x + y
    }
    obj.x = 10
    obj.y = 50
    println("obj -> x=${obj.x} y=${obj.y} sum=${obj.sum()}")
}

fun eg2() {
    println("example2".red())

    val exec = Exec()

    //
    // SAM aka Single Abstract Method only works for Java interfaces.
    //
    val r: Runnable = Runnable { println("run") }
    exec(r)

    // inline example of above
    exec(Runnable { println("run2") })

    //
    // For Kotlin interfaces, this is how you do it.
    //
    val g = object : MyRunnable {
        override fun run() {
            println("run3")
        }
    }
    exec(g)

    // inline example of above
    exec(object : MyRunnable {
        override fun run() {
            println("run4")
        }
    })
}

class Exec {
    operator fun invoke(r: Runnable) {
        r.run()
    }

    operator fun invoke(r: MyRunnable) {
        r.run()
    }
}

interface MyRunnable {
    fun run()
}