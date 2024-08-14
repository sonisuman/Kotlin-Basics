package part_5

fun main() {
    val sequenceOfLists = sequenceOf(
        listOf(1, 2, 3),
        listOf(4, 5, 6),
        listOf(7, 8, 9)
    )

    val flatMappedSequence = sequenceOfLists.flatMap { it.asSequence() }

    println("Flattened sequence: ${flatMappedSequence.toList()}")
}
