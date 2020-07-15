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

package properties

/**
 * Properties stored in a map. This comes up a lot in applications like parsing JSON
 * or doing other "dynamic" stuff. Delegates take values from this map (by the string keys -
 * names of properties). Of course, you can have var's as well,
 * that will modify the map upon assignment (note that you'd need MutableMap instead of read-only Map).
 */

// more info
// http://www.baeldung.com/kotlin-delegated-properties

class MyWeatherData(val map: MutableMap<String, Any>) {
  val zipcode: String by map
  val currenttemp: Int by map
  val forecast: List<Int> by map
  override fun toString(): String {
    return "${zipcode}, ${currenttemp}, ${forecast}"
  }
}

fun main(args: Array<String>) {
  val paloaltoweather = MyWeatherData(
      // make sure that the key names match the prop names of the class
      mutableMapOf(
          "zipcode" to "94301",
          "currenttemp" to 49,
          "forecast" to listOf(50, 40, 60)
      )
  )
  println(paloaltoweather)
  paloaltoweather.map.set("currenttemp", 70)
  println(paloaltoweather)

}