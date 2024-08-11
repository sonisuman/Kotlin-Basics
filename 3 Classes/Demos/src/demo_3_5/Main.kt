package demo_3_5

// Demo 3.5

class Person(val myName:String = "Unknown")

fun main() {
    val bob = Person("Robert")
    val ted = Person()

    println("bob's name is ${bob.myName}")
    println("ted's name is ${ted.myName}")
}