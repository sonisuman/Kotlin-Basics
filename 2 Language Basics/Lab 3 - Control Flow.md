# Lab 2-3  Control 
<!--suppress CheckImageSize -->

## Lab Setup

Create a new Kotlin project like you did for the last lab. You will only need to work in a single file `Main.kt`

Alternatively, you can use the last project and just do each part in a new package

---

The purpose of the lab is to get you familiar with the Kotlin way of writing some control structures

## Part 1 - The if statement

Most people are accustomed to writing and if conditional like this:

```kotlin

fun oldCheckNumberSign(number: Int) :String {
    if (number > 0) {
        return "$number is positive"
    } else if (number < 0) {
        return "$number is negative"
    } else {
        return "$number is zero"
    }
}

fun main() {
    println(oldCheckNumberSign(-10))
    println(oldCheckNumberSign(10))
    println(oldCheckNumberSign(0))
  
}

```
```shell
-10 is negative
10 is positive
0 is zero

```

Rewrite the function so that it only executes on `return` statement which is the value returned by the conditional

Your solution should look something like this

```kotlin

fun checkNumberSign(number: Int) :String {
     return if (number > 0) "$number is positive"
     else if (number < 0) "$number is negative"
     else "$number is zero"
    }


fun main() {
    println(checkNumberSign(-10))
    println(checkNumberSign(10))
    println(checkNumberSign(0))

}
```
```shell
-10 is negative
10 is positive
0 is zero
```
---

## Part 2 - When block

Write a when block that converts a `Char` letter grade to a corresponding string like this.  Test on a `grade variable of type 'Char'

```text
'A' is "Excellent"
'B' is "Good"
'C' is "Average"
'D' is "Below Average"
'F' is "Fail"
Anything else is "Incomplete"
```

Your solution should look something like this:

```kotlin

ffun getGradeDescription(grade: Char): String {
    return when (grade) {
        'A' -> "Excellent"
        'B' -> "Good"
        'C' -> "Average"
        'D' -> "Below Average"
        'F' -> "Fail"
        else -> "Incomplete"
    }
}

fun main() {
    println("Grade A: ${getGradeDescription('A')}")
    println("Grade C: ${getGradeDescription('C')}")
    println("Grade E: ${getGradeDescription('E')}")
}
```
```shell
Grade A: Excellent
Grade C: Average
Grade E: Incomplete
```

Rewrite the function so that it doesn't test the `grade` variable but uses a logical test for each branch

Your solution should look something like this with the same output as before

```kotlin
fun getGradeDescription(grade: Char): String {
    return when {
        grade == 'A' -> "Excellent"
        grade == 'B' -> "Good"
        grade == 'C' -> "Average"
        grade == 'D' -> "Below Average"
        grade == 'F' -> "Fail"
        else -> "Invalid grade"
    }
}
fun main() {
    println("Grade A: ${getGradeDescription('A')}")
    println("Grade C: ${getGradeDescription('C')}")
    println("Grade E: ${getGradeDescription('E')}")
}
```

--- 

## part 3 - Loops and Stuff

This part of the lab assumes a basic familiarity with loops.

#### The labeled `break` statement


Write a function named `findNumberInMatrix()` that takes a 2D list (list of lists) of integers and a target number as parameters. The function should print the row and column index of the first occurrence of the target number using nested loops.

This is the starter code 

```kotlin
un findNumberInMatrix(matrix: List<List<Int>>, target: Int) {
    for (i in matrix.indices) {
        println("Checking row $i")
        for (j in matrix[i].indices) {
            println("Checking column $j")
            if (matrix[i][j] == target) {
                println("Found $target at row $i, column $j")
                break
            }
        }
    }
}

fun main() {
    val matrix = listOf(
        listOf(1, 2, 3),
        listOf(4, 5, 6),
        listOf(7, 8, 9)
    )

    findNumberInMatrix(matrix, 5)
   
}
```
Clearly we want to break out of the outer loop when we find the value, but that would not be possible with break as shown because that would just break out of the inner loop.
 
Running the code shows that after we find the value, we break out of the column loop

We can fix this by adding a label to the outer loop and a labeled break

```kotlin
fun findNumberInMatrix(matrix: List<List<Int>>, target: Int) {
    outerLoop@  for (i in matrix.indices) {
         println("Checking row $i")
        for (j in matrix[i].indices) {
            println("Checking column $j")
            if (matrix[i][j] == target) {
                println("Found $target at row $i, column $j")
                break@outerLoop

            }
        }
    }
}
```
Test the code to make sure it runs.


## End Lab