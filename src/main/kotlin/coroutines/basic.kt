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

fun runSynchronous() {
  makeNetworkRequest(::api_debug, "boo")
  makeNetworkRequest(::api_ipify)
  makeNetworkRequest(::api_nominatim, "mountain view, ca")
}

fun makeNetworkRequest(api: KFunction<Any>, vararg args: Any?): Any {
  println("makeNetworkRequest using api: ${api.name}")
  val startTime = System.currentTimeMillis()
  println("makeNetworkRequest on thread: ${Thread.currentThread()}")
  try {
    val response: Any = api.call(args)
    println("response = ${response}")
    return response
  }
  finally {
    val endTime = System.currentTimeMillis()
    val timeTaken = (endTime - startTime)
    println("makeNetworkRequest is done (with either error or success) in " +
            "${timeTaken} ms")
  }
}

fun runAsynchronous() {

}