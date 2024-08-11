package part_3

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
