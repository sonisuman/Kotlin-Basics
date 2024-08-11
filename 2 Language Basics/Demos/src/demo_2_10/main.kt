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