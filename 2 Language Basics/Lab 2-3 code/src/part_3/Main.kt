package part_3

fun findNumberInMatrix(matrix: List<List<Int>>, target: Int) {
    outerLoop@  for (i in matrix.indices) {
         println("Checking row $i")
        for (j in matrix[i].indices) {
            println("Checking column $j")
            if (matrix[i][j] == target) {
                println("Found $target at row $i, column $j")
                break@outerLoop

            }
        }
    }
}

fun main() {
    val matrix = listOf(
        listOf(1, 2, 3),
        listOf(4, 5, 6),
        listOf(7, 8, 9)
    )

    findNumberInMatrix(matrix, 5)
   // Test with a number not in the matrix
}
