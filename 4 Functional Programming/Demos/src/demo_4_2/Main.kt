package demo_4_2


// Higher-order function that takes a function as a parameter
fun performOperation(a: Int, b: Int, operation: (Int, Int) -> Int): Int {
    return operation(a, b)
}

// Example functions to pass as arguments
fun add(x: Int, y: Int): Int = x + y
fun multiply(x: Int, y: Int): Int = x * y
fun doubled(x:Int) : Int = x * 2

fun main() {
    // Using performOperation with different operations
    val sum = performOperation(5, 3, ::add)
    val product = performOperation(5, 3, ::multiply)
    //val d = performOperation(4.::doubled)

    println("Sum: $sum")         // Output: Sum: 8
    println("Product: $product") // Output: Product: 15
}