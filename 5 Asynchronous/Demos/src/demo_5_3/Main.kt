package demo_5_3
import kotlin.concurrent.thread

// Demo 5.3

fun main() {
    println("In main thread ${Thread.currentThread().name}")
    // Start a new thread using the thread function
    thread {
        println("In thread ${Thread.currentThread().name}")
        // Define the task to be executed in the thread
        for (i in 1..5) {
            println("Thread is running: $i")
            Thread.sleep(500)  // Sleep for 500 milliseconds
        }
    }
    // Main thread continues to run independently
    println("Main thread is finishing...")
}

