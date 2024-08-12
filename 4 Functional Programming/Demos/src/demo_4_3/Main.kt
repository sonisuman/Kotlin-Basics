package demo_4_3


class Person(var name: String, var age: Int)

// Extension function that uses Person as the receiver type
fun Person.introduce() {
    println("Hello, my name is $name and I am $age years old.")
}


fun main() {
    val person = Person("Alice", 25)

    // Calling the function with 'Person' as a receiver type
    person.introduce()
}