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

package utils

// http://www.lihaoyi.com/post/BuildyourownCommandLinewithANSIescapecodes.html

fun String.green(): String = "\u001b[32m$this\u001b[0m"
fun String.red(): String = "\u001b[31m$this\u001b[0m"
fun String.yellow(): String = "\u001b[33m$this\u001b[0m"
fun String.log(): Unit = println(this)