# Lab 5-4  Producers and Actors

<!--suppress CheckImageSize -->

### Objectives

1. Create a producer that generates data
2. Create an actor that receives data
3. Make them talk to each other

## Step 1 - Setup for the project

Use the same setup you had for the last lab. 

---

## Part 2 - Create the Producer

In this section we create the producer that generates a series of numbers. They are sent into the chanel `prodChannel`

Run it and confirm that the numbers are being generated

```kotlin
package part_1

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*

@OptIn(ExperimentalCoroutinesApi::class)
fun main() = runBlocking {
    // Create a producer coroutine that generates a stream of numbers
    val prodChannel = produce {
        for (x in 1..10) {
            send(x)  // Send each number to the channel
            delay(100L)  // Simulate some work
        }
    }

    // Consume the numbers
    for (number in prodChannel) {
        println("Received number: $number")
    }

    println("Done receiving numbers")
}

```
```shell
Received number: 1
Received number: 2
Received number: 3
Received number: 4
Received number: 5
Received number: 6
Received number: 7
Received number: 8
Received number: 9
Received number: 10
Done receiving numbers
```

## Part 3 - Creating the actor

The actor will receive messages of type 'NumberMessage' which is a sealed class. Why do this?
- Type Safety: A sealed class allows you to define a fixed set of types (subclasses) that can be used as messages for the actor. This ensures that only the predefined types can be sent to the actor, making the code safer and reducing the chance of errors due to unexpected message types.
- Exhaustive when Statements: When you use a sealed class in a when statement, the compiler knows all possible subclasses. This makes the when statement exhaustive, meaning the compiler can ensure that all possible cases are handled. If you forget to handle a case, the compiler will produce an error, which makes your code more robust.
- Encapsulation of Actor Messages: By using a sealed class, all possible messages that the actor can process are encapsulated in one place. This makes the actor’s communication protocol clear and easy to manage. If you need to add a new type of message, you can simply add a new subclass to the sealed class, and the compiler will enforce handling of this new message type wherever relevant.

- In the code below, the actor receives messages that are either a number object of a getSum object. When the getSum() message is sent, it includes a response channel for the actor to provide a reply

Run the code to ensure it works

```kotlin
package part_2
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*

// Define the message types
sealed class NumberMessage
data class Number(val value: Int) : NumberMessage()
data class GetSum(val responseChannel: SendChannel<Int>) : NumberMessage()

fun main() = runBlocking<Unit> {
    // Create an actor coroutine that sums numbers
    val sumActor = actor<NumberMessage> {
        var sum = 0
        for (msg in channel) {
            when (msg) {
                is Number -> sum += msg.value
                is GetSum -> msg.responseChannel.send(sum)  // Send the current sum back through the response channel
            }
        }
    }

    // Send some numbers to the actor
    sumActor.send(Number(1))
    sumActor.send(Number(2))
    sumActor.send(Number(3))

    // Request the sum from the actor using a response channel
    val responseChannel = Channel<Int>()
    sumActor.send(GetSum(responseChannel))
    val sum = responseChannel.receive()  // Receive the sum from the response channel
    println("The sum is: $sum")

    // Close the actor
    sumActor.close()
}
```

---

## Part 4 - Connect the two

In the code, the two are connected.  The instructor will do a code walkthrough

```kotlin
package part_3

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*

// Define a sealed class to represent messages sent to the actor
sealed class NumberMessage
data class Number(val value: Int) : NumberMessage()
data class GetSum(val responseChannel: SendChannel<Int>) : NumberMessage()

@OptIn(ExperimentalCoroutinesApi::class, ObsoleteCoroutinesApi::class)
fun main() = runBlocking<Unit> {
    // Create a producer coroutine that generates numbers
    val numberProducer = produce {
        for (x in 1..10) {
            send(x)
            delay(100L)
        }
    }

    // Create an actor coroutine that sums numbers
    val sumActor = actor<NumberMessage> {
        var sum = 0
        for (msg in channel) {
            when (msg) {
                is Number -> sum += msg.value
                is GetSum -> msg.responseChannel.send(sum)  // Send the current sum back through the response channel
            }
        }
    }

    // Connect the producer to the actor
    for (number in numberProducer) {
        sumActor.send(Number(number))
    }

    // Request and print the final sum using a response channel
    val responseChannel = Channel<Int>()
    sumActor.send(GetSum(responseChannel))
    val sum = responseChannel.receive()  // Receive the sum from the response channel
    println("The final sum is: $sum")

    // Close the actor and producer
    sumActor.close()
    numberProducer.cancel()
}

```

---



## End Lab