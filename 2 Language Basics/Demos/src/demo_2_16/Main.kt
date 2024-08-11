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