package part_3

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*

// Define a sealed class to represent messages sent to the actor
sealed class NumberMessage
data class Number(val value: Int) : NumberMessage()
data class GetSum(val responseChannel: SendChannel<Int>) : NumberMessage()

@OptIn(ExperimentalCoroutinesApi::class, ObsoleteCoroutinesApi::class)
fun main() = runBlocking<Unit> {
    // Create a producer coroutine that generates numbers
    val numberProducer = produce {
        for (x in 1..10) {
            send(x)
            delay(100L)
        }
    }

    // Create an actor coroutine that sums numbers
    val sumActor = actor<NumberMessage> {
        var sum = 0
        for (msg in channel) {
            when (msg) {
                is Number -> sum += msg.value
                is GetSum -> msg.responseChannel.send(sum)  // Send the current sum back through the response channel
            }
        }
    }

    // Connect the producer to the actor
    for (number in numberProducer) {
        sumActor.send(Number(number))
    }

    // Request and print the final sum using a response channel
    val responseChannel = Channel<Int>()
    sumActor.send(GetSum(responseChannel))
    val sum = responseChannel.receive()  // Receive the sum from the response channel
    println("The final sum is: $sum")

    // Close the actor and producer
    sumActor.close()
    numberProducer.cancel()
}
