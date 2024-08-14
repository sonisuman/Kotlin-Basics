package part_5

fun main() {
    // Immutable map of countries and capitals
    val countryCapitals = mapOf("USA" to "Washington, D.C.", "France" to "Paris", "Germany" to "Berlin")

    // Mutable map of students and grades
    val studentGrades = mutableMapOf("Alice" to "A", "Bob" to "B", "Charlie" to "C")

    // Add and update entries
    studentGrades["David"] = "B"
    studentGrades["Alice"] = "A+"

    // Print all students and grades
    studentGrades.forEach { (student, grade) ->
        println("$student: $grade")
    }
}
