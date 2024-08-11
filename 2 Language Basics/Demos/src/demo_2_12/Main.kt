package demo_2_12


class Cat


fun main() {
    val aint:Array<Int>  = arrayOf(1,2,3,4)
    // this is an error
    //val a_int:Array<Int>  = arrayOf(1,2,3,"Fluffy")
    for (item in aint) {
        println("$item  is of type ${item::class}")
    }

    val acat:Array<Cat> = arrayOf(Cat(),Cat())

    for (item in acat) {
        println("$item  is of type ${item::class}")
    }
    // This is implicitly Array<Any>
    val a = arrayOf(1, 3, "Fluffy", Cat())
    for (item in a) {
        println("$item  is of type ${item::class}")
    }
}