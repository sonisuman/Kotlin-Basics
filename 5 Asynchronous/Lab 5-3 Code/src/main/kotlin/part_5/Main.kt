package part_5

import kotlinx.coroutines.*


fun main() = runBlocking {
    println("Starting Main ")
    launch {
        println("---Starting launch 1")
        delay(800)
        println("---Ending launch 1")
    }
    runBlocking {
        println("---Starting launch 2")
        delay(400)
        println("---Ending launch 2")
    }
    println("Ending Main ")

}