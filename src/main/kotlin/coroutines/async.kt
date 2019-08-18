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

package coroutines_async

import com.importre.crayon.blue
import kotlinx.coroutines.*
import utils.red
import java.net.URL
import java.net.URLEncoder
import kotlin.reflect.KFunction

fun main() {
  println("-- asynchronous".red())
  coroutines_sync.runTimedBlock("runSynchronous") {
    runAsynchronous()
  }
}

fun runAsynchronous() {
  runBlocking {
    GlobalScope.launch(Dispatchers.IO) {
      val deferredList = mutableListOf<Deferred<Any>>()
      deferredList += async { execute(::api_debug, "boo") }
      deferredList += async { execute(::api_ipify) }
      deferredList += async { execute(::api_nominatim, "mountain view, ca") }
      deferredList.forEach { it.await() }
    }.join()
  }
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

suspend fun execute(apiFunction: KFunction<Any>, vararg args: Any?): Any =
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