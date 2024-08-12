package demo_5_5
import kotlin.concurrent.thread

fun main() {
    // Create and start a thread
    val myThread = thread(start = true) {
        try {
            for (i in 1..10) {
                // Check if the thread has been interrupted
                if (Thread.currentThread().isInterrupted) {
                    println("Thread was interrupted, stopping execution...")
                    return@thread
                }

                println("Thread is running: $i")
                Thread.sleep(1000)  // Simulate some work by sleeping for 1 second
            }
        } catch (e: InterruptedException) {
            println("Thread interrupted during sleep, stopping execution...")
        }
    }

    // Main thread waits for 3 seconds before interrupting the other thread
    Thread.sleep(3000)
    println("Interrupting the thread...")
    myThread.interrupt()  // Interrupt the thread

    // Optionally, wait for the thread to finish
    myThread.join()
    println("Thread has finished.")
}
