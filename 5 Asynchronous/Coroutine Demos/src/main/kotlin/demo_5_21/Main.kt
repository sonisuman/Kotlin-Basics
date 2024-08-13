package demo_5_21

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*

fun main() = runBlocking {
    val channel = produce {
        for (x in 1..5) {
            send(x)
            delay(100L)
        }
    }

    for (y in channel) {
        println(y)
    }
}
