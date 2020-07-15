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
 * The observable() function takes two arguments: initial value and a handler for modifications.
 * The handler gets called every time we assign to `name`, it has three parameters:
 * a property being assigned to, the old value and the new one. If you want to be able to veto
 * the assignment, use vetoable() instead of observable().
 */
import utils.green
import utils.red
import kotlin.properties.Delegates

// more info
// http://www.baeldung.com/kotlin-delegated-properties

data class Person(val name: String, val age: Int)

class Username {
  // observable delegate that is a more complicated object
  var user: Person by Delegates.observable(Person("null",
                                                  -1)) { prop, old, new ->
    println("user value changed " +
            "\n\t${old.toString().red()} -> \n\t${new.toString().green()}")
  }
  // observable delegate that is a simple string
  var id: String by Delegates.observable("null") { prop, old, new ->
    println("id value changed ${old.red()} -> ${new.green()}")
  }
}

class Name {
  var user: String by Delegates.observable("null") { prop, old, new ->
    println("user value changed ${old.red()} -> ${new.green()}")
  }
}

fun main(args: Array<String>) {
  val obj1 = Name()
  obj1.user = "Carl"

  val obj2 = Username()
  obj2.user = Person("naz", 43)
  obj2.id = "1234"
}