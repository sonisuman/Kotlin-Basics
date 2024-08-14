# Lab 7-2  Sequences

## Part 1: Introduction to Sequences

#### Step 1 - Creating Sequences

1. Create a sequence from a list of integers.
2. Create a sequence using the sequenceOf function.
3. Create an empty sequence and print its contents.

```kotlin
package part_1

fun main() {
    // Create a sequence from a list
    val list = listOf(1, 2, 3, 4, 5)
    val sequenceFromList = list.asSequence()

    // Create a sequence using sequenceOf
    val sequenceOfNumbers = sequenceOf(6, 7, 8, 9, 10)

    // Create an empty sequence
    val emptySequence = emptySequence<Int>()

    println("Sequence from list: ${sequenceFromList.toList()}")
    println("Sequence of numbers: ${sequenceOfNumbers.toList()}")
    println("Empty sequence: ${emptySequence.toList()}")
}
```
```shell
Sequence from list: [1, 2, 3, 4, 5]
Sequence of numbers: [6, 7, 8, 9, 10]
Empty sequence: [] 
```
#### Step 2 - Sequence vs. List

1. Create a list and a sequence of numbers from 1 to 100.
2. Filter the list and the sequence to include only even numbers.
3. Map the filtered list and sequence to their squares.
4. Print the results and compare the behavior of list operations vs. sequence operations.

Notice that sequences perform operations lazily, only when the final result is needed (like calling toList()), whereas lists process all elements immediately.

```kotlin
fun main() {
    // Create a list and a sequence
    val numbersList = (1..100).toList()
    val numbersSequence = (1..100).asSequence()

    // Filter and map the list
    val filteredList = numbersList.filter { it % 2 == 0 }.map { it * it }
    println("Filtered and mapped list: $filteredList")

    // Filter and map the sequence
    val filteredSequence = numbersSequence.filter { it % 2 == 0 }.map { it * it }
    println("Filtered and mapped sequence: ${filteredSequence.toList()}")
}

```

## Part 2: Lazy Evaluation in Sequences

#### Step 1 - Understanding Lazy Evaluation

1. Create a sequence of integers from 1 to 10.
2. Apply a filter operation that only passes even numbers.
3. Apply a map operation that squares each number.
4. Print each number in the sequence and observe when the operations are applied.


Notice how filtering and mapping are interleaved during iteration, highlighting the lazy nature of sequences.

```kotlin
package part_3

fun main() {
    val sequence = (1..10).asSequence()
        .filter {
            println("Filtering: $it")
            it % 2 == 0
        }
        .map {
            println("Mapping: $it")
            it * it
        }

    println("Result: ${sequence.toList()}")
}

```
```shell
Filtering: 1
Filtering: 2
Mapping: 2
Filtering: 3
Filtering: 4
Mapping: 4
Filtering: 5
Filtering: 6
Mapping: 6
Filtering: 7
Filtering: 8
Mapping: 8
Filtering: 9
Filtering: 10
Mapping: 10
Result: [4, 16, 36, 64, 100]
```

#### Step - Infinite Sequences with generateSequence

 1. Create an infinite sequence of natural numbers starting from 1 using generateSequence.
2. Filter the sequence to only include numbers divisible by 3.
3. Take the first 10 numbers from the filtered sequence and print them.

```kotlin
package part_4

fun main() {
    // Create an infinite sequence
    val infiniteSequence = generateSequence(1) { it + 1 }

    // Filter and take first 10 numbers divisible by 3
    val firstTenDivisibleBy3 = infiniteSequence
        .filter { it % 3 == 0 }
        .take(10)
        .toList()

    println("First 10 numbers divisible by 3: $firstTenDivisibleBy3")
}
```
```shell
First 10 numbers divisible by 3: [3, 6, 9, 12, 15, 18, 21, 24, 27, 30]
```

## Part 3: Advanced Sequence Operations

#### Step 1 - Using flatMap with Sequences

1. Create a sequence of lists where each list contains a few numbers.
2. Use flatMap to create a single sequence containing all the numbers from all lists.
3. Print the flattened sequence.

```kotlin
fun main() {
    val sequenceOfLists = sequenceOf(
        listOf(1, 2, 3),
        listOf(4, 5, 6),
        listOf(7, 8, 9)
    )

    val flatMappedSequence = sequenceOfLists.flatMap { it.asSequence() }

    println("Flattened sequence: ${flatMappedSequence.toList()}")
}
```
```shell
Flattened sequence: [1, 2, 3, 4, 5, 6, 7, 8, 9]
```

#### Step 2 - Custom Sequence Builder

1. Create a custom sequence builder using the sequence function.
2. Generate a sequence that alternates between two numbers (e.g., 1 and 2) indefinitely.
3. Take the first 10 elements of this sequence and print them.

```kotlin
package part_6

fun alternatingSequence(): Sequence<Int> = sequence {
    while (true) {
        yield(1)
        yield(2)
    }
}

fun main() {
    val sequence = alternatingSequence().take(10)
    println("Alternating sequence: ${sequence.toList()}")
}

```
```shell
Alternating sequence: [1, 2, 1, 2, 1, 2, 1, 2, 1, 2]
```

## End Lab