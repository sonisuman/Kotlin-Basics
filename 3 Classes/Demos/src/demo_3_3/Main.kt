package demo_3_3

class Person(val name: String)

class Person2 constructor(var name: String) {
    val counter = 1
    init {
        if (name == "") {
            name = "Unknown"
        }
        println("This is the first init block")
    }

    init {
        println("This is the second init block")
    }
}

fun main() {
    val bob = Person("Robert")
    val ted = Person2("Theodore")
    val igor = Person2("")

    println("bob's name is ${bob.name}")
    println("ted's name is ${ted.name}")
    println("igor's name is ${igor.name}")
}