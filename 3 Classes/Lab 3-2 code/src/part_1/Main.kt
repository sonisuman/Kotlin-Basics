package part_1

open class Person(val firstName: String, val lastName: String, val age: Int) {

    open fun introduce() {
        println("Hi, I'm $firstName $lastName, and I'm $age years old.")
    }
}

class Student(firstName: String, lastName: String, age: Int, val studentId: String)
    : Person(firstName, lastName, age) {

    override fun introduce() {
        println("Hi, I'm $firstName $lastName, I'm $age years old, and my student ID is $studentId.")
    }
}

fun main() {
    val person = Person("John", "Doe", 45)
    val student = Student("Jane", "Smith", 20, "S12345")

    person.introduce()
    student.introduce()
}
