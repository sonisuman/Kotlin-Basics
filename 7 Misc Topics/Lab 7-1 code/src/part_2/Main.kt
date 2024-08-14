package part_2

fun main() {
    val randomNumbers = listOf(5, 12, 7, 8, 20, 15, 3, 2)

    // Filter even numbers
    val evenNumbers = randomNumbers.filter { it % 2 == 0 }

    // Square each number
    val squaredNumbers = evenNumbers.map { it * it }

    // Sum all elements
    val sumOfSquares = squaredNumbers.fold(0) { sum, number -> sum + number }

    println("Original list: $randomNumbers")
    println("Filtered (even) list: $evenNumbers")
    println("Squared list: $squaredNumbers")
    println("Sum of squares: $sumOfSquares")
}
