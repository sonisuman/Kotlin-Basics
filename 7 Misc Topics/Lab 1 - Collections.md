# Lab 7-1  Collections

## Part 1 - Working with lists

#### Step 1 -  Creating and Manipulating Lists

1. Create an immutable list of integers from 1 to 10.
2. Create a mutable list of strings representing the days of the week.
3. Print the first and last elements of the integer list.
4. Add a new day "Funday" to the mutable list and remove "Monday".
5. Sort the mutable list in alphabetical order and print it.

```kotlin
package part_1

fun main() {
    // Immutable list of integers
    val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    // Mutable list of days of the week
    val daysOfWeek = mutableListOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")

    // Print the first and last elements of the numbers list
    println("First element: ${numbers.first()}, Last element: ${numbers.last()}")

    // Add and remove elements in the mutable list
    daysOfWeek.add("Funday")
    daysOfWeek.remove("Monday")

    // Sort and print the list
    daysOfWeek.sort()
    println("Sorted days of the week: $daysOfWeek")
}
```

```shell
First element: 1, Last element: 10
Sorted days of the week: [Friday, Funday, Saturday, Sunday, Thursday, Tuesday, Wednesday]
```

#### Step 2 -  Advanced List Operations

1. Create a list of random integers.
2. Filter the list to only include even numbers.
3. Use map to create a new list where each number is squared.
4. Use fold to sum all the elements of the squared list.
5. Print the original, filtered, and squared lists, and the sum.

```kotlin
package part_2

fun main() {
    val randomNumbers = listOf(5, 12, 7, 8, 20, 15, 3, 2)

    // Filter even numbers
    val evenNumbers = randomNumbers.filter { it % 2 == 0 }

    // Square each number
    val squaredNumbers = evenNumbers.map { it * it }

    // Sum all elements
    val sumOfSquares = squaredNumbers.fold(0) { sum, number -> sum + number }

    println("Original list: $randomNumbers")
    println("Filtered (even) list: $evenNumbers")
    println("Squared list: $squaredNumbers")
    println("Sum of squares: $sumOfSquares")
}

```
```shell
Original list: [5, 12, 7, 8, 20, 15, 3, 2]
Filtered (even) list: [12, 8, 20, 2]
Squared list: [144, 64, 400, 4]
Sum of squares: 612

```

## Part 2 - Working with Sets

#### Step 1 - Understanding Sets

1. Create an immutable set of the first five prime numbers.
2. Create a mutable set of random integers, including duplicates.
3. Add and remove elements from the mutable set.
4. Check if a certain number is in the set.
5. Print the final contents of both sets.

```kotlin
fun main() {
    // Immutable set of prime numbers
    val primeNumbers = setOf(2, 3, 5, 7, 11)

    // Mutable set with duplicates
    val randomNumbers = mutableSetOf(1, 2, 3, 2, 4, 5, 3)

    // Add and remove elements
    randomNumbers.add(6)
    randomNumbers.remove(1)

    // Check for membership
    println("Is 3 in the set? ${randomNumbers.contains(3)}")

    println("Prime numbers: $primeNumbers")
    println("Random numbers: $randomNumbers")
}

```
```shell
Is 3 in the set? true
Prime numbers: [2, 3, 5, 7, 11]
Random numbers: [2, 3, 4, 5, 6]
```

#### Step 2 - Set Operations

1. Create two sets of integers.
2. Perform and print the results of the following operations:
   - Union of the two sets.
   - Intersection of the two sets.
   - Difference between the two sets.
3. Convert the union result into a list and sort it.

```kotlin
package part_4

fun main() {
    val setA = setOf(1, 2, 3, 4, 5)
    val setB = setOf(4, 5, 6, 7, 8)

    // Union
    val unionSet = setA.union(setB)
    println("Union: $unionSet")

    // Intersection
    val intersectionSet = setA.intersect(setB)
    println("Intersection: $intersectionSet")

    // Difference
    val differenceSet = setA.subtract(setB)
    println("Difference: $differenceSet")

    // Convert to list and sort
    val sortedUnionList = unionSet.toList().sorted()
    println("Sorted union list: $sortedUnionList")
}

```
```shell
Union: [1, 2, 3, 4, 5, 6, 7, 8]
Intersection: [4, 5]
Difference: [1, 2, 3]
Sorted union list: [1, 2, 3, 4, 5, 6, 7, 8]
```

## Part 3 - Working with Maps

#### Step 1 - Basic Map Operations

1. Create an immutable map of country names to their capital cities.
2. Create a mutable map of students' names to their grades.
3. Add a new student and update an existing student's grade.
4. Print the list of all students and their grades.

```kotlin
package part_5

fun main() {
    // Immutable map of countries and capitals
    val countryCapitals = mapOf("USA" to "Washington, D.C.", "France" to "Paris", "Germany" to "Berlin")

    // Mutable map of students and grades
    val studentGrades = mutableMapOf("Alice" to "A", "Bob" to "B", "Charlie" to "C")

    // Add and update entries
    studentGrades["David"] = "B"
    studentGrades["Alice"] = "A+"

    // Print all students and grades
    studentGrades.forEach { (student, grade) ->
        println("$student: $grade")
    }
}
```
```shell
Alice: A+
Bob: B
Charlie: C
David: B
```

#### Step 2 -  Advanced Map Operations

1. Create a map where the keys are words and the values are their lengths.
2. Filter the map to only include words with more than 3 letters.
3. Use mapValues to convert the lengths to uppercase letters (e.g., length 4 becomes 'D').
4. Group the words by their first letter using groupBy.

```kotlin
package part_6

fun main() {
    val words = listOf("apple", "banana", "pear", "kiwi", "grape")

    // Map words to their lengths
    val wordLengths = words.associateWith { it.length }

    // Filter words with more than 3 letters
    val filteredWordLengths = wordLengths.filter { it.value > 3 }

    // Convert lengths to uppercase letters
    val lengthAsLetters = filteredWordLengths.mapValues { (_, length) -> ('A' + (length - 1)) }

    // Group words by their first letter
    val groupedByFirstLetter = words.groupBy { it.first() }

    println("Word lengths: $wordLengths")
    println("Filtered word lengths: $filteredWordLengths")
    println("Lengths as letters: $lengthAsLetters")
    println("Grouped by first letter: $groupedByFirstLetter")
}
```
```shell
Word lengths: {apple=5, banana=6, pear=4, kiwi=4, grape=5}
Filtered word lengths: {apple=5, banana=6, pear=4, kiwi=4, grape=5}
Lengths as letters: {apple=E, banana=F, pear=D, kiwi=D, grape=E}
Grouped by first letter: {a=[apple], b=[banana], p=[pear], k=[kiwi], g=[grape]}
```

## End Lab