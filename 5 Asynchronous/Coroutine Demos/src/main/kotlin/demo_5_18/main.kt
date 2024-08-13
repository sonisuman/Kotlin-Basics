package demo_5_18
import kotlinx.coroutines.*

fun main() = runBlocking {
    println("Launch async")
    val deferredResult = async {
        delay(1000L)
        "Result from async"
    }
    println("Main program continues")
    val result = deferredResult.await()  // Await the result
    println("Async result: $result")
}
