package demo_5_1

// Demo 5.1

// Subclass the Thread class
class MyThread : Thread() {
    override fun run() {
        // Define the task to be executed in the thread
        println("In thread ${Thread.currentThread().name}")
        for (i in 1..5) {
            println("Thread is running: $i")
            Thread.sleep(500)  // Sleep for 500 milliseconds
        }
    }
}

fun main() {
    // Create an instance of the subclassed Thread
    val myThread = MyThread()

    // Start the thread
    println("In main thread ${Thread.currentThread().name}")
    myThread.start()

    // Main thread continues to run independently
    println("Main thread is finishing...")
}