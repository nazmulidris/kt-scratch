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

package basics

import utils.red

fun main(args: Array<String>) {

    println("-- Classes".red())
    var a1 = Animal(species = Animal.Species.Cat, name = "Kitty", weight = 10)
    a1.also(::println)
    a1.also { println(it) }

    println("-- Data Classes".red())
    DataExample().demo()

    println("-- Phone checkout".red())
    checkout()
}

// Normal class

class Animal(val species: Species, val name: String, val weight: Int) {
    init {
        println("Animal initialized w/ name = $name")
    }

    val prop1 = "Name = $name"
    val prop2 = "Length = ${name.length}"

    override fun toString(): String {
        val list = listOf(species, name, weight, prop1, prop2)
        return list.joinToString(prefix = "{", postfix = "}")
    }

    enum class Species {
        Cat, Squirrel, Monkey
    }

}

// Data class, 1st example

class DataExample {

    enum class Gender { Male, Female, Unknown }
    data class User(val name: String, val age: Int, val email: String, val sex: Gender);

    fun demo() {
        var d1 = User(name = "John Wick", age = 50, email = "jw@gmail.com", sex = Gender.Male)
        println(d1)
        var d2 = User(name = "Luke Skywalker", age = 50, email = "luke@gmail.com", sex = Gender.Male)
        var d3 = d2.copy(name = "Leia Skywalker", email = "isuck@gmail.com", sex = Gender.Unknown)
        println(d2)
        println(d3)
    }

}

// Data class, 2nd example

data class Invoice(val listOfItems: List<Item>)
data class Item(val sku: String, val price: Float, val quantity: Int)

fun checkout(){

    val phone1 = Item("Pixel2XL",500f,1)
    val case1 = Item("Saddleback",100f,1)
    val headphone1 = Item("EarBuds",50f,1)

    val invoice1 = Invoice(listOf(phone1, case1, headphone1))

    val total = sumOfItems(invoice1)

    print(total)

}

fun sumOfItems(invoice: Invoice): Float{
    var total = 0f
    for (item in invoice.listOfItems){
        total += item.price * item.quantity
    }
    return total
}