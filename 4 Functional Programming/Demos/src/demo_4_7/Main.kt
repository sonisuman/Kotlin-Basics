package demo_4_7

inline fun performOperation(a: Int, b: Int, operation: (Int, Int) -> Int): Int {
    return operation(a, b)
}

fun main() {
    val sum = performOperation(3, 4) { x, y -> x + y }
    val product = performOperation(3, 4) { x, y -> x * y }

    println("Sum: $sum")       // Output: Sum: 7
    println("Product: $product") // Output: Product: 12
}
