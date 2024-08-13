package demo_5_15


import kotlinx.coroutines.*

fun main() = runBlocking<Unit> {
    runBlocking { // not confined -- will work with main thread
        println("First Block  : I'm working in thread ${Thread.currentThread().name}")
        delay(500)
        println("First Block   : After delay in thread ${Thread.currentThread().name}")
    }
    runBlocking { // context of the parent, main runBlocking coroutine
        println("Second Block: I'm working in thread ${Thread.currentThread().name}")
        delay(1000)
        println("Second Block: After delay in thread ${Thread.currentThread().name}")
    }
}