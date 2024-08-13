package demo_5_22

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*

fun main() = runBlocking<Unit> {
    val actor = actor<Int> {
        var sum = 0
        for (msg in channel) {
            sum += msg
            println("Received $msg, sum is now $sum")
        }
    }

    actor.send(1)
    actor.send(2)
    actor.send(3)
    actor.close()  // Closing the actor
}
