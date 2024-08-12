package demo_5_6
import kotlin.concurrent.thread

// Demo 5.6

class Counter {
    var count = 0

    fun increment() {
        count++
    }
}

fun main() {
    // Cra
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
