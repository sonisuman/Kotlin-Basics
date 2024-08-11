# Lab 2-1  Data Types
<!--suppress CheckImageSize -->


## Lab Setup

Create a new Kotlin project like you did for the last lab. You will only need to work in a single file `Main.kt` or you can create a new package for each section.


## Part 1 Basic Types

In this first part, you will experiment with some basic types and smart casting


Create a `Main.kt` file with a `main()` method. If you want, you can use different packages for the different parts of this lab, like in the demos.

#### Create an extension function

Recall that all the types we will be working with are subclasses of `Any`. In this first step, create an extension function for any that prints out the type of a variable.

We did this in one of the demos. The code you need is:

```kotlin
fun Any.getClassName() :String?  {
    return(this::class.simpleName)
}

```
Can you explain why the return value is `String?` and not `String`

#### Basic types

Remember that a basic type is a wrapper class for a Java primitive type. But they inherit from `Any`. Even literals are actually object so we can apply the above function to all of them.

Test this out by running the following code in the `main()` function

```kotlin
    println("This is a string".getClassName())
    println(true.getClassName())
    println(34.7.getClassName())
    println(null?.getClassName())
```
Try it with a couple of other types. Remove the `?` in the last line and explain why you got a compile error when you did.

Now declare an empty class called 'Cat' or whatever else you want and add a line to the above code to test out what it prints out. Remember, you need to create an object of that type first.

```kotlin

class Cat


fun main() {

    println("This is a string".getClassName())
    println(true.getClassName())
    println(34.7.getClassName())
    println(null?.getClassName())
    println(Cat().getClassName())
}
```

Add the following two lines into the above code and run it

```kotlin
    println(Any().getClassName())
    println(Unit.getClassName())
```
As expected, we had to create an `Any` object to get the type, but we didn't with `Unit`, because it is a singleton. Only one object of type `Unit` exists so we can't create a new one.

## Part 2 - Smart Casting

When a variable has an assigned type, we cannot assign a new value of a different type to the variable.

However, with a variable of type `Any` we can assign any type to it since it is the root class.

You can delete your work from the previous section and use the same file, or start again with a new package and a new file `Main.kt`

Add the extension function to the file as in the last section.

```kotlin
fun Any.getClassName() :String?  {
    return(this::class.simpleName)
}
```

Add the `main()` function:

```kotlin
fun main() {

    var anything: Any
    anything = "Hi there"
    println("$anything has type ${anything.getClassName()}")
    println("${anything.uppercase()}")

    anything = 34.7
    println("$anything has type ${anything.getClassName()}")
    //println("${anything.uppercase()}")


    var x = "Hi There"
    println("$x has type ${x.getClassName()}")

    // this is an error
    //x = 45
}
```

Things to note.  When the first assignment to `anything` was made, the compiler noticed that it now contained a `String` object. So for the subsequent uses of the variable, string operation were allowed.

When the second assignment was made, the compiler noticed that `anything` now contained a `Double` and did not allow any subsequent operations that were not allowed for `Double`

The last part of the code shows that smart casting does not take place between different basic types, which is due to how basic types are implemented.

## Part 3 - Type Conversion 

Basic types are not cast, they are converted. There is only one implicit conversion allowed, `Int` literals can be assigned to `Long` since they are also `Long` values.

No other implicit conversions are allowed. Even `Float` to `Double` is not allowed. Notice, that this is stricter than Java.

To see this, create a new file `Main.kt` in a new package or reuse an old one.

Enter the following code and experiment. Remember that it is the literal that has the type which is used to infer the type of the variable

```kotlin
fun main() {

    //val intVal:Int = 43L;
    //val floatVal:Float = 34.4
    //val doubleVal:Double = 43.0F
    //val stringVal:String = 'A'
    //val charVal:Char = "A"
    val longVal: Long = 43;
    val intVal: Int = 43
    val doubleVal: Double = 43.0
    val floatVal: Float = 34.4F
    val charVal: Char = 'A'
    val stringVal: String = "A"
}
```
To see how to use the conversion functions delete the above code and add the following code:

```kotlin
 fun main() {
    val intNumber: Int = 42
    val longNumber: Long = intNumber.toLong()  // Int to Long
    val doubleNumber: Double = intNumber.toDouble()  // Int to Double
    val floatNumber: Float = doubleNumber.toFloat()  // Double to Float

    val stringNumber: String = intNumber.toString()  // Int to String
    val intFromString: Int = stringNumber.toInt()  // String to Int

    val charValue: Char = 'A'
    val intFromChar: Int = charValue.code  // Char to Int
    val charFromInt: Char = intFromChar.toChar()  // Int to Char

    println("Long: $longNumber")
    println("Double: $doubleNumber")
    println("Float: $floatNumber")
    println("String: $stringNumber")
    println("Int from String: $intFromString")
    println("Int from Char: $intFromChar")
    println("Char from Int: $charFromInt")
}

```

## End Lab