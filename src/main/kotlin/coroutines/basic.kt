/*
 * Copyright 2019 Nazmul Idris. All rights reserved.
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

package coroutines

import utils.red
import java.net.URL
import java.net.URLEncoder
import kotlin.reflect.KFunction

fun main() {
  println("-- coroutines basic".red())
  runSynchronous()
}

fun runSynchronous() {
  execute(::api_debug, "boo")
  execute(::api_ipify)
  execute(::api_nominatim, "mountain view, ca")
}

fun runAsynchronous() {

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

fun execute(apiFunction: KFunction<Any>, vararg args: Any?): Any {
  val startTime = System.currentTimeMillis()
  println("execute using apiFunction: ${apiFunction.name}")
  println("execute on thread: ${Thread.currentThread()}")
  try {
    val response: Any = apiFunction.call(args)
    println("response = ${response}")
    return response
  }
  finally {
    val endTime = System.currentTimeMillis()
    val timeTaken = (endTime - startTime)
    println("execute is done (with either error or success) in " +
            "${timeTaken} ms")
  }
}