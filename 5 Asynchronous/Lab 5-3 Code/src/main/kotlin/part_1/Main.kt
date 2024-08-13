package part_1

import kotlinx.coroutines.*

fun main() = runBlocking {
    println("Main starts on: ${Thread.currentThread().name}")

    launch(Dispatchers.Default) {
        println("Default dispatcher on: ${Thread.currentThread().name}")
        delay(500)
    }

    launch(Dispatchers.IO) {
        println("IO dispatcher on: ${Thread.currentThread().name}")
        delay(500)
    }

    launch(Dispatchers.Unconfined) {
        println("Unconfined dispatcher on: ${Thread.currentThread().name}")
        delay(500)
        println("Unconfined dispatcher after delay: ${Thread.currentThread().name}")
    }


    println("Main ends on: ${Thread.currentThread().name}")
}
