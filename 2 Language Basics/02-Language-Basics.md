# 2. Kotlin Language Basics

<!--suppress CheckImageSize -->
<img src="../images/Logo.png" width="300" alt="">

## Overview

This section is an overview of the features that are unique to Kotlin or different from the Java way of doing things.

The discussion of data types and control structures is intended to be very high-level. Rather than spend a lot of class time doing basic exercises in class, many of the lab exercises are provided for self-study.

This section leans heavily on the official documentation and is intended to provide more of a conceptual background to the topics and examples of these concepts in practice.

## Packages and Imports

[Kotlin Documentation](https://kotlinlang.org/docs/packages.html)

As seen in the lab in the previous section, packages and imports work the same in Kotlin as they do in Java with several minor additions. Since Kotlin and Java are intended to be interoperable in the same project, to introduce a new form of packaging mechanism in Kotlin would make interoperability problematic.

The two innovations in Kotlin are:

- Any top level construct, including functions and variables that are not declared `private` can be imported.

- If there are two identical symbols imported from different packages, instead of having to use the fully qualified name for one, we can define an alias instead.

This is demonstrated in demo 2–1.

## Variable Syntax and Semantics

[Kotlin Documentation](https://kotlinlang.org/docs/basic-syntax.html#variables)

In addition to `val` and `var`, Kotlin also has a `const` modifier for `val` in order to define true constants. The primary conceptual difference is that when a value is declared with `val`, it is immutable _after_ assignment has taken place during runtime. However, the value of a `const` must be known and initialized at compile time before any runtime processing occurs.

- `const` can only be used at the top level or within objects, while `val` can be used in any context. For example, `const` modifiers cannot be used in a function body.
- `const` values are initialized at compile time: `val` values are initialized at runtime.
- `const` can only be used with basic types and strings: `val` can be used with any type.

Unlike Java, variables do not have their type explicitly declared. Instead, a variable's type is inferred from the type of the data assigned to the variable when it is declared.

Once the type of a variable is determined, it cannot be used.

In a variable's type cannot be inferred, then it must be explicit in the definition.

This is shown in Demo 2.2

```kotlin
package demo_2_2

fun main() {

    // Type inferencing
    var int1 = 33
    var long1 = 3L
    // The following is a compile time error
    // var s
    var s : String

    // The following are errors
    // int1 = "A string"
    // int1 = 3.4
    // s = 45
    int1 = 45
    long1 = 45  // This is also a valid long
    s = "This is a string"

    println("The type of int1 is ${int1::class.qualifiedName}")
    println("The type of long1 is ${long1::class.qualifiedName}")
    println("The type of s is ${s::class.qualifiedName}")

}
```

```shell
The type of int1 is kotlin.Int
The type of long1 is kotlin.Long
The type of s is kotlin.String
```

It is important to keep a clear distinction between immutable variables and immutable data.
- An immutable variable can be thought of as pointing to memory location. Assigning a new value has the effect of making the variable point to a different location in memory, this is not allowed.
- An immutable variable can point to a mutable array. Since the array is mutable, the contents of the array can be modified even though the variable referencing it is immutable

This is shown in Demo 2.3

```kotlin
package demo_2_3

fun main() {


    val mutableList = mutableListOf(1, 2, 3)  // A mutable list
    val immutableList = listOf(10, 20, 30)  // An immutable list

    // Both of the variables are immutable so this is an error
    // mutableList = mutableListOf(0, 0, 0)

    // Modifying the list
    mutableList.add(4)  // Adds 4 to the list
    mutableList[0] = 10  // Updates the first element to 10
    mutableList.removeAt(1)  // Removes the element at index 1
    println(mutableList)

    // But this won't work
    // immutableList.add(4)
    // immutableList[0] = 10
}
```
```shell
[10, 3, 4]
```
---

## Data Types

[Kotlin Documentation](https://kotlinlang.org/docs/basic-types.html)

One of the primary differences between Java and Kotlin is in how the primitive data types are handled. Java divides data into two different categories. The primitive or builtin data types like `int` or `float`, and then the reference data types. These two categories have different syntax and are generally cannot be mixed except by enclosing the primitive types in wrapper classes like `Integer`.

The Kotlin uses the term `basic` types to refer to what Java calls primitive types. Basic types are primitive types at runtime with all the efficiencies that primitive types provide. However, at the source code level, they user reference syntax, or in other words, basic types are objects syntactically.

This is shown in demo 2–4

```kotlin 
package demo_2_4

const val DAY = "THURSDAY"

fun main() {
    val s = 10.79.toString()
    println(DAY.lowercase())
    println("length of s ${s.length}")
    println("Compare 19.89 to 35 = ${19.89.compareTo(45)}")
}
```
```shell
thursday
length of s is 5
Compare 19.89 to 35 = -1
```

### Unsigned Numeric Types

[Kotlin Documentation](https://kotlinlang.org/docs/unsigned-integer-types.html)

There is a type of class in Kotlin called an `in-line` class that is analogous to the wrapper classes like `Integer` in Java.

The Kotlin basic types map directly to the corresponding JVM primitive types for efficiency. However, inline classes can be used to define new types, which is what Kotlin has done to add unsigned integer values.

We will briefly discuss inline classes later in the course.

### String, Chars and Booleans

The operation of the types `Char`, `Boolean` and `String` work pretty much the same as in Java.

One notable exception is that strings in Kotlin can use the array notation to read elements, but Kotlin strings are immutable just like Java strings.

```kotlin
package demo_2_3

fun main() {
    val s = "This is a string"
    println("The third character of s is ${s[2]}")

    // This is a compile error
    // s[3] = '1'
}
```

```shell
The third character of s is i
```
---

## Special Data Types

#### Any and Any?

[Any Documentation](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/)

The `Any` and `Any?`types are analogous to the `Object` type in Java.

- `Any` is the root of the Kotlin class hierarchy. Every Kotlin class implicitly inherits from `Any`, making it the common supertype for all non-nullable types.
- `Any`, like `Object` in Java, provides fundamental methods like `toString()`, `hashCode()`, and `equals()`.
- A variable of type `Any` is automatically cast to the appropriate type on assignment
- `Any` is non-nullable, which means it cannot take the value `null` The corresponding nullable value `Any?` can take `null` as a value
- The `Any` class does not have analogues to the `Object` methods `clone()`, `wait()`, `notify()`, and `notifyAll()`.

```kotlin
package demo_2_5

fun main() {
    // Define an 'Any variable' and an 'Any?' variable
    var anyValue: Any
    val nullableAny: Any?

    // Assign it an Integer value and smart cast to Integer
    anyValue = 10
    println("The type of anyValue is: ${anyValue::class} with value $anyValue")

    // Assign a string value and smart cast to
    anyValue = "Hi there"
    println("The type of anyValue is: ${anyValue::class} with value $anyValue")

    //anyValue = null;  // This is a compile error
    nullableAny = null
    println("The value of nullableAny is: $nullableAny")

    // This is not allowed since the class method cannot be called on a potentially null object
   // println("The type of anyValue is: ${nullableAny::class} with value nullableAny")
}
```
```shell 
The type of anyValue is: class java.lang.Integer (Kotlin reflection is not available) with value 10
The type of anyValue is: class java.lang.String (Kotlin reflection is not available) with value Hi there
The value of nullableAny is: null
```
Extension methods (covered later) can be added directly to the `Any` class as shown in the following demo.

```kotlin
package demo_2_6

fun Any.printClassName() {
    println(this::class.simpleName)
}

fun main() {
    // Define an 'Any variable' and an 'Any?' variable
    var anyValue: Any


    // Assign it an Integer value and smart vast to Integer
    anyValue = 10
    anyValue.printClassName()

    // Assign a string value and smart cast to
    anyValue = "Hi there"
    anyValue.printClassName()

}
```
```shell
Int
String
```

#### Nothing

_From the Kotlin documentation_

"`Nothing` has no instances. You can use `Nothing` to represent "a value that never exists": for example, if a function has the return type of `Nothing`, it means that it never returns (always throws an exception)."

```kotlin
package demo_2_7

fun main() {
    val rv = test(5)
    println("Result: $rv")
}

fun test(value: Int): Int {
    return if (value > 0) {
        value
    } else {
        // This branch returns Nothing
        throwError("Value must be greater than zero")
    }
}

// Nothing is used her to document that nothing is returned ever from this function
fun throwError(message: String): Nothing {
    throw IllegalArgumentException(message)
}

```

#### Unit

In Kotlin, `Unit` is the equivalent of `void` in languages like Java or C and signifies that a function does not return any meaningful value. However, unlike `void`, `Unit` is an actual type and can be used as a return type for functions.

The values `Unit`, `Nothing` and `null` are very similar conceptually. The following contrasts their differences:

**Unit:**

- Represents a function that returns no meaningful value. It is similar to `void` in other languages but is defined type whereas `void` generally means no type.
- `Unit` is used as a return type for functions that execute without returning any meaningful result, like `println()` for example
- `Unit` is a singleton meaning that there is only one instance of the `Unit` type

**Nothing:**

- `Nothing` is used to signify that a function never returns normally, often because the function either throws an exception or enters an infinite loop.
- `Nothing`used in situations where a function is _guaranteed_ to not return
- `Nothing` can be used any place a type modifier is expected, but essentially indicates that the code will not proceed past that point in the normal flow of control. In otherworldly, it will not return to the place where it was called from and that code after the place the function is called from is unreachable.

**Null:**

- As in other languages, this is a placeholder value that represents the absence of a value usually  used to indicate that a variable does not currently hold any value or that an object reference does not point to anything.
- Unlike Java, Kotlin supports writing non-nullable code, which we will look at later.

---

## Nullability

[Kotlin Documentation](https://kotlinlang.org/docs/null-safety.html)

- In Kotlin, a distinction is made between references that can be null and those that cannot.
- All variables are by default non-nullable, meaning they cannot hold a null.
- Nullable forms of each variable are indicated with a `?` postfix.

```kotlin
package demo_2_8

fun main() {
    var name:String
    var nameOrNull:String?

    var age:Int
    var ageOrNull:Int?

    // these are errors
    //name = null
    //age = null

    name = "Bob"
    age = 32

    nameOrNull = "Ted"
    nameOrNull = null
    ageOrNull = 32
    ageOrNull = null

    println("Name is $name and age is $age")
    println("NullabeName is $nameOrNull and nullableAge is $ageOrNull")
    println("Yelled name is ${name.uppercase()}")
    // compile error
    //println("Yelled null is ${nameOrNull.uppercase()}")
}
```
```shell
Name is Bob and age is 32
NullabeName is null and nullableAge is null
Yelled name is BOB
```
### Safe Calls

- The compiler will always try and check to unsure a NPE does not occur. The code below will not compile.
- However, the commented out code will run because it detects that a test for null as been done

```kotlin
package demo_2_9
fun main() {
    val b: String? = "Kotlin"
    //     if ( b != null && b.length > 0) {
    if (b != null && b.length > 0) {
        print("String of length ${b.length}")
    } else {
        print("Empty string")
    }
}
```
A safe call is an operator that takes nullability into account. Instead of throwing a NPE, the value `null` is propagated.
- Note that this possible because we have just reference variables in our source code.

```kotlin
package demo_2_10

fun main() {
    val a = "Kotlin"
    val b: String? = null
    println(b?.length)
    println(a?.length) // Unnecessary safe call

    var len:Int? = a.length
    println("Value of a.length is $len")

    len = b?.length
    println("Value of b.length is $len")
}
```
```shell
null
6
Value of a.length is 6
Value of b.length is null
```

### The Elvis Operator


```kotlin
package demo_2_11

fun main() {

    var s:String? = "Hi there"
    var elvis:String? = null

    println("The length of s is ${ s?.length ?: 0 }")
    println("The length of elvis is ${ elvis?.length ?: 0 }")
}
```
```shell
The length of s is 8
The length of elvis is 0
```

---

## Arrays

[Kotlin Documentation](https://kotlinlang.org/docs/arrays.html)

Arrays are of a fixed size. Resizing an array means creating a new array of the desired size then copying over the elements of the old array. The reason is tht Kotlin arrays are build on top of Java arrays which are designed to occupy a contiguous block of memory. This allows constant time access to any element in the array.

The contents of arrays themselves are mutable, however.

Arrays can be defined to be a specific types using the `Array<T>` notation. However, mixed types of arrays can be defined using `Array<Any>`. This works because the basic types and reference types are treated the same in Kotlin.

```kotlin
package demo_2_12

class Cat


fun main() {
    val aint:Array<Int>  = arrayOf(1,2,3,4)
    // this is an error
    //val a_int:Array<Int>  = arrayOf(1,2,3,"Fluffy")
    for (item in aint) {
        println("$item  is of type ${item::class}")
    }

    val acat:Array<Cat> = arrayOf(Cat(),Cat())

    for (item in acat) {
        println("$item  is of type ${item::class}")
    }
    // This is implicitly Array<Any>
    val a = arrayOf(1, 3, "Fluffy", Cat())
    for (item in a) {
        println("$item  is of type ${item::class}")
    }
}
```
```shell
1  is of type int  
2  is of type int  
3  is of type int  
4  is of type int ( 
demo_2_12.Cat@1c655221  is of type class demo_2_12.Cat 
demo_2_12.Cat@58d25a40  is of type class demo_2_12.Cat 
1  is of type class java.lang.Integer  
3  is of type class java.lang.Integer 
Fluffy  is of type class java.lang.String  
demo_2_12.Cat@1b701da1  is of type class demo_2_12.Cat  
```


#### Empty Array

`emptyArray` is a utility function that returns an empty array of a specified type. Instead of returning `null` or a nullable array, you can return an empty array to indicate that no elements are present, which avoids potential `NullPointerException` issues.

When you know in advance that an array is going to be empty, using `emptyArray()` is more efficient than creating a new array with a specified size of zero using `Array<T>(0) { ... }`. The emptyArray function returns a singleton instance of an empty array, avoiding unnecessary memory allocations.

**Common Use Cases:**

- _Default Parameters:_ Use emptyArray() as a default value for a function parameter that expects an array.
- _Returning Values:_ When a function's logic leads to a case where it should return an empty array instead of `null`, `emptyArray()` is a convenient and type-safe way to do this.



```kotlin
package demo_2_13

fun processNames(names: Array<String> = emptyArray()) {
    if (names.isEmpty()) {
        println("No names provided")
    } else {
        names.forEach { println(it) }
    }
}

fun main() {
    processNames() // Prints: No names provided
    processNames(arrayOf("Alice", "Bob")) // Prints: Alice Bob
}
```
```shell
No names provided
Alice
Bob
```

### Comparing arrays

In the following example notice that `d1` and `d2` have two different objects as their contents so they are not equivalent, but at a deeper leve, the two different objects have the same values so the are deep equals

```kotlin
package demo_2_14

fun main() {
    val a = arrayOf(1, 2, 3)
    val b = arrayOf(5, 6, 7)
    val c = arrayOf(5, 6, 7)

    println("Array a and b equal ${a.contentEquals(b)}")
    println("Array c and b equal ${c.contentEquals(b)}")
    println("Array a and b deep equal ${a.contentDeepEquals(b)}")
    println("Array c and b deep equal ${c.contentDeepEquals(b)}")

    val d1 = arrayOf(b)
    val d2 = arrayOf(c)
    println("Array d1 and d2 equal ${d1.contentEquals(d2)}")
}
```
```shell
Array a and b equal false
Array c and b equal true
Array a and b deep equal false
Array c and b deep equal true
Array d1 and d2 equal false
Array d1 and d2 deep equal true
```


## Control Flow

Control flow works similar to Java but with some differences. We will just highlight the differences here.

###  Conditionals

[Kotlin Documentation](https://kotlinlang.org/docs/control-flow.html#if-expression)

In Kotlin, the `if-then-else` construct return a value. In this way, it functions like the Java ternary operator "?:"

The return value of the expression is the value of the last expression in the block that was executed.

```kotlin
package demo_2_16

fun main() {
    val a = 2
    val b = 3
    var max: Int

    // Java style
    if (a > b) max= a
    else max= b
     println("Java style max = $max")

    // Kotlin Style
   max = if (a > b) a else b
    println("Kotlin style 1 max = $max")

   // More Kotlin Style
    max = if (a > b) {
        println("In the then branch")
        a
    } else {
        println("In the then branch")
        b
    }
    println("Kotlin style 2 max = $max")
}
```
```shell
Java style max = 3
Kotlin style 1 max = 3
In the then branch
Kotlin style 2 max = 3
```

### When

[Kotlin Documentation](https://kotlinlang.org/docs/control-flow.html#when-expression)

This is basically the same as a `switch` statement in Java with the same extension as with `if`, it returns a value

```kotlin
package demo_2_15

fun main() {
    val p = listOf(2,3,5,7,11)
    val x = 45
    val result = when {
        x > 11 -> "The number is too big"
        isEven(x) -> "An even number"
        x in p -> "A prime"
        x == 9 -> "Just a nine"
        else -> "No Matches"
    }
  println(result)
}

fun isEven(x:Int) : Boolean = 0 == x % 2
```

## Loops

As per the documentation

## Returns and Jumps


[Kotlin Documentation](https://kotlinlang.org/docs/returns.html)

In most cases, the behaviour of `return`, `break` and `continue` is the same as  Java; however, once loops and functions are nested, there has to be a way to determine which of the constructs we are returning from of jumping out of: the inner or the outer.

This is solved by providing labels for the constructs so that we can specify exactly which construct we are returning from or jumping out of.

```kotlin
package demo_2_17

fun main() {
    println("Demo of break with label:")
    breakDemo()

    println("\nDemo of continue with label:")
    continueDemo()
}

fun breakDemo() {
    outerLoop@ for (i in 1..3) {
        for (j in 1..3) {
            println("i = $i, j = $j")
            if (j == 2) {
                println("Breaking the outer loop when j == 2")
                break@outerLoop // breaks the outer loop
            }
        }
    }
    println("Exited the outer loop.")
}

fun continueDemo() {
    outerLoop@ for (i in 1..3) {
        for (j in 1..3) {
            if (j == 2) {
                println("Continuing the outer loop when j == 2")
                continue@outerLoop // skips to the next iteration of the outer loop
            }
            println("i = $i, j = $j")
        }
    }
    println("Finished the loops.")
}
```
```shell
Demo of break with label:
i = 1, j = 1
i = 1, j = 2
Breaking the outer loop when j == 2
Exited the outer loop.

Demo of continue with label:
i = 1, j = 1
Continuing the outer loop when j == 2
i = 2, j = 1
Continuing the outer loop when j == 2
i = 3, j = 1
Continuing the outer loop when j == 2
Finished the loops.
```
 
The same problem exists when a lambda function inside a function executes are return

```kotlin
package demo_2_18

fun main() {
    println("Demo of labeled return:")
    findNumber(listOf(1, 2, 3, 4, 5))

}

fun findNumber(numbers: List<Int>) {
    numbers.forEach numberLoop@{
        if (it == 4) {
            println("Found number 4, returning from lambda using labeled return.")
            return@numberLoop // returns from the lambda, not the enclosing function
            //println("Found number 4, returning from function.")
            //return
        }
        println("Processing number $it")
    }
    println("Finished processing all numbers.")
}
```

Note that if we are using an anonymous function instead of a lambda, the `return` always refers to returning from the anonymous function

## Exceptions

[Kotlin Documentation](https://kotlinlang.org/docs/exceptions.html)

Two of the big changes from Java are the fact that there are no checked exceptions.

The second is the use of the helper functions like `require()` to emulate as assertion type mechanism.

## End Module



