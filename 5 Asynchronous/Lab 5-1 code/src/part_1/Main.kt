package part_1

class MyThread : Thread() {
    override fun run() {
        // Define the task to be executed in the thread
        println("In thread ${Thread.currentThread().name}")
        for (i in 1..20) {
            println("Thread ${Thread.currentThread().name} is running: $i")
            Thread.sleep(50)  // Sleep for 500 milliseconds
        }
    }
}


fun main() {
    // Create an instance of the subclassed Thread
    val myThread = MyThread()
    val otherThread = MyThread()

    // Start the thread
    println("In main thread ${Thread.currentThread().name}")
    myThread.start()
    otherThread.start()

    // Main thread continues to run independently
    println("Main thread is finishing...")
}