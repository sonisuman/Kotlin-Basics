package demo_5_12

import kotlinx.coroutines.*

fun main() = runBlocking {
    val job1 = launch { // launch a new coroutine and keep a reference to its Job
        delay(2000L)
        println("This is job 1")
    }
    val job2 = launch { // launch a new coroutine and keep a reference to its Job
       // job1.cancel()
       // job1.join()
        delay(1000L)
        println("This is job 2")
    }
    println("Hello")
   //job1.join() // wait until child coroutine completes
    println("Done")
}