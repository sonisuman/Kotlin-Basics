package demo_4_5

fun main() {

    // Anonymous Function with No Parameters:
    val sayHello = fun() {
        println("Hello, World!")
    }
    sayHello()

    // Anonymous Function with Parameters:
    val multiply = fun(a: Int, b: Int): Int {
        return a * b
    }
    val result = multiply(3, 4)
    println(result)

    // Anonymous Function with Implicit Return Type:
    // The return type can be omitted if it can be inferred
    val add = fun(a: Int, b: Int) = a + b
    val sum = add(15, 70)
    println(sum)

   // Anonymous Function as a Function Parameter:

    fun performOperation(a: Int, b: Int, operation: (Int, Int) -> Int): Int {
        return operation(a, b)
    }
    val result1 = performOperation(8, 3, fun(x: Int, y: Int): Int {
        return x - y
    })
    println(result1)

}


