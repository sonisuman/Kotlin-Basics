package part_4

fun main() {
    // Create an infinite sequence
    val infiniteSequence = generateSequence(1) { it + 1 }

    // Filter and take first 10 numbers divisible by 3
    val firstTenDivisibleBy3 = infiniteSequence
        .filter { it % 3 == 0 }
        .take(10)
        .toList()

    println("First 10 numbers divisible by 3: $firstTenDivisibleBy3")
}
