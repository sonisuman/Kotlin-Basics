# Lab 3-3 Value Classes
<!--suppress CheckImageSize -->

## Lab Setup

Create a new Kotlin project like you did for the last lab. You will only need to work in a single file `Main.kt`

Alternatively, you can use the last project and just do each part in a new package

---

## Part 1 - Defining the class

Value classes allow you to define lightweight wrappers around a single value. Value classes can be used to add type safety and encapsulation without the overhead of traditional classes. 

Define a value class named `Email` that wraps a String. The value class should validate the email format during initialization.

The `@JvmInline` annotation is used to indicate that this is a value class.

The Email value class wraps a String and enforces that the string contains an "@" symbol to ensure it's a valid email address.

The init block performs the validation during the creation of an Email object.

Note the use of the `require()` method to throw an exception

```kotlin

@JvmInline
value class Email(val email: String) {
    init {
        require(email.contains("@")) { "Invalid email address" }
    }

    override fun toString(): String = email
}
```

Now create a valid and an invalid instance of an Email object

```kotlin
fun main() {
    val validEmail = Email("example@example.com")
    println("Valid Email: $validEmail")

    try {
        val invalidEmail = Email("example.com")
    } catch (e: IllegalArgumentException) {
        println("Caught an exception: ${e.message}")
    }
}

```
```shell
Valid Email: example@example.com
Caught an exception: Invalid email address
```

---

## Part 2 - Add functionality

Extend the Email value class by adding a method getDomain() that returns the domain part of the email (the part after the "@" symbol).

```kotlin
@JvmInline
value class Email(val email: String) {
    init {
        require(email.contains("@")) { "Invalid email address" }
    }

    override fun toString(): String = email

    fun getDomain(): String {
        return email.substringAfter("@")
    }
}
fun main() {
    val email = Email("example@example.com")
    println("Email: $email")
    println("Domain: ${email.getDomain()}")
}

```
```shell
Email: example@example.com
Domain: example.com
```

---

## Part 3 - Value Class Equality and Copying

Without making any changes to the class definition, run the following code. Then uncomment out the structural equality line and note the error

Explain what this error means.

```kotlin
fun main() {
    val email1 = Email("example@example.com")
    val email2 = Email("example@example.com")

    println("Email1 equals Email2: ${email1 == email2}")
    //println("Email1 same object as Email2: ${email1 === email2}")
}
```
```shell
Email1 equals Email2: true
```

Remember that the class is a value class which means that is a lightweight wrappers around a single value and is optimized for performance. Value classes are generally immutable, meaning their underlying value cannot be changed after they are created.

the underlying value is often stored directly rather than as a reference to an object in memory. This means that even if you create two instances of a value class with the same value, they might not refer to the same object in memory, making referential equality (===) less meaningful.

To allow structural equality, remove the annotation at the top of the class and the keyword `value`, the run the code

```kotlin
//@JvmInline value
 class Email(val email: String) {
    init {
        require(email.contains("@")) { "Invalid email address" }
    }

    override fun toString(): String = email

    fun getDomain(): String {
        return email.substringAfter("@")
    }
}

fun main() {
    val email1 = Email("example@example.com")
    val email2 = Email("example@example.com")

    println("Email1 equals Email2: ${email1 == email2}")
    println("Email1 same object as Email2: ${email1 === email2}")
}
````
```shell
Email1 equals Email2: false
Email1 same object as Email2: false
```
 But now both inequalities are false. Can you explain why?

---

## End Lab