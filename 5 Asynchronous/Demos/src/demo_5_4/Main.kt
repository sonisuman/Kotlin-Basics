package demo_5_4
import kotlin.concurrent.thread

// Demo 5.4

val task: () -> Unit = {

    for (i in 1..5) {
        println("Thread ${Thread.currentThread().name} is running: $i")
        Thread.sleep(500)  // Sleep for 500 milliseconds
    }
}

fun main() {
    println("In main thread ${Thread.currentThread().name}")
    // Start a new thread using the thread function
    thread(block = task, name="myThread")
    // Main thread continues to run independently
    println("Main thread is finishing...")
}

