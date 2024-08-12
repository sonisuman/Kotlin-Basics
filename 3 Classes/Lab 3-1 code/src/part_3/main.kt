class Student(first: String?, last: String?, var a: Int?, var g: String) {
    var firstName: String
        private set
    var lastName: String
        private set
    var age: Int?
        private set
    var grade: String =this.validateGrade(g)
        set(value) {
            field = this.validateGrade(value)
        }

    constructor(firstName: String, lastName: String) : this(firstName, lastName, null, "NA")

    private fun validateGrade(g:String): String {
       return  if (g.uppercase() !in listOf("A","B","C","D","F")) "NA" else g.uppercase()
    }

    init {
        this.firstName = if (first.isNullOrEmpty()) "FNU" else first
        this.lastName = if (last.isNullOrEmpty()) "LNU" else last
        this.age = if (a != null && a!! > 0) a else null
    }

    fun getFullName(): String {
        return "$firstName $lastName"
    }
}


fun main() {
    var student = Student("John", "Doe", 20, "A")
    println("Student: ${student.firstName} ${student.lastName}, Age: ${student.age}, Grade: ${student.grade}")
    student.grade = "B"
    println("Student: ${student.firstName} ${student.lastName}, Age: ${student.age}, Grade: ${student.grade}")
    student = Student("Jane", "Doe")
    println("Student: ${student.firstName} ${student.lastName}, Age: ${student.age}, Grade: ${student.grade}")

}