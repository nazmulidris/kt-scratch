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

fun main(args: Array<String>) {

  // Singleton
  Registry.add(Resource(123, "macbookpro"))
  Registry.add(Resource(477, "dell xps"))
  Registry.print()

  // Singleton used w/ collection
  val strings = listOf("abc", "DEF", "aaabbbb", "CCCCC")
  println(strings.sortedWith(IgnoreCaseStringComparator))

}

// Singleton (1 class and 1 object)
object Registry {

  val allResources = arrayListOf<Resource>()

  fun add(asset: Resource) {
    allResources.add(asset)
  }

  fun print() {
    for (asset in allResources) {
      println(asset)
    }
  }

}

data class Resource(val assetTag: Int, val description: String)

// Comparator singleton
object IgnoreCaseStringComparator : Comparator<String> {
  override fun compare(p0: String, p1: String): Int {
    if (p0.toLowerCase() == p1.toLowerCase()) return 0
    else return -1
  }
}