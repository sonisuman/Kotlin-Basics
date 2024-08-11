package demo_2_14

fun main() {
    val a = arrayOf(1,2,3)
    val b = arrayOf(5,6,7)
    val c = arrayOf(5,6,7)

    println("Array a and b equal ${a.contentEquals(b)}")
    println("Array c and b equal ${c.contentEquals(b)}")
    println("Array a and b deep equal ${a.contentDeepEquals(b)}")
    println("Array c and b deep equal ${c.contentDeepEquals(b)}")

    val d1 = arrayOf(b)
    val d2 = arrayOf(c)
    println("Array d1 and d2 equal ${d1.contentEquals(d2)}")
    println("Array d1 and d2 deep equal ${d1.contentDeepEquals(d2)}")
}