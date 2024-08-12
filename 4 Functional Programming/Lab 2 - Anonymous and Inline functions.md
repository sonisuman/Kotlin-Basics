# Lab 4-2   Anonymous and inline functions
<!--suppress CheckImageSize -->

## Lab Setup

Create a new Kotlin project like you did for the last lab. You will only need to work in a single file `Main.kt`

Alternatively, you can use the last project and just do each part in a new package

---

## Part 1 - Anonymous Functions

Create an anonymous function to calculate the square of a number.

```kotlin
val square = fun(x: Int): Int { return x * x }

fun main() {
    println("Square of 4 is: ${square(4)}")
}
```
```shell
Square of 4 is: 16
```

Use the anonymous function within a higher-order function that calculates the sum of squares of two numbers.

```kotlin
fun sumOfSquares(x: Int, y: Int, operation: (Int) -> Int): Int {
    return operation(x) + operation(y)
}

fun main() {
    val result = sumOfSquares(3, 4, fun(x: Int): Int { return x * x })
    println("Sum of squares: $result")
}
```
```shell
Sum of squares: 25
```

---

## Part 2 - Closure

A closure in Kotlin is a function that captures variables from its surrounding scope. These variables are called free variables because they are not defined within the function itself but are used within it. The closure "closes over" these free variables, meaning that it remembers the values of these variables even when the function is executed outside of their original scope.

Write a function makeIncrementer that returns an anonymous function. The function should increment its argument by a specified value, which is captured from the function’s scope.

```kotlin

```
---

## End Lab