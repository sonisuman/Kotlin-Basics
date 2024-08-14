package part_1

fun main() {
    // Create a sequence from a list
    val list = listOf(1, 2, 3, 4, 5)
    val sequenceFromList = list.asSequence()

    // Create a sequence using sequenceOf
    val sequenceOfNumbers = sequenceOf(6, 7, 8, 9, 10)

    // Create an empty sequence
    val emptySequence = emptySequence<Int>()

    println("Sequence from list: ${sequenceFromList.toList()}")
    println("Sequence of numbers: ${sequenceOfNumbers.toList()}")
    println("Empty sequence: ${emptySequence.toList()}")
}
