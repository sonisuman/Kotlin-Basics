package part_3

import kotlinx.coroutines.*

fun main() = runBlocking {
    println("Main starts on: ${Thread.currentThread().name}")

    val deferredResult = async {
        println("Async coroutine on: ${Thread.currentThread().name}")
        delay(1000L)
        42
    }

    launch {
        println("Launch coroutine on: ${Thread.currentThread().name}")
        delay(500L)
        println("Launch coroutine ends")
    }

    val result = deferredResult.await()
    println("Result from async: $result")

    println("Main ends on: ${Thread.currentThread().name}")
}
