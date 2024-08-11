package demo_3_7

// Demo 3.7

class Thing {
    init {
        println("Init block")
    }
}

class PrivateThing private constructor()

fun main() {
    val thing = Thing()
    // Compiler error
    //val privateThing = PrivateThing()
}