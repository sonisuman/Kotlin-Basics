package part_4

import kotlinx.coroutines.*

fun main() = runBlocking {
    println("Starting Main ")
   runBlocking {
       println("---Starting runBlocking 1")
       delay(800)
       println("---Ending runBlocking 1")
   }
    runBlocking {
        println("---Starting runBlocking 2")
        delay(400)
        println("---Ending runBlocking 2")
    }
    println("Ending Main ")


}
