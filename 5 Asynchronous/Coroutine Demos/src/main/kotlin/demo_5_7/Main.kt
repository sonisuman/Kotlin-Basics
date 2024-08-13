package demo_5_7

import kotlinx.coroutines.*

fun main() = runBlocking {
    println("Main context: $coroutineContext")
    println("Main name: ${coroutineContext[CoroutineName]}")
    println("Main scope: $this")
    println("Main Thread being used: ${Thread.currentThread().name}")

    // Create a coroutine scope
    val scope = CoroutineScope(Dispatchers.Default)
    // val scope = CoroutineScope(Dispatchers.Unconfined)
    // Launch a coroutine within the scope
    scope.launch(CoroutineName("Demo5-7")) {
        delay(1000L)
        println("Coroutine context: $coroutineContext")
        println("Coroutine name: ${coroutineContext[CoroutineName]}")
        println("Coroutine scope: $this")
        println("Thread being used: ${Thread.currentThread().name}")
    }

    // Optionally, cancel the scope to cancel all coroutines within it
    //scope.cancel()

    println("Main program continues...")
    delay(2000L) // Delay to keep the main thread alive until the coroutine completes
}