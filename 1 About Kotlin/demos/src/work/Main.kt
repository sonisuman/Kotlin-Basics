package work

// Demo 1.1 Program Structure

// Main.kt file
// Note that the variable alternateGreeting is defined in another file
// Other.kt in the same package work

val Greeting = "Hello World"

fun main() {
    println(Greeting)  // top level variable in this file/package
    println(alternateGreeting) // top level variable in Other.kt
    // println(rudeGreeting) // not allowed: private top level variable in another file
    goAway()  // top level function in Other.kt
}