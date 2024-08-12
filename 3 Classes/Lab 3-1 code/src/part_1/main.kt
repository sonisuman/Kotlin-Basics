package part_1

class Student(val firstName: String, val lastName: String, var age: Int, var grade: String) {
    fun getFullName(): String {
        return "$firstName $lastName"
    }
}


fun main() {
    val student = Student("John", "Doe", 20, "A")

    println("Student: ${student.firstName} ${student.lastName}, Age: ${student.age}, Grade: ${student.grade}")

    println(student.getFullName())

}