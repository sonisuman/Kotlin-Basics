package demo_2_2

fun main() {

    // Type inferencing
    var int1 = 33
    var long1 = 3L
    // The following is a compile error
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