package part_6

import kotlinx.coroutines.*


fun main() = runBlocking {
    println("Starting Main ")
    val v1 = async {
        println("---Starting async 1")
        delay(800)
        println("---Ending async 1")
        "One"
    }
   val v2 = async {
        println("---Starting async 2")
        delay(400)
        println("---Ending async 2")
       "Two"
    }
    // Waiting for 2
    println("Result = ${v2.await()}")
    println("Result = ${v1.await()}")
    println("Ending Main ")

}