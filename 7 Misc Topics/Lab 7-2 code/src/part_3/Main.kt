package part_3

fun main() {
    val sequence = (1..10).asSequence()
        .filter {
            println("Filtering: $it")
            it % 2 == 0
        }
        .map {
            println("Mapping: $it")
            it * it
        }

    println("Result: ${sequence.toList()}")
}
