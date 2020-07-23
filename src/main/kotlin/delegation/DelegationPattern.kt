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

package delegation

import utils.log
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

/*
 * Implementation Delegation (class and interface):
 * - [Official docs](https://kotlinlang.org/docs/reference/delegation.html)
 * - [Great tutorial](https://www.baeldung.com/kotlin-delegation-pattern)
 *
 * Property Delegation:
 * - [Official docs](https://kotlinlang.org/docs/reference/delegated-properties.html)
 * - [Great tutorial](https://www.baeldung.com/kotlin-delegated-properties)
 *
 * Summary of both:
 * - [Tutorial](https://proandroiddev.com/delegation-in-kotlin-e1efb849641)
 */

interface ProducerIF {
  fun produce(): String
}

class ProducerImpl : ProducerIF {
  override fun produce(): String = "ProducerImpl"
}

class EnhancedProducer(private val delegateObject: ProducerIF) : ProducerIF by delegateObject {
  override fun produce(): String = "${delegateObject.produce()} and EnhancedProducer"
}

class EnhancedProducer2 : ProducerIF by ProducerImpl() {
  override fun produce(): String = "EnhancedProducer2"
}

class EnhancedProducer3(private val delegateObject: ProducerIF) : ProducerIF by delegateObject {
  private val lock = ReentrantLock()
  override fun produce(): String {
    lock.withLock {
      return "${delegateObject.produce()} and EnhancedProducer3"
    }
  }
}

fun main() {
  ProducerImpl().produce().log()
  EnhancedProducer(ProducerImpl()).produce().log()
  EnhancedProducer2().produce().log()
  EnhancedProducer3(ProducerImpl()).produce().log()
}