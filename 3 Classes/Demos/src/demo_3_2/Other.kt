package demo_3_2

// Demo 1.1 Program Structure
// Other.kt file

// this is visible in all files in package work
val alternateGreeting = "Go Away!!!"

// this is visible ONLY in this file
private val rudeGreeting = "You must be a PHP programmer"

// This can access the rudeGreeting variable since it is in the same file
fun goAway() {
    println(rudeGreeting)
}