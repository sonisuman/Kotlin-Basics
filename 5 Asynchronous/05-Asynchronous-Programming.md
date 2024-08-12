# 5. Asynchronous Programming

<!--suppress CheckImageSize -->
<img src="../images/Logo.png" width="300" alt="">

[Concurrency library](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.concurrent/)

---

## Some Basics

_Parallel computing_ is a type of computing “in which many calculations or processes are carried out simultaneously”.

_Concurrent computing_ is a form of computing in which several computations are executed concurrently – in overlapping time periods – instead of sequentially.

It is possible to have parallelism without concurrency, and concurrency without parallelism.

The difference between parallel and concurrent computing might not be obvious, but it is nevertheless very important, because it means that you can have  
- A parallel application
- A concurrent application
- A parallel and concurrent application

The main motivation for using parallelism is the desire to improve the performance of your code, as it allows you to split the workload into chunks that can be executed simultaneously, hence reducing the time spent on the task overall.

The main motivation for concurrency is the desire to increase responsiveness. This was used even before multicore processors to enable proper user interfaces.

<img src="images/parallel.png" alt="" width="600">

In the diagram above, the three colours represent three different tasks. In the concurrent and parallel version, the three tasks take turns using the two cores.

#### Concurrency: processes versus threads

<img src="images/threads1.png" alt="" width="600">

Operating systems work with processes. 
- Each process has its own (virtual) memory, executes its own code, and holds its own resources (like file descriptors) from the OS.
- For safety reasons, processes do not usually have access to the memory of other processes.

Threads, on the other hand, work in a single process, meaning that they share virtual memory and resources, while each has its own register, stack (frame), and program counter (the code that is being performed).

<img src="images/tasking.png" alt="" width="600">

There are different models for scheduling, with the main difference being who or what decides to switch the execution context and when they do so.
- In a preemptive model, the OS scheduler chooses when each thread gets processor time and how much of this time it gets. The user has limited control over this, so it appears mostly random.
- In a cooperative model, there are specific points where the execution context can be switched. The user does not know which tasks will be chosen, but they do know where switches can happen.

#### Java Threading

The JVM has its own scheduler which is independent of the OS scheduler
- A JVM thread != an OS thread but maps to OS threads
- Multithreaded JVM apps can run on a single-threaded OS
- JVM threads are either daemons or user threads.
- The app stops when all user threads are done.
- The JVM does not wait for daemon threads to finish.

Kotlin/JVM is the basis for threading because the Kotlin/Native API is not yet stable and there is not a Parallel Programming API in Kotlin/Common yet.
- Usually, JVM threads are mapped 1-to-1 to OS threads, but that is not a requirement for JVM implementations, so N-to-1 (DOS) or 1-to-N mapping is also possible.
- JCM has recently implemented support for virtual threads
- User threads are used for common tasks, while daemon threads are used for services like logging, which are not essential, meaning it is not critical if some parts of their work is lost.
- The `Kotlin.concurrent` API has wrapper classes for the Java concurrency APIs

---

## Creating Threads

Threads can be created by subclassing the `Thread` class and overriding the `run()` method

```kotlin
package demo_5_1

// Subclass the Thread class
class MyThread : Thread() {
    override fun run() {
        // Define the task to be executed in the thread
        println("In thread ${Thread.currentThread().name}")
        for (i in 1..5) {
            println("Thread is running: $i")
            Thread.sleep(500)  // Sleep for 500 milliseconds
        }
    }
}

fun main() {
    // Create an instance of the subclassed Thread
    val myThread = MyThread()

    // Start the thread
    println("In main thread ${Thread.currentThread().name}")
    myThread.start()

    // Main thread continues to run independently
    println("Main thread is finishing...")
}
```
```shell
In main thread main
Main thread is finishing...
In thread Thread-0
Thread is running: 1
Thread is running: 2
Thread is running: 3
Thread is running: 4
Thread is running: 5
Process finished with exit code 0
```

Or they can be created by creating a Thread with the constructor that takes a runnable object.

```kotlin
package demo_5_2

// Create a runnable function
val myRunnable = {
    println("In thread ${Thread.currentThread().name}")
    for (i in 1..5) {
        println("Thread is running: $i")
        Thread.sleep(500)  // Sleep for 500 milliseconds
    }
}

fun main() {

    // Pass the Runnable instance to a Thread
    val thread = Thread(myRunnable)

    // Start the thread
    println("In main thread ${Thread.currentThread().name}")
    thread.start()

    // Main thread continues to run independently
    println("Main thread is finishing...")
}

```

However, the preferred way is to use the higher order function `thread` that is defined [here](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.concurrent/thread.html)

```kotlin
package demo_5_3
import kotlin.concurrent.thread

fun main() {
    println("In main thread ${Thread.currentThread().name}")
    // Start a new thread using the thread function
    thread {
        println("In thread ${Thread.currentThread().name}")
        // Define the task to be executed in the thread
        for (i in 1..5) {
            println("Thread is running: $i")
            Thread.sleep(500)  // Sleep for 500 milliseconds
        }
    }
    // Main thread continues to run independently
    println("Main thread is finishing...")
}

```

The `thread` method starts a new thread in the above code. If replaced with `run`, then the code is executed in the same thread.

Another way to write the above is:

```kotlin 
package demo_5_4
import kotlin.concurrent.thread

val task: () -> Unit = {

    for (i in 1..5) {
        println("Thread ${Thread.currentThread().name} is running: $i")
        Thread.sleep(500)  // Sleep for 500 milliseconds
    }
}

fun main() {
    println("In main thread ${Thread.currentThread().name}")
    // Start a new thread using the thread function
    thread(block = task, name="myThread")
    // Main thread continues to run independently
    println("Main thread is finishing...")
}
```
```shell
In main thread main
Main thread is finishing...
Thread myThread is running: 1
Thread myThread is running: 2
Thread myThread is running: 3
Thread myThread is running: 4
Thread myThread is running: 5
```

#### Thread properties

A thread's properties cannot be changed after it is started. Main properties of a thread:
- _id:_ Long — This is the thread's identifier
- _name:_ String
- _priority:_ Int — This can range from 1 to 10, with a larger value indicating higher priority
_- daemon:_ Boolean
- _state:_ Thread.state
- _isAlive:_ Boolean

_State_ is specific, while _isAlive_ is a flag that is easier to understand and just signals that the thread is executing something. When a thread has been created but has not started, it has nothing to execute and is not alive. When the thread has finished all of its work, or after it encounters an error, it is also not alive.

There are different “blocked” states, because a thread can be blocked for different reasons.
- Blocked means it is waiting for some OS events, like writing to a socket.
- Waiting means it is waiting for some resource, like a lock or a condition.
- Timed waiting means the thread is sleeping or performing a blocking operation with timeout.
  
<img src="images/aynch1.png" alt="" width="600">

_Runnable_ is a state that indicates that a thread can be executed, meaning that it is up to the scheduler to decide whether it will be. The scheduler can move the thread away from the process (park it) at an arbitrary moment in time/statement in code.
- The _Running_ box is dashed, because we can think of it as a virtual state.
- It would make no sense to have a separate `Thread.state` for “_Running_”, because by the time you got this information, there’s a good chance that the scheduler would already have moved the thread back to Runnable.
- A thread can go to the _Waiting_ or _Blocked_ state only from the _Running_ state because the thread has to perform specific operations to block itself or to start waiting.

#### Manipulating Thread states

- _val myThread = thread { ... }_ — Creates a new 
- _myThread.start()_ — Starts a thread
- _myThread.join()_ — Causes the current thread to wait for another thread to finish
- _sleep(...)_ —  Puts the current thread to sleep
- _yield()_ — Tries to step back
- _myThread.interrupt()_ — Tries to interrupt a thread
- _myThread.isInterrupted()_ — Checks whether thread was interrupted
- _interrupted()_ — Checks and clears the interruption flag


The sleep and yield methods are only applicable to the current thread, which means that you cannot suspend another thread. All blocking and waiting methods can throw _InterruptedException_

An example of interrupting a thread is in demo 5.5

```kotlin
package demo_5_5
import kotlin.concurrent.thread

fun main() {
    // Create and start a thread
    val myThread = thread(start = true) {
        try {
            for (i in 1..10) {
                // Check if the thread has been interrupted
                if (Thread.currentThread().isInterrupted) {
                    println("Thread was interrupted, stopping execution...")
                    return@thread
                }

                println("Thread is running: $i")
                Thread.sleep(1000)  // Simulate some work by sleeping for 1 second
            }
        } catch (e: InterruptedException) {
            println("Thread interrupted during sleep, stopping execution...")
        }
    }

    // Main thread waits for 3 seconds before interrupting the other thread
    Thread.sleep(3000)
    println("Interrupting the thread...")
    myThread.interrupt()  // Interrupt the thread

    // Optionally, wait for the thread to finish
    myThread.join()
    println("Thread has finished.")
}
```
```shell
Thread is running: 1
Thread is running: 2
Thread is running: 3
Interrupting the thread...
Thread interrupted during sleep, stopping execution...
Thread has finished.
```
---

### Thread Problems

- In reality, programs (threads) spend a lot of time waiting for data to be fetched from disk, network, etc.
- The number of threads that can be launched is limited by the underlying operating system (each takes some number of MBs).
- Threads aren’t cheap, as they require context switches which are costly.
- Threads aren’t always available. Some platforms, such as JavaScript, do not even support them.
- Working with threads is hard. Bugs in threads (which are extremely difficult to debug), race conditions, and deadlocks are common problems we suffer from in multithreaded programming.
Threads terminating due to exceptions is a problem that deserves to be a separate point.

In practice, applications frequently work with the network and must do so for external events. This results in them spending a lot of time not doing anything useful.

There’s a seemingly obvious solution to the problem of having a considerable number of threads that remain blocked because they’re waiting for an I/O operation to finish: we can just increase the number of threads so that they can complete more useful work while some of them are being blocked. This does not always work, however, because there is a limit to the number of threads an application can use. This limit might come from the OS or simply from not having enough memory to store the desired amount of threads, because each thread requires MBs of memory for its stack and so on.

Furthermore, simply doubling the number of threads, for instance, will not cut execution time in half, as when there are more threads, more time is spent switching contexts to get to the processor before beginning to do anything useful.Creating two times more threads does not speed up the execution time twofold, because more threads require more context switching to get to the processor and start actually executing something useful. The more threads there are, the larger the percentage of time spent managing those threads instead of doing actual work.

Moreover, multi-threaded applications require thorough control over shared resources due to the huge number of multi-threaded specific problems, like race-conditions and deadlocks. When these problems arise, they are extremely difficult to debug and localize.

Another problem is that not everything always goes according to the plan, and exceptions are bound to happen. When an unhandled exception occurs, the thread terminates, and this situation is extremely hard to deal with from another thread.

---

## Coroutines


Coroutines are not a new concept. They existed long before Kotlin, Java, and even C. Coroutines can make up an application that will work on a cooperative multitasking model, where threads mostly work in preemptive multitasking.  Recently coroutines have found their way into a lot of languages.

- Melvin Conway coined the term “coroutine” in 1958 for his assembly program.
- Coroutines were first introduced as a language feature in Simula’67 with the detach and resume commands.
- A coroutine can be thought of as an instance of a suspendable computation, i.e. one that can suspend at some point and later resume execution, possibly even on another thread.
- Coroutines calling each other (and passing data back and forth) can form the machinery for cooperative multitasking.

[Kotlin Documentation](https://kotlinlang.org/docs/coroutines-basics.html)


A coroutine is an instance of suspendable computation. It is conceptually similar to a thread in the sense that it takes a block of code to run and has a similar life-cycle. It is created and started, but it is not bound to any particular thread. It may suspend its execution in one thread and resume in another one. Moreover, like a future or a promise, it can complete with some result (which is either a value or an exception).

### Coroutine Scope

A coroutine scope in Kotlin defines the context in which coroutines are launched and controls their lifecycle. It helps manage coroutines by ensuring that they are properly structured and that they can be cancelled or completed as a group.

`CoroutineScope` is an interface provided by the standard library, and its implementation can be found in `kotlinx.coroutines` or written from scratch. This interface only has one field – `CoroutineContext`.

#### Context and Lifecycle Management:

- A coroutine scope provides a context that is shared among all the coroutines launched within it. 
- this context includes elements like the dispatcher, job, and other coroutine-related configurations.
- Coroutines launched within a scope inherit the context and are children of the scope. This means if the scope is cancelled, all the coroutines in that scope are also cancelled.

#### Hierarchical Structure:
- Coroutines can be structured hierarchically using scopes. For example, a parent coroutine can launch child coroutines, and cancelling the parent coroutine will cancel all its children.

#### Built-in Scopes:

Kotlin provides some built-in coroutine scopes for common use cases:
- _GlobalScope_: A global scope that is tied to the lifetime of the entire application, but should be used sparingly because it is not bound to any specific lifecycle.
- _runBlocking_: A blocking scope that runs a coroutine and blocks the current thread until it completes.
- _CoroutineScope_: A general-purpose scope that can be used in custom classes, activities, or other components to manage coroutines tied to their lifecycle.

### Coroutine Contexts

`CoroutineContext` is an interface that is designed to store information about the execution environment for coroutines. You can think of it as a map from a class to an instance (object) of that class in the environment.

Each element of the context is a context on its own. It is designed to make working with contexts easy. For example, you can create a context by simply creating an instance of `CoroutineName`, and then you can add `CoroutineDispatcher` by simply using plus, which is overridden for contexts.

Why do we even need `CoroutineScope` if it only has one property – `CoroutineContext`? Couldn’t we achieve the same result by just using `CoroutineContext`?

This division is used to separate the coroutine’s execution environment/state, which is the context, from its behavior/lifecycle, which is the scope. We will revisit this idea when discussing structured concurrency.

### Dispatchers

A coroutine dispatcher in Kotlin is a component that determines the thread or thread pool on which a coroutine will run. It controls how and where the coroutine's execution is scheduled, allowing you to specify whether a coroutine should run on the main thread, a background thread, or a pool of threads optimized for specific tasks like I/O operations or CPU-intensive work.

Some of this is demonstrated in demo 5.7

```kotlin 
package demo_5_7

import kotlinx.coroutines.*

fun main() = runBlocking {
    println("Main context: $coroutineContext")
    println("Main name: ${coroutineContext[CoroutineName]}")
    println("Main scope: $this")
    println("Main Thread being used: ${Thread.currentThread().name}")

    // Create a coroutine scope
    val scope = CoroutineScope(Dispatchers.Default)
   // val scope = CoroutineScope(Dispatchers.Unconfined)
    // Launch a coroutine within the scope
    scope.launch(CoroutineName("Demo5-7")) {
        delay(1000L)
        println("Coroutine context: $coroutineContext")
        println("Coroutine name: ${coroutineContext[CoroutineName]}")
        println("Coroutine scope: $this")
        println("Thread being used: ${Thread.currentThread().name}")
    }

    // Optionally, cancel the scope to cancel all coroutines within it
    //scope.cancel()

    println("Main program continues...")
    delay(2000L) // Delay to keep the main thread alive until the coroutine completes
}

```

A more detailed example from the Kotlin documentation:

```kotlin
package demo_5_8

import kotlinx.coroutines.*

@DelicateCoroutinesApi
@OptIn(ExperimentalCoroutinesApi::class)

fun main() = runBlocking<Unit> {
    launch { // context of the parent, main runBlocking coroutine
        println("main runBlocking      : I'm working in thread ${Thread.currentThread().name}")
    }
    launch(Dispatchers.Unconfined) { // not confined -- will work with main thread
        println("Unconfined            : I'm working in thread ${Thread.currentThread().name}")
    }
    launch(Dispatchers.Default) { // will get dispatched to DefaultDispatcher
        println("Default               : I'm working in thread ${Thread.currentThread().name}")
    }
    launch(newSingleThreadContext("MyOwnThread")) { // will get its own new thread
        println("newSingleThreadContext: I'm working in thread ${Thread.currentThread().name}")
    }
}
```

```text
Unconfined            : I'm working in thread main
Default               : I'm working in thread DefaultDispatcher-worker-1
newSingleThreadContext: I'm working in thread MyOwnThread
main runBlocking      : I'm working in thread main
```

Inside the `CoroutineScope` we can write ordinary code or launch a coroutine that will asynchronously work in the background without blocking the main execution thread. We can think about `launch` as “fire and forget”. 

The code does not wait for `launch` to do anything, it just gets thrown into the scope to be executed at some point, while execution continues as though nothing happened. `launch` accepts a suspending block as an argument, which will be the code executed in a new coroutine. Things like `launch` that allow you to create new coroutines are called coroutine builders.

#### Unconfined dispatcher

Basically uses whatever thread is available when it resumes from a delay. Example from the Kotlin documentation.

```kotlin
package demo_5_9

import kotlinx.coroutines.*

fun main() = runBlocking<Unit> {
    launch(Dispatchers.Unconfined) { // not confined -- will work with main thread
        println("Unconfined      : I'm working in thread ${Thread.currentThread().name}")
        delay(500)
        println("Unconfined      : After delay in thread ${Thread.currentThread().name}")
    }
    launch { // context of the parent, main runBlocking coroutine
        println("main runBlocking: I'm working in thread ${Thread.currentThread().name}")
        delay(1000)
        println("main runBlocking: After delay in thread ${Thread.currentThread().name}")
    }
}
```
```text
Unconfined      : I'm working in thread main
main runBlocking: I'm working in thread main
Unconfined      : After delay in thread kotlinx.coroutines.DefaultExecutor
main runBlocking: After delay in thread main
```


## Extract function refactoring

[Kotlin Documentation](Extract function refactoring)

Note that the `suspend` keyword helps the compiler know this is a function called from inside a coroutine scope and there for can use the `delay()` function

```kotlin
package demo_5_10

import kotlinx.coroutines.*

fun main() = runBlocking { // this: CoroutineScope
    launch { doWorld() }
    println("Hello")
}

// this is your first suspending function
suspend fun doWorld() {
    delay(1000L)
    println("World!")
}

```

## Scope builder and concurrency

[Kotlin documenttion](https://kotlinlang.org/docs/coroutines-basics.html#scope-builder-and-concurrency)

