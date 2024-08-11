package demo_3_6


// Demo 3.6

class Person(var myName:String, var age:Int ) {
     init {
         println("Executing primary constructor block")
         if (age < 0) this.age = 0
     }

    constructor(name: String) : this(name,0) {
        println("Executing one argument constructor")
    }

    constructor() : this("Unknown") {
        println("Executing no argument constructor")
    }
}

fun main() {
    val bob = Person("Robert", 32)
    println("------ bob's name is ${bob.myName} my age is ${bob.age}")
    val ted = Person("Theodore")
    println("------ ted's name is ${ted.myName} my age is ${ted.age}")
    val anon = Person()
    println("------ anon's name is ${anon.myName} my age is ${anon.age}")

}