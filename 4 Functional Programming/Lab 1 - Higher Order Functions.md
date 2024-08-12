# Lab 4-1  Higher Order Functions
<!--suppress CheckImageSize -->

## Lab Setup

Create a new Kotlin project like you did for the last lab. You will only need to work in a single file `Main.kt`

Alternatively, you can use the last project and just do each part in a new package

---

## Part 1 - Defining the function

A higher order function is one that can take other functions as parameters or return functions as results. 

Write a function named `performOperation` that takes two integers and a function as parameters. The function parameter should define an operation that takes two integers and returns an integer.

```kotlin
fun performOperation(x: Int, y: Int, operation: (Int, Int) -> Int): Int {
    return operation(x, y)
}

```
In the main() function, call performOperation() with different operations such as addition, subtraction, multiplication, and division. Use lambda expressions to define these operations.

Note the use of the training lambda notation

```kotlin
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
```

```shell
Sum: 15
Difference: 5
Product: 50
Quotient: 2
```
---

## Part 2  - Returning a function from a higher order function 

Write a function named `getOperation` that returns a function based on a string parameter. The string can be "add", "subtract", "multiply", or "divide". The returned function should perform the corresponding operation using a `when` block. The return value should be a function literal or lambda

If an operation specified is not allowed, throw an illegal argument exception

```kotlin
fun getOperation(operation: String): (Int, Int) -> Int {
    return when (operation.lowercase()) {
        "add" -> { a, b -> a + b }
        "subtract" -> { a, b -> a - b }
        "multiply" -> { a, b -> a * b }
        "divide" -> { a, b -> a / b }
        else -> throw IllegalArgumentException("Unknown operation")
    }
}
```
Now use both operations you have defined together.

```kotlin
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
```
```shell
Sum using getOperation: 15
Product using getOperation: 50
Difference using getOperation: 5
Quotient using getOperation: 2
```

---

## End Lab