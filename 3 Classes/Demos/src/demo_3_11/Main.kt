package demo_3_11

// Demo 3.11

interface Named {
    val firstName: String
    val lastName: String
    val name: String
}

class Employee(f:String, l:String) : Named {
    // implementing 'name' is not required
    override val firstName: String = f
    override val lastName: String = l
    override val name: String get() = "$firstName $lastName"
}

fun main() {
    val bob = Employee("Bob", "Smith")
    println(bob.name)
}