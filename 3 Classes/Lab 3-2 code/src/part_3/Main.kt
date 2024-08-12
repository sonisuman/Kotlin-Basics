package part_3

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


interface Teacher {
    val subject: String

    fun teach() {
        println("I teach $subject.")
    }
}

class TA(firstName: String, lastName: String, age: Int, override val subject: String)
    : Person(firstName, lastName, age), Teacher {

    override fun introduce() {
        println("Hello, I'm $firstName $lastName, I'm $age years old, and I teach $subject.")
    }
}

fun Person.isAdult(): Boolean {
    return this.age >= 18
}



fun main() {
    val john = Person("John", "Doe", 19)
    val jane = Student("Jane", "Smith", 17, "S12345")
    val emily = TA("Emily", "Johnson", 35, "Mathematics")
    john.introduce()
    println("John is an adult is ${john.isAdult()}")
    jane.introduce()
    println("Jane is an adult is ${jane.isAdult()}")

}
