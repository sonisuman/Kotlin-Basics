package demo_4_4

fun main() {

    // Lambda with No Parameters:
    val greet = { println("Hello, World!") }
    greet()
    greet.invoke()

    // Lambda with Parameters:
    val sum = { a: Int, b: Int -> a + b }
    var result = sum(3, 4)
    println(result)

    // Lambda with Implicit it Parameter:
    // When a lambda has only one parameter,
    // you can omit the parameter declaration and use it as the implicit name for the parameter.
    val square = { it: Int -> it * it }
    result = square(5)
    println(result)

    val uc: (Int) -> Int = { it * it }
    println(uc(100))

    // Using Lambdas with Standard Library Functions:
    val numbers = listOf(1, 2, 3, 4, 5)
    val doubled = numbers.map { it * 2 }
    println(doubled)

    // Lambda as Function Parameter:
    fun operateOnNumbers(a: Int, b: Int, operation: (Int, Int) -> Int): Int {
        return operation(a, b)
    }

    val sumResult = operateOnNumbers(10, 5) { x, y -> x + y }
    val multiplyResult = operateOnNumbers(10, 5) { x, y -> x * y }

    println("Sum: $sumResult")          // Output: Sum: 15
    println("Product: $multiplyResult") // Output: Product: 50


}