package demo_3_10

// Demo 3.10

open class Shape {
    open fun draw() {
        println("Shape draw")
    }  // removing open creates an error
    open val vertexCount: Int = 0
    fun fill() { /*...*/ }
}

open class Rectangle() : Shape() {
    override val vertexCount = 4
    override fun draw() {
        println("Rectangle draw")
    }  // removing override is an error
}

class Box() : Rectangle() {
    override fun draw() {
       // super.draw()
        println("Box draw")
    }

}

fun main() {
    val b = Box()
    println("Vertices = ${b.vertexCount}")

    b.draw()
}