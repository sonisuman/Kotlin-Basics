package part_1

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*

@OptIn(ExperimentalCoroutinesApi::class)
fun main() = runBlocking {
    // Create a producer coroutine that generates a stream of numbers
    val prodChannel = produce {
        for (x in 1..10) {
            send(x)  // Send each number to the channel
            delay(100L)  // Simulate some work
        }
    }

    // Consume the numbers
    for (number in prodChannel) {
        println("Received number: $number")
    }

    println("Done receiving numbers")
}
