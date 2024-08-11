# 4. Functional Kotlin Programming

<!--suppress CheckImageSize -->
<img src="../images/Logo.png" width="300" alt="">

## Function Basics

[Kotlin Documentation](https://kotlinlang.org/docs/functions.html)

We have already seen the basics of using functions in Kotlin

## Function Scope

Since functions are first class citizens, they scope similar to variables. In Kotlin, member functions, or functions defined as methods are in Java, work in the same way as Java methods.

### Local Functions

Local functions can be defined in other functions analogously to local variables. Any variables defined in the scope in which the function is defined can be reference as free variables.

This is shown in Demo 4.1

```kotlin
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
```
```shell
Hi there
Bye Now
Factorial of 5 is: 120
```

## Higher-order functions and lambdas

[Kotlin Documentation](https://kotlinlang.org/docs/lambdas.html)

Since functions are first class, they are like data in that they can be stored in variables, passed as parameters to other functions and be returned from other functions.

Functions that take other functions as parameters or return a function are called `higher-order functions` As shown below in Demo 4.2

```kotlin
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

```
```shell
Sum: 8
Product: 15
```
The `::add` notation in Kotlin is known as a function reference that allows a function to be passed as an argument to another function without actually invoking it. If the function was referenced directly, then the function would be executed and the result passed instead of the reference to the function.

## Function types

[Kotlin Documentation](https://kotlinlang.org/docs/lambdas.html#function-types)

Like variables, functions are type in terms of the type and number of arguments they take and the type of their return value.

### Receiver types

This allows functions to be "added on" to a class definition as if they were defined as class methods. This was referenced in the previous module under extensions

This is shown in demo 4.3

```kotlin
package demo_4_3

class Person(var name: String, var age: Int)

// Extension function that uses Person as the receiver type
fun Person.introduce() {
    println("Hello, my name is $name and I am $age years old.")
}


fun main() {
    val person = Person("Alice", 25)

    // Calling the function with 'Person' as a receiver type
    person.introduce()
}
```
```shell
Hello, my name is Alice and I am 25 years old.
```

### Anonymous functions

An anonymous function  is defined without a name and is typically used when a function needs to be passed as an argument, returned from another function, or assigned to a variable. Unlike lambdas, anonymous functions allow the return type to be specified explicitly and can include multiple statements with control structures such as return, break, or continue.

```kotlin 

```