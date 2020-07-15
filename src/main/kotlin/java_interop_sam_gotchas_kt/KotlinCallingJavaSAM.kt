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

@file:JvmName("KotlinCallingJavaSAM")

package java_interop_sam_gotchas_kt

import assertk.assertThat
import assertk.assertions.hasSize
import dsl.Colors
import java_interop_sam_gotchas.Callback
import java_interop_sam_gotchas.Log
import java_interop_sam_gotchas.ThirdParty

fun main() {

  try {
    FaultyClass().codeThatLeaks()
  }
  catch (e: Throwable) {
    println(Colors.Red(e.message!!))
  }

  try {
    GoodClass().codeThatWorks()
  }
  catch (e: Throwable) {
    println(Colors.Red(e.message!!))
  }

}

class FaultyClass {
  /**
   * 1. The leak happens because our created callback is a `(Int) -> Unit` function and is not instance of
   * [ThirdParty.Callback].
   * 2. So after passing the function reference `callback` to a SAM, a different instance of [ThirdParty.Callback] is
   * created every time!
   */
  fun codeThatLeaks() {
    val callback = ::onValueChanged
    Log.d(ThirdParty.TAG, "Kotlin callback created: $callback")

    val thirdParty = ThirdParty()

    with(thirdParty) {
      printState()
      addCallback(callback)
      printState()
      removeCallback(callback)
      printState()
    }

    assertThat(thirdParty.callbacks, "thirdParty.callbacks").hasSize(0)
  }

  fun onValueChanged(value: Int) {
    Log.d("KOTLIN", "onValueChanged called w/ $value")
  }
}

class GoodClass {
  fun codeThatWorks() {
    // This is a capturing lambda, meaning that it does not have a single identity. Every time, this executes, a **new**
    // instance is created.
    val callback = object : Callback {
      override fun onValueChanged(value: Int) = this@GoodClass.onValueChanged(value)
    }

    // The following also works, even though this is a non-capturing lambda, which means that it has a single identity.
    // Every time, this executes, the **same** instance is re-used.
    //val callback = Callback { value -> this@GoodClass.onValueChanged(value) }

    // Also works:
    //val callback = Callback(::onValueChanged)

    Log.d(ThirdParty.TAG, "Kotlin callback created: $callback")

    val thirdParty = ThirdParty()

    with(thirdParty) {
      printState()
      addCallback(callback)
      printState()
      removeCallback(callback)
      printState()
    }

    assertThat(thirdParty.callbacks, "thirdParty.callbacks").hasSize(0)
  }

  fun onValueChanged(value: Int) {
    Log.d("KOTLIN", "onValueChanged called w/ $value")
  }
}