# Lab 3-2 Inheritance
<!--suppress CheckImageSize -->

## Lab Setup

Create a new Kotlin project like you did for the last lab. You will only need to work in a single file `Main.kt`

Alternatively, you can use the last project and just do each part in a new package

---

## Part 1 - Defining the classes

Define a class named `Person` with the following properties:
- _firstName_: A String representing the person's first name.
- _lastName_: A String representing the person's last name.
- _age_: An Int representing the person's age.

Add a method named `introduce()` that prints a basic introduction using the person's name and age.

```kotlin
open class Person(val firstName: String, val lastName: String, val age: Int) {

    open fun introduce() {
        println("Hi, I'm $firstName $lastName, and I'm $age years old.")
    }
}

```

In the same file, define a class named `Student` that inherits from `Person`.

Add a new property to the Student class:
- _studentId:_ A String representing the student's ID.

Override the `introduce()` method to include the student's ID in the introduction.

```kotlin
class Student(firstName: String, lastName: String, age: Int, val studentId: String)
    : Person(firstName, lastName, age) {

    override fun introduce() {
        println("Hi, I'm $firstName $lastName, I'm $age years old, and my student ID is $studentId.")
    }
}
```
Run a couple of test cases 

Your solution should look something like this:

```kotlin
pen class Person(val firstName: String, val lastName: String, val age: Int) {

    open fun introduce() {
        println("Hi, I'm $firstName $lastName, and I'm $age years old.")
    }
}

class Student(firstName: String, lastName: String, age: Int, val studentId: String)
    : Person(firstName, lastName, age) {

    override fun introduce() {
        println("Hi, I'm $firstName $lastName, I'm $age years old, and my student ID is $studentId.")
    }
}

fun main() {
    val person = Person("John", "Doe", 45)
    val student = Student("Jane", "Smith", 20, "S12345")

    person.introduce()
    student.introduce()
}
```
```shell
Hi, I'm John Doe, and I'm 45 years old.
Hi, I'm Jane Smith, I'm 20 years old, and my student ID is S12345.
```

--- 

## Part 2 - Interface

Now define in the same file a `Teacher` interface that looks like this

```kotlin
interface Teacher {
    val subject: String

    fun teach() {
        println("I teach $subject.")
    }
}
```
Notice that the interface cannot have state, so the `subject` property will have to be implemented by a class that implements the interface.

Now create a `TA` class that is both a `Student` and `Teacher`

```kotlin

class TA(firstName: String, lastName: String, age: Int, override val subject: String) 
    : Person(firstName, lastName, age), Teacher {

    override fun introduce() {
        println("Hello, I'm $firstName $lastName, I'm $age years old, and I teach $subject.")
    }
}

``` 

Your final solution should look something like this:

```kotlin
package part_2

open class Person(val firstName: String, val lastName: String, val age: Int) {

    open fun introduce() {
        println("Hi, I'm $firstName $lastName, and I'm $age years old.")
    }
}

class Student(firstName: String, lastName: String, age: Int, val studentId: String)
    : Person(firstName, lastName, age) {

    override fun introduce() {
        println("Hi, I'm $firstName $lastName, I'm $age years old, and my student ID is $studentId.")
    }
}


interface Teacher {
    val subject: String

    fun teach() {
        println("I teach $subject.")
    }
}

class TA(firstName: String, lastName: String, age: Int, override val subject: String)
    : Person(firstName, lastName, age), Teacher {

    override fun introduce() {
        println("Hello, I'm $firstName $lastName, I'm $age years old, and I teach $subject.")
    }
}



fun main() {
    val person = Person("John", "Doe", 45)
    val student = Student("Jane", "Smith", 20, "S12345")
    val gradStudent = TA("Emily", "Johnson", 35, "Mathematics")
    person.introduce()
    student.introduce()
    gradStudent.introduce()
    gradStudent.teach()

}

```
```shell
Hi, I'm John Doe, and I'm 45 years old.
Hi, I'm Jane Smith, I'm 20 years old, and my student ID is S12345.
Hello, I'm Emily Johnson, I'm 35 years old, and I teach Mathematics.
I teach Mathematics.
```
---

## Part 3 - Extension function

Add an extension function `isAdult()` for the `Person` class that returns true if the person is over 18 and false otherwise.

Notice that it is inherited by all the subclasses of `Person`

```kotlin
fun Person.isAdult(): Boolean {
    return this.age >= 18
}
```

Your final solution should look something like this

```kotlin
package part_3

open class Person(val firstName: String, val lastName: String, val age: Int) {

    open fun introduce() {
        println("Hi, I'm $firstName $lastName, and I'm $age years old.")
    }
}

class Student(firstName: String, lastName: String, age: Int, val studentId: String)
    : Person(firstName, lastName, age) {

    override fun introduce() {
        println("Hi, I'm $firstName $lastName, I'm $age years old, and my student ID is $studentId.")
    }
}


interface Teacher {
    val subject: String

    fun teach() {
        println("I teach $subject.")
    }
}

class TA(firstName: String, lastName: String, age: Int, override val subject: String)
    : Person(firstName, lastName, age), Teacher {

    override fun introduce() {
        println("Hello, I'm $firstName $lastName, I'm $age years old, and I teach $subject.")
    }
}

fun Person.isAdult(): Boolean {
    return this.age >= 18
}



fun main() {
    val john = Person("John", "Doe", 19)
    val jane = Student("Jane", "Smith", 17, "S12345")
    val emily = TA("Emily", "Johnson", 35, "Mathematics")
    john.introduce()
    println("John is an adult is ${john.isAdult()}")
    jane.introduce()
    println("Jane is an adult is ${jane.isAdult()}")

}

```
```shell
Hi, I'm John Doe, and I'm 19 years old.
John is an adult is true
Hi, I'm Jane Smith, I'm 17 years old, and my student ID is S12345.
Jane is an adult is false
```

## End Lab