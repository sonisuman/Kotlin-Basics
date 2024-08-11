# Lab 2-2  Arrays 
<!--suppress CheckImageSize -->

## Lab Setup

Create a new Kotlin project like you did for the last lab. You will only need to work in a single file `Main.kt`

Alternatively, you can use the last project and just do each part in a new package

---

## Part 1 - Array Types

An array's type is the type of data it contains. 

In the `main()` method, create and array of `Int` and an array of `String`
Notice that the type can be explicit or implicit.

We also create an `Any` array of various types, including an array.

Also create an empty class, in this example we are using `Cat`

The `println()` method prints out a reference to the object. You can see that the reference clearly indicated the type


```kotlin

class Cat

fun main() {
    var intArray:Array<Int> = arrayOf(1,2,3)
    val otherInt = arrayOf(10,101)
    var stringArray:Array<String> = arrayOf("Hello", "World")
    val otherString = arrayOf("Goodbye","World")
    
    val anyArray = arrayOf(45.0, Cat(), "Hi there", otherInt)
    println(intArray)
    println(otherString)
    println(anyArray)

}
```
You should see output that looks like this, although the part after the `@` will be different.

```shell
[Ljava.lang.Integer;@723279cf
[Ljava.lang.String;@10f87f48
[Ljava.lang.Object;@b4c966a

```

Now add a generic function that will print out elements of at array and their type

```kotlin
fun <T> printArrayElements(array: Array<T>) {
    for (element in array) {
        println(element)
    }
}
```

Use the function in `main()` to print out the contents of the `stringArray` and `anyArray`

```kotlin
fun main() {
    var intArray:Array<Int> = arrayOf(1,2,3)
    val otherInt = arrayOf(10,101)
    var stringArray:Array<String> = arrayOf("Hello", "World")
    val otherString = arrayOf("Goodbye","World")

    val anyArray = arrayOf(45.0, Cat(), "Hi there", otherInt)
    // println(intArray)
    //  println(otherString)
    //  println(anyArray)
    printArrayElements(stringArray)
    printArrayElements(anyArray)
}
```
You should see output like this:

```shell
45.0
part_1.Cat@6e8cf4c6
Hi there
[Ljava.lang.Integer;@12edcd21
```
---

## Part Two - Creating arrays

In this section, you will create an array of `Int?` and an array of `Int` using two different methods.

Start with a new project or package and create a `Mail.kt` file and add the helper function below we used in the last sections

```kotlin 
fun <T> printArrayElements(array: Array<T>) {
    for (element in array) {
        println(element)
    }
}
```

In the `main()` function create the two arrays, modify an element in each and then print them out.

```kotlin
fun main() {
    val intNullArray: Array<Int?> = arrayOfNulls(3)
    intNullArray[0] = 124
    println("Int Null Array")
    printArrayElements(intNullArray)

    val intArray:Array<Int> =  Array(3) { 0 }
    intArray[1] = 98
    println("Int Array")
    printArrayElements(intArray)
}
```

The output should look something like this:

```shell
Int Null Array
124
null
null
Int Array
0
98
0
```

Check to see what happens when you change `Int?` to `Int` in the first definition.

---

## Part 3 - Array operations

Create a new project or package and crete a `Main.kt` file. In this part, the code is provided to experiment with a variety of operations that can be performed on arrays.  

Experiment with the code and run it to see how the operations work.

```kotlin
fun main() {
    val a = arrayOf(1.0, 2.3, 4.5, 8.3)

    // Accessing elements
    println("First element: ${a[0]}")
    println("Last element: ${a[a.size - 1]}")

    // Modifying elements
    a[1] = 3.7
    println("Modified array: ${a.joinToString()}")

    // Iterating over elements
    for (element in a) {
        println(element)
    }

    // Finding an element
    val index = a.indexOf(4.5)
    println("Index of 4.5: $index")

    val containsValue = a.contains(8.3)
    println("Array contains 8.3: $containsValue")

    // Filtering elements
    val filteredArray = a.filter { it > 3.0 }
    println("Filtered array (elements > 3.0): ${filteredArray.joinToString()}")

    // Summing elements
    val sum = a.sum()
    println("Sum of array elements: $sum")

    // Finding minimum and maximum
    val min = a.minOrNull()
    val max = a.maxOrNull()
    println("Minimum element: $min")
    println("Maximum element: $max")

    // Sorting the array
    val sortedArray = a.sortedArray()
    println("Sorted array: ${sortedArray.joinToString()}")

    // Reversing the array
    val reversedArray = a.reversedArray()
    println("Reversed array: ${reversedArray.joinToString()}")

    // Mapping elements
    val multipliedArray = a.map { it * 2 }
    println("Array with elements multiplied by 2: ${multipliedArray.joinToString()}")
}

```
The output should look something like this.

``shell
First element: 1.0
Last element: 8.3
Modified array: 1.0, 3.7, 4.5, 8.3
1.0
3.7
4.5
8.3
Index of 4.5: 2
Array contains 8.3: true
Filtered array (elements > 3.0): 3.7, 4.5, 8.3
Sum of array elements: 17.5
Minimum element: 1.0
Maximum element: 8.3
Sorted array: 1.0, 3.7, 4.5, 8.3
Reversed array: 8.3, 4.5, 3.7, 1.0
Array with elements multiplied by 2: 2.0, 7.4, 9.0, 16.6
``


---

## End Lab