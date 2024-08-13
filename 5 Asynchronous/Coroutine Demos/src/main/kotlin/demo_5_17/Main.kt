import kotlinx.coroutines.*

fun main() {
    println("Main program starts: ${Thread.currentThread().name}")

    // Launch a coroutine in the GlobalScope
    GlobalScope.launch {
        println("Fake network request starts: ${Thread.currentThread().name}")
        val data = fetchDataFromNetwork()
        println("Data fetched: $data")
    }

    // Simulate some other work in the main thread
    println("Main thread is doing other work...")

    // Keep the main thread alive for a while to see the coroutine's output
    Thread.sleep(3000) // Not needed in a real application with an event loop or main loop

    println("Main program ends: ${Thread.currentThread().name}")
}

// Simulating a network call that fetches data
suspend fun fetchDataFromNetwork(): String {
    delay(2000) // Simulate network delay
    return "Fetched Data"
}

