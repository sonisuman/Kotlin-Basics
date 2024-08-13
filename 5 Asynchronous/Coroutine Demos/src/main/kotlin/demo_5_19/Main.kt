package demo_5_19

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*

fun main() = runBlocking<Unit> {
    // Create a channel to send and receive integers
    val channel = Channel<Int>()

    // Launch a coroutine to send data into the channel
    launch {
        for (i in 1..5) {
            channel.send(i)  // Sending data
            println("Sent: $i")
        }
        channel.close()  // Closing the channel after sending
    }

    // Launch a coroutine to receive data from the channel
    launch {
        for (i in channel) {  // Receiving data
            println("Received: $i")
        }
    }
}
