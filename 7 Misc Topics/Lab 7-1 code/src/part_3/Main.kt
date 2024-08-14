package part_3

fun main() {
    // Immutable set of prime numbers
    val primeNumbers = setOf(2, 3, 5, 7, 11)

    // Mutable set with duplicates
    val randomNumbers = mutableSetOf(1, 2, 3, 2, 4, 5, 3)

    // Add and remove elements
    randomNumbers.add(6)
    randomNumbers.remove(1)

    // Check for membership
    println("Is 3 in the set? ${randomNumbers.contains(3)}")

    println("Prime numbers: $primeNumbers")
    println("Random numbers: $randomNumbers")
}
