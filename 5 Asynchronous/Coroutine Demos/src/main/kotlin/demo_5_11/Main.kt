package demo_5_11

import kotlinx.coroutines.*

// Sequentially executes doWorld followed by "Done"
fun main() = runBlocking {
    println("main runBlocking: I'm working in thread ${Thread.currentThread().name}")
    doWorld()
    println("Done")
}

// Concurrently executes both sections
suspend fun doWorld() = coroutineScope { // this: CoroutineScope
    println("doWorld: I'm working in thread ${Thread.currentThread().name}")
    launch {
        println("first launch: I'm working in thread ${Thread.currentThread().name}")
        delay(2000L)
        println("World 2")
    }
    launch {
        println("first launch: I'm working in thread ${Thread.currentThread().name}")
        delay(1000L)
        println("World 1")
    }
    println("Hello")
}