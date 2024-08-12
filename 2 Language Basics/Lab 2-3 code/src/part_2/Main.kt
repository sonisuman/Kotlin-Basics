package  part_2

/* fun getGradeDescription(grade: Char): String {
    return when (grade) {
        'A' -> "Excellent"
        'B' -> "Good"
        'C' -> "Average"
        'D' -> "Below Average"
        'F' -> "Fail"
        else -> "Incomplete"
    }
}*/
fun getGradeDescription(grade: Char): String {
    return when {
        grade == 'A' -> "Excellent"
        grade == 'B' -> "Good"
        grade == 'C' -> "Average"
        grade == 'D' -> "Below Average"
        grade == 'F' -> "Fail"
        else -> "Invalid grade"
    }
}
fun main() {
    println("Grade A: ${getGradeDescription('A')}")
    println("Grade C: ${getGradeDescription('C')}")
    println("Grade E: ${getGradeDescription('E')}")
}