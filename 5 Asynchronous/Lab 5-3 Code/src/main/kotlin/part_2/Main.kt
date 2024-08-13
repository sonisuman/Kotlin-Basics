package part_2

import kotlinx.coroutines.*

fun main() = runBlocking {
    println("Main starts on: ${Thread.currentThread().name}")

    val customScope = CoroutineScope(Dispatchers.Default)

    customScope.launch {
        println("---Custom scope launch 1 on: ${Thread.currentThread().name}")
        delay(500)
        println("---Custom scope end 1 on: ${Thread.currentThread().name}")
    }

    customScope.launch {
        println("+++Custom scope launch 2 on: ${Thread.currentThread().name}")
        delay(500)
        println("+++Custom scope end 2 on: ${Thread.currentThread().name}")
    }

    // Cancel the scope after 3000ms
    delay(3000)
    customScope.cancel()
    println("Custom scope canceled")

    println("Main ends on: ${Thread.currentThread().name}")
}
