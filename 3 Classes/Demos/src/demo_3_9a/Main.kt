package demo_3_9a

// Demo 3.9

// Base class with two constructors
open class BaseClass {
    val name: String

    constructor(name: String) {
        this.name = name
        println("Base class constructor with name: $name")
    }

    constructor(name: String, age: Int) {
        this.name = "$name (Age: $age)"
        println("Base class constructor with name and age: $name, $age")
    }
}

// Derived class with two secondary constructors
class DerivedClass : BaseClass {

    // First secondary constructor: initializes base class directly
    constructor(name: String) : super(name) {
        println("Derived class constructor with name: $name")
    }

    // Second secondary constructor: delegates to another constructor
    constructor(name: String, age: Int) : this(name) {
        println("Derived class constructor with name and age: $name, $age")
    }
}

fun main() {
    val derived1 = DerivedClass("Bob")
    println("-----------")
    val derived2 = DerivedClass("Charlie", 30)
}
