package part_2

fun main() {
    // Create a list and a sequence
    val numbersList = (1..100).toList()
    val numbersSequence = (1..100).asSequence()

    // Filter and map the list
    val filteredList = numbersList.filter { it % 2 == 0 }.map { it * it }
    println("Filtered and mapped list: $filteredList")

    // Filter and map the sequence
    val filteredSequence = numbersSequence.filter { it % 2 == 0 }.map { it * it }
    println("Filtered and mapped sequence: ${filteredSequence.toList()}")
}
