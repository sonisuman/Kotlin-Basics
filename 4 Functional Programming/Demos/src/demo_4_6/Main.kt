package demo_4_6

fun main() {
    // Accumulator function as a closure
    val accumulator = createAccumulator()

    println(accumulator(5))  // Output: 5
    println(accumulator(10)) // Output: 15
    println(accumulator(3))  // Output: 18
}

fun createAccumulator(): (Int) -> Int {
    var sum = 0  // Free variable captured by the lambda

    return { value: Int ->
        sum += value
        sum  // The lambda returns the updated sum
    }
}
