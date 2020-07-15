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

package coroutines_sync

import com.importre.crayon.blue
import com.importre.crayon.green
import com.importre.crayon.red
import java.net.URL
import java.net.URLEncoder
import kotlin.reflect.KFunction

fun main() {
  println("-- synchronous".red())
  runTimedBlock("runSynchronous") {
    runSynchronous()
  }
}

fun runSynchronous() {
  val r1 = execute(::api_debug, "boo")
  val r2 = execute(::api_ipify)
  val r3 = execute(::api_nominatim, "mountain view, ca")
  println("$r1, $r2, $r3".green())
}

// API function implementations.

fun api_debug(vararg args: Any?): String = args.joinToString()

fun api_ipify(vararg args: Any?): String {
  val url = "https://api.ipify.org?format=json"
  return URL(url).readText()
}

fun api_nominatim(vararg args: Any?): String {
  val query = URLEncoder.encode(args[0] as String, "UTF-8")
  val url =
      "https://nominatim.openstreetmap.org/search" +
      "?q=${query}" +
      "&format=geojson&addressdetails=1" +
      "&limit=1"
  return URL(url).readText()
}

// Driver that takes an API function and executes it w/ metering.

fun execute(apiFunction: KFunction<Any>, vararg args: Any?): Any =
    runTimedBlock("execute-function") {
      println("execute using apiFunction: ${apiFunction.name}")
      println("execute on thread: ${Thread.currentThread()}")
      val response: Any = apiFunction.call(args)
      println("response = ${response}")
      response
    }

// Utils.

fun runTimedBlock(name: String, block: () -> Any): Any {
  val startTime = System.currentTimeMillis()
  try {
    return block()
  }
  finally {
    val endTime = System.currentTimeMillis()
    println("$name block took ${endTime - startTime} ms to run".blue())
  }
}