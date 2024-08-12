# Lab 5-1  Threads
<!--suppress CheckImageSize -->

## Lab Setup

Create a new Kotlin project like you did for the last lab. You will only need to work in a single file `Main.kt`

Alternatively, you can use the last project and just do each part in a new package

This lab is essentially a recap of the demos with the opportunity for you to experiment with threading. The important learning takeaway is that you have a good understanding of how threads work

---

## Part 1 - Creating and running multiple threads in three different ways


First, use the subclassing of the Thread class as in demo 5-1 but make some changes to have two threads running at the same time, and have each thread print out its name as it executes.

What you should see is the two threads taking turns printing out.

```kotlin
class MyThread : Thread() {
    override fun run() {
        // Define the task to be executed in the thread
        println("In thread ${Thread.currentThread().name}")
        for (i in 1..20) {
            println("Thread ${Thread.currentThread().name} is running: $i")
            Thread.sleep(500)  // Sleep for 500 milliseconds
        }
    }
}

fun main() {
    // Create an instance of the subclassed Thread
    val myThread = MyThread()
    val otherThread = MyThread()

    // Start the thread
    println("In main thread ${Thread.currentThread().name}")
    myThread.start()
    otherThread.start()

    // Main thread continues to run independently
    println("Main thread is finishing...")
}

```
```shell
In main thread main
Main thread is finishing...
In thread Thread-0
In thread Thread-1
Thread Thread-0 is running: 1
Thread Thread-1 is running: 1
Thread Thread-1 is running: 2
Thread Thread-0 is running: 2
.... etc ...
```
Some things to try. 

1. Change the sleep time to see how it affects the rate of output
2. Change the `start()` to `run()` and see what happens. Can you explain the result?

```kotlin
// Start the thread
    println("In main thread ${Thread.currentThread().name}")
    myThread.run()
    otherThread.run()
```

Optional: Redo the lab but modify the code from demo 5-2

---

## Part 2 - Kotlin Style threads

The basis for this demo 5-3 where we used the Kotlin form of creating a thread.  This time, we will create two threads using the thread builder `thread`

```kotlin 

import kotlin.concurrent.thread

fun main() {
    println("In main thread ${Thread.currentThread().name}")
    // Start a new thread using the thread function
    thread {
        println("In thread ${Thread.currentThread().name}")
        // Define the task to be executed in the thread
        for (i in 1..5) {
            println("Thread  ${Thread.currentThread().name} is running: $i")
            Thread.sleep(500)  // Sleep for 500 milliseconds
        }
    }
    thread {
        println("In thread ${Thread.currentThread().name}")
        // Define the task to be executed in the thread
        for (i in 1..5) {
            println("Thread  ${Thread.currentThread().name} is running: $i")
            Thread.sleep(500)  // Sleep for 500 milliseconds
        }
    }
    // Main thread continues to run independently
    println("Main thread is finishing...")
}
```
 Running this should produce the following output

```shell
In main thread main
In thread Thread-0
Thread  Thread-0 is running: 1
Main thread is finishing...
In thread Thread-1
Thread  Thread-1 is running: 1
Thread  Thread-0 is running: 2
Thread  Thread-1 is running: 2
Thread  Thread-0 is running: 3
Thread  Thread-1 is running: 3
Thread  Thread-1 is running: 4
Thread  Thread-0 is running: 4
Thread  Thread-0 is running: 5
Thread  Thread-1 is running: 5

```

Similar to what you did in the last session, replace the `thread` operator with `run` like this

```kotlin
   /
    run {
        println("In thread ${Thread.currentThread().name}")
        // Define the task to be executed in the thread
        for (i in 1..5) {
            println("Thread  ${Thread.currentThread().name} is running: $i")
            Thread.sleep(500)  // Sleep for 500 milliseconds
        }
    }
    run {
        println("In thread ${Thread.currentThread().name}")
        // Define the task to be executed in the thread
        for (i in 1..5) {
            println("Thread  ${Thread.currentThread().name} is running: $i")
            Thread.sleep(500)  // Sleep for 500 milliseconds
        }
    }

```
 Can you explain the result?

---

## Part 3 - Thready safety

In demo 5-6, we saw an example of a race condition with a simple counter.

```kotlin
    import kotlin.concurrent.thread

    class Counter {
        var count = 0

        fun increment() {
            count++
        }
    }

    fun main() {

        val counter = Counter()

        // Create 100 threads that increment the counter
        val threads = List(100) {
            thread {
                for (i in 1..1000) {
                    counter.increment()
                }
            }
        }

    }
```

Run the code a few times and notice the race condition. The total should be 100,000 but it is different everytime as the threads are interleaving reads and writes.

```shell
Final counter value: 99047
```

To make the `Counter` class thread-safe the `synchronized` keyword ensures that the increment method is executed by only one thread at a time.  

```kotlin
class Counter {
    @Volatile
    var count = 0

    fun increment() {
        synchronized(this) {
            count++
        }
    }

}
```

- `@Volatile`: This annotation ensures that the value of count is always read from and written to main memory, not cached, so all threads see the latest value.
- `synchronized(this)`: This block ensures that only one thread at a time can execute the code within it. When a thread enters the block, it locks the instance (this), preventing other threads from entering any synchronized block on the same instance until the lock is released.

Run the modified code and see that the value 100,000 is consistently generated.

---


## End Lab