package part_2


class Student(first: String?, last: String?, var a: Int?, var g: String) {
    val firstName: String
    val lastName: String
    val age: Int?
    val grade : String

    init {
        this.firstName = if (first.isNullOrEmpty()) "FNU" else first
        this.lastName = if (last.isNullOrEmpty()) "LNU" else last
        this.age = if ( a != null && a!! > 0) a else null
        this.grade = if (g.uppercase() !in listOf("A","B","C","D","F")) "NA" else g.uppercase()
    }

    fun getFullName(): String {
        return "$firstName $lastName"
    }
}


fun main() {
    var student = Student("John", "Doe", 20, "A")
    println("Student: ${student.firstName} ${student.lastName}, Age: ${student.age}, Grade: ${student.grade}")
    student = Student("", "Doe", -1, "5")
    println("Student: ${student.firstName} ${student.lastName}, Age: ${student.age}, Grade: ${student.grade}")
    student = Student("", null, null, "a")
    println("Student: ${student.firstName} ${student.lastName}, Age: ${student.age}, Grade: ${student.grade}")

}