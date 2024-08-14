package part_6

fun alternatingSequence(): Sequence<Int> = sequence {
    while (true) {
        yield(1)
        yield(2)
    }
}

fun main() {
    val sequence = alternatingSequence().take(10)
    println("Alternating sequence: ${sequence.toList()}")
}
