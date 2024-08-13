# Lab 5-3  Coroutines basics

<!--suppress CheckImageSize -->

### Objectives

1. Understand and use different dispatchers to control coroutine execution context.
2. Create and manage coroutine scopes.
3. Utilize various coroutine builders (launch, async, runBlocking) effectively.

## Step 1 - Setup for the project

Use the same setup you had for the last lab. 

---

# Step 2 - Introduction to Dispatchers

Write and run the following code:

```kotlin
import kotlinx.coroutines.*

fun main() = runBlocking {
    println("Main starts on: ${Thread.currentThread().name}")

    launch(Dispatchers.Default) {
        println("Default dispatcher on: ${Thread.currentThread().name}")
        delay(500)
    }

    launch(Dispatchers.IO) {
        println("IO dispatcher on: ${Thread.currentThread().name}")
        delay(500)
    }

    launch(Dispatchers.Unconfined) {
        println("Unconfined dispatcher on: ${Thread.currentThread().name}")
        delay(500)
        println("Unconfined dispatcher after delay: ${Thread.currentThread().name}")
    }


    println("Main ends on: ${Thread.currentThread().name}")
}

```
How does each dispatcher affect the thread on which the coroutine is executed?

What happens when the Unconfined dispatcher is used before and after a suspension?

---

## Part 3 - Scope

In this code, you will define and run your own custom scope.

Enter and run the following code:

```kotlin
package part_2

import kotlinx.coroutines.*

fun main() = runBlocking {
    println("Main starts on: ${Thread.currentThread().name}")

    val customScope = CoroutineScope(Dispatchers.Default)

    customScope.launch {
        println("---Custom scope launch 1 on: ${Thread.currentThread().name}")
        delay(500)
        println("---Custom scope end 1 on: ${Thread.currentThread().name}")
    }

    customScope.launch {
        println("+++Custom scope launch 2 on: ${Thread.currentThread().name}")
        delay(500)
        println("+++Custom scope end 2 on: ${Thread.currentThread().name}")
    }

    // Cancel the scope after 3000ms
    delay(3000)
    customScope.cancel()
    println("Custom scope canceled")

    println("Main ends on: ${Thread.currentThread().name}")
}

```
```shellMain starts on: main
---Custom scope launch 1 on: DefaultDispatcher-worker-1
+++Custom scope launch 2 on: DefaultDispatcher-worker-2
+++Custom scope end 2 on: DefaultDispatcher-worker-1
---Custom scope end 1 on: DefaultDispatcher-worker-2
Custom scope canceled
Main ends on: main
```

Now change the delays so that the delays in the two launches are 1000 and the delay in the main routine to 300 so that the `customScope.cancel()` gets executed before the coroutines are finished.

```shell
Main starts on: main
---Custom scope launch 1 on: DefaultDispatcher-worker-1
+++Custom scope launch 2 on: DefaultDispatcher-worker-2
Custom scope canceled
Main ends on: main
```

---

## Part 4 - Builders

In this part of the lab, compare the builders aync and launch

Create the code as shown

```kotlin
package part_3

import kotlinx.coroutines.*

fun main() = runBlocking {
    println("Main starts on: ${Thread.currentThread().name}")

    val deferredResult = async {
        println("Async coroutine on: ${Thread.currentThread().name}")
        delay(1000L)
        42
    }

    launch {
        println("Launch coroutine on: ${Thread.currentThread().name}")
        delay(500L)
        println("Launch coroutine ends")
    }

    val result = deferredResult.await()
    println("Result from async: $result")

    println("Main ends on: ${Thread.currentThread().name}")
}
```
Nte that `42` is the value returned by the async coroutine. The main thread will block until the `deferredResult.await()` returns.

 ```shell
Main starts on: main
Async coroutine on: main
Launch coroutine on: main
Launch coroutine ends
Result from async: 42
Main ends on: main
```

To see the blocking, increase the delay in the async to a larger value like 10000

---

## Optional

The three last packages of code in the lab allow for further experimentation with running different kinds of coroutines.

---


## End Lab