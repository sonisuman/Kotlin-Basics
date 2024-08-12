package demo_4_1

fun calculateFactorial(number: Int): Int {
    // Local function to calculate factorial
    // and a local free variable
    var freeVar = "Hi there"
    fun factorial(n: Int): Int {
        if (n <=1 ) println(freeVar)
        return if (n <= 1) 1 else n * factorial(n - 1)
    }

    // Call the local function
    factorial(number)
    freeVar = "Bye Now"
    return factorial(number)
}

fun main() {
    val result = calculateFactorial(5)
    println("Factorial of 5 is: $result")  // Output: Factorial of 5 is: 120

}