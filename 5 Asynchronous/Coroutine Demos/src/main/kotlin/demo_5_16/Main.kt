package demo_5_16

import kotlinx.coroutines.*

fun main() {
    // Creating a custom coroutine scope with a Job and Dispatcher
    val myCoroutineScope = CoroutineScope(Job() + Dispatchers.Default)

    // Launching a coroutine within the custom scope
    myCoroutineScope.launch {
        try {
            val data = fetchDataFromNetwork()
            println("Data fetched: $data")
        } catch (e: Exception) {
            println("Error fetching data: ${e.message}")
        }
    }

    // Simulate some other work in the main thread
    println("Main thread is running...")

    // Keeping the main thread alive to see coroutine's output
    Thread.sleep(3000)

    // Cancel the scope to clean up any running coroutines
    myCoroutineScope.cancel()
}

// Simulating a network call that fetches data
suspend fun fetchDataFromNetwork(): String {
    delay(2000) // Simulate network delay
    return "Fetched Data"
}
