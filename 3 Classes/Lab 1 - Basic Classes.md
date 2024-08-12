# Lab 3-1  Basic Classes
<!--suppress CheckImageSize -->

## Lab Setup

Create a new Kotlin project like you did for the last lab. You will only need to work in a single file `Main.kt`

Alternatively, you can use the last project and just do each part in a new package

---

## Part 1 - Defining a class

Define a class named `Student` with the following properties:
- _firstName_: A String representing the student's first name.
- _lastName:_ A String representing the student's last name.
- _age:_ An Int representing the student's age.
- _grade:_ A String representing the student's grade.

As part of the definition, use a primary constructor that initializes all the properties in the constructor

Add a method named `getFullName()` to the `Student` class that returns the full name of the student (i.e., `firstName` `lastName`).

Now create and instance of the class and print out the full name

A possible solution is shown below 

```kotlin
package part_1

class Student(val firstName: String, val lastName: String, var age: Int, var grade: String) {
    fun getFullName(): String {
        return "$firstName $lastName"
    }
}


fun main() {
    val student = Student("John", "Doe", 20, "A")

    println("Student: ${student.firstName} ${student.lastName}, Age: ${student.age}, Grade: ${student.grade}")

    println(student.getFullName())

}
```

## Part 2 - Adding in Validation

As it stands, the class has no initialization validation

Rewrite the constructor to not define properties directly but instead provide values to be assigned in directly to explicit declarations of the properties.

We have been told that we have to enforce the following validity rules:

1. first name, last name and age might be `null` (old database system)
2. first name and last name might be an empty string
3. the age might be < 0 

We have to change the types of data in the constructor like this

```kotlin
class Student(first: String?, last: String?, var a: Int?, var g: String)
```
These are the fixups we have to do.

1. If first name is null or "", we convert it to "FNU" for "first name unknown"
2. We do the same with the second name but use "LNU"
3. Ages below 0 or are replaced with `null`
4. Grades that are not one of "A", "B", "C", "D", "F" are replaced with NA

Use an `init{}` block to do the tests like this:

```kotlin
class Student(first: String?, last: String?, var a: Int?, var g: String) {
    val firstName:String
    
    init{
        this.firstName = if (first.isNullOrEmpty()) "FNU" else first
        // you do the rest
    }
}

```
Run your result with the following data and you should get this result

```kotlin
fun main() {
    var student = Student("John", "Doe", 20, "A")
    println("Student: ${student.firstName} ${student.lastName}, Age: ${student.age}, Grade: ${student.grade}")
    student = Student("", "Doe", -1, "5")
    println("Student: ${student.firstName} ${student.lastName}, Age: ${student.age}, Grade: ${student.grade}")
    student = Student("", null, null, "a")
    println("Student: ${student.firstName} ${student.lastName}, Age: ${student.age}, Grade: ${student.grade}")

}
```

One possible solution is this:

```kotlin
package part_2


class Student(first: String?, last: String?, var a: Int?, var g: String) {
    val firstName: String
    val lastName: String
    val age: Int?
    val grade : String

    init {
        this.firstName = if (first.isNullOrEmpty()) "FNU" else first
        this.lastName = if (last.isNullOrEmpty()) "LNU" else last
        this.age = if ( a != null && a!! > 0) a else null
        this.grade = if (g.uppercase() !in listOf("A","B","C","D","F")) "NA" else g.uppercase()
    }

    fun getFullName(): String {
        return "$firstName $lastName"
    }
}
```
---

## Part 3 - Secondary constructor and setters

Convert the `getFullName()` method to a read only property like so

```kotlin
 val fullName: String
        get() = "$firstName $lastName"
```

Next, make the all the properties, except `grade` mutable so they can be changed by methods in the class, but are read-only outside the class.

```kotlin
var firstName: String
  private set

```

Then write a custom `set()` method for grade that allows only the values you tested for in the previous section. Note that you are replicating logic so refactor out the logic into a private member function like this

```kotlin
 private fun validateGrade(g:String): String {
       return  if (g.uppercase() !in listOf("A","B","C","D","F")) "NA" else g.uppercase()
    }
```
 
Then use this is the `set` function and the initialization

```kotlin
 var grade: String = this.validateGrade(g)
        set(value) {
           field = this.validateGrade(value)
        }
```

Finally, add a secondary constructor that takes just the first and last name, and sets the age to 'null' and the grade to `"NA"`

```kotlin
 constructor(firstName: String, lastName: String) : this(firstName, lastName, null, "NA")
```

The final solution should look something like this

```kotlin
class Student(first: String?, last: String?, var a: Int?, var g: String) {
    var firstName: String
        private set
    var lastName: String
        private set
    var age: Int?
        private set
    var grade: String =this.validateGrade(g)
        set(value) {
            field = this.validateGrade(value)
        }

    constructor(firstName: String, lastName: String) : this(firstName, lastName, null, "NA")

    private fun validateGrade(g:String): String {
       return  if (g.uppercase() !in listOf("A","B","C","D","F")) "NA" else g.uppercase()
    }

    init {
        this.firstName = if (first.isNullOrEmpty()) "FNU" else first
        this.lastName = if (last.isNullOrEmpty()) "LNU" else last
        this.age = if (a != null && a!! > 0) a else null
    }

    fun getFullName(): String {
        return "$firstName $lastName"
    }
}

// runs some tests
fun main() {
    var student = Student("John", "Doe", 20, "A")
    println("Student: ${student.firstName} ${student.lastName}, Age: ${student.age}, Grade: ${student.grade}")
    student.grade = "B"
    println("Student: ${student.firstName} ${student.lastName}, Age: ${student.age}, Grade: ${student.grade}")
    student = Student("Jane", "Doe")
    println("Student: ${student.firstName} ${student.lastName}, Age: ${student.age}, Grade: ${student.grade}")

}
```

````shell
Student: John Doe, Age: 20, Grade: A
Student: John Doe, Age: 20, Grade: B
Student: Jane Doe, Age: null, Grade: NA
````
---

## End Lab