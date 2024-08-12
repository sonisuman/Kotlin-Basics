package part_3

import kotlin.concurrent.thread

class Counter {
    @Volatile
    var count = 0

    fun increment() {
        synchronized(this) {
            count++
        }
    }

}


fun main() {

    val counter = Counter()

    // Create 100 threads that increment the counter
    val threads = List(100) {
        thread {
            for (i in 1..1000) {
                counter.increment()
            }
        }
    }

    // Wait for all threads to finish
    threads.forEach { it.join() }

    // Print the final value of the counter
    println("Final counter value: ${counter.count}")
}
