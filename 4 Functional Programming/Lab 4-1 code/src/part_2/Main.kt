package part_2

fun performOperation(x: Int, y: Int, operation: (Int, Int) -> Int): Int {
    return operation(x, y)
}
fun getOperation(operation: String): (Int, Int) -> Int {
    return when (operation.lowercase()) {
        "add" -> { a, b -> a + b }
        "subtract" -> { a, b -> a - b }
        "multiply" -> { a, b -> a * b }
        "divide" -> { a, b -> a / b }
        else -> throw IllegalArgumentException("Unknown operation")
    }
}

fun main() {
    val addOperation = getOperation("add")
    val multiplyOperation = getOperation("multiply")

    val sum = performOperation(10, 5, addOperation)
    val product = performOperation(10, 5, multiplyOperation)
    val difference = performOperation(10, 5, getOperation("subtract"))
    val quotient = performOperation(10, 5, getOperation("divide"))

    println("Sum using getOperation: $sum")
    println("Product using getOperation: $product")
    println("Difference using getOperation: $difference")
    println("Quotient using getOperation: $quotient")
}
