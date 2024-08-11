package part_1

class Cat

fun <T> printArrayElements(array: Array<T>) {
    for (element in array) {
        println(element)
    }
}

fun main() {
    var intArray:Array<Int> = arrayOf(1,2,3)
    val otherInt = arrayOf(10,101)
    var stringArray:Array<String> = arrayOf("Hello", "World")
    val otherString = arrayOf("Goodbye","World")

    val anyArray = arrayOf(45.0, Cat(), "Hi there", otherInt)
    println(intArray)
    println(otherString)
    println(anyArray)
    printArrayElements(stringArray)
    printArrayElements(anyArray)
}