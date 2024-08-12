package part_1

fun performOperation(x: Int, y: Int, operation: (Int, Int) -> Int): Int {
    return operation(x, y)
}

fun main() {
    val sum = performOperation(10, 5) { a, b -> a + b }
    val difference = performOperation(10, 5) { a, b -> a - b }
    val product = performOperation(10, 5) { a, b -> a * b }
    val quotient = performOperation(10, 5) { a, b -> a / b }

    println("Sum: $sum")
    println("Difference: $difference")
    println("Product: $product")
    println("Quotient: $quotient")
}
