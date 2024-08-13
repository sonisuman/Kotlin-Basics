@file:OptIn(ObsoleteCoroutinesApi::class)

package part_2
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*

// Define the message types
sealed class NumberMessage
data class Number(val value: Int) : NumberMessage()
data class GetSum(val responseChannel: SendChannel<Int>) : NumberMessage()

fun main() = runBlocking<Unit> {
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

    // Send some numbers to the actor
    sumActor.send(Number(1))
    sumActor.send(Number(2))
    sumActor.send(Number(3))

    // Request the sum from the actor using a response channel
    val responseChannel = Channel<Int>()
    sumActor.send(GetSum(responseChannel))
    val sum = responseChannel.receive()  // Receive the sum from the response channel
    println("The sum is: $sum")

    // Close the actor
    sumActor.close()
}
