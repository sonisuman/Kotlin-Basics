package demo_5_13

import kotlinx.coroutines.*

fun main() = runBlocking {
    val job1 = launch { // launch a new coroutine and keep a reference to its Job
        val job2 = launch { // launch a new coroutine and keep a reference to its Job
            delay(3000L)
            println("This is job 2")
        }
        //job2.join()
        delay(1000L)
        println("This is job 1")
    }

    println("Hello")
    //job1.join() // wait until child coroutine completes
    // job1.cancel()
    println("Done")
}