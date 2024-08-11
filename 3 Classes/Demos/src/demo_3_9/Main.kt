package demo_3_9

// Demo 3.9

// Base class with a primary constructor
open class BaseClass(val name: String)

// Derived class with a primary constructor that initializes the base class
class DerivedClass(name: String, val age: Int) : BaseClass(name) {
    fun printInfo() {
        println("Name: $name, Age: $age")
    }
}

fun main() {
    val derived = DerivedClass("Alice", 25)
    derived.printInfo()  // Output: Name: Alice, Age: 25
}
