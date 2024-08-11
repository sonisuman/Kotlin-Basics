package part_2

fun <T> printArrayElements(array: Array<T>) {
    for (element in array) {
        println(element)
    }
}

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