package demo_3_2

// Demo 3.1 Program Structure

// Main.kt file
// Note that the variable alternateGreeting is defined in the other file
// Other.kt in the same package work

val greeting = "Hello World"

fun main() {
    println(greeting)  // top level variable in this file/package
    println(alternateGreeting) // top level variable in Other.kt
    // println(rudeGreeting) // not allowed: private top level variable in another file
    goAway()  // top level function in Other.kt
}