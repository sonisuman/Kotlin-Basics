package demo_5_2

// Demo 5.2

// Create a runnable function
 val myRunnable = {
        println("In thread ${Thread.currentThread().name}")
        for (i in 1..5) {
            println("Thread is running: $i")
            Thread.sleep(500)  // Sleep for 500 milliseconds
        }
}

fun main() {

    // Pass the Runnable instance to a Thread
    val thread = Thread(myRunnable)

    // Start the thread
    println("In main thread ${Thread.currentThread().name}")
    thread.start()

    // Main thread continues to run independently
    println("Main thread is finishing...")
}
