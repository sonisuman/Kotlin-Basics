package demo_3_8

class Rect(w: Double, h: Double) {
    var height :Double = if (h > 0.0) h else throw IllegalArgumentException("Height must be greater than 0")
        get() {
            println("-----Height getter")
            return field
        }
        set(h) {
            println("-----Height setter")
            if (h <= 0.0)  throw IllegalArgumentException("Height must be greater than 0")
            field = h
        }
    var width: Double = if (w > 0.0) w else throw IllegalArgumentException("Width must be greater than 0")

    val area
        get() = this.height * this.width



}

fun main() {
    val r = Rect(1.0,1.0)
    println("The height is ${r.height}")
    println("The width is ${r.width}")
    println("The area is ${r.area}")
    r.height = 4.0
    println("The height is ${r.height}")
    println("The area is ${r.area}")

}