# The Kotlin Language
<!--suppress CheckImageSize -->
<img src="../images/Logo.png" width="300" alt="">

## About Kotlin

[Reference](https://blog.jetbrains.com/kotlin/2016/02/kotlin-1-0-released-pragmatic-language-for-jvm-and-android/)

The development of Kotlin as a JVM language began in 2011 when Jetbrains decided that none of the existing languages had the features they required, except for `Scala` which had unacceptable compile times.

Kotlin was intended to be an "industrial strength object-oriented" language, which would be a Java/Scala hybrid that supported a pragmatic programming model and features such as:

- Support for modern hardware (similar to the motivation of Rob Pike and Ken Thompson to develop Go), especially the use of modern multi-threading architectures.
- Built in support for cross-platform development
- Backwards compatibility with existing Java code
- Incorporation of more modern programming language features characteristic of current compiler technology
- Less cluttered and easier to read syntax
- Multiple compilation targets: bytecode, native executables, and JavaScript
- The ability to share code between platforms from a single project.

[What does "pragmatic" mean](https://blog.jetbrains.com/kotlin/2016/02/kotlin-1-0-released-pragmatic-language-for-jvm-and-android/#what-does-pragmatic-mean?)

Kotlin can be thought of as "Java the Next Generation - Mobile" to rework a number of the 1990s design constraints that influenced how Java was implemented.

1. To operate in a mid 1990s cross-platform hardware environment.
2. To have a quick learning curve for C++ developers
3. To strictly follow the object-oriented programming model
4. Support for the current state of the 1990s compiler development
5. The absence of non-desktop computing environment

Java has evolved over the decades to provide support for multi-threading, functional programming, streams, and collections; but this has been done by adding to the existing language. The most important consideration when creating new versions of Java has always been to support backward compatibility so that existing Java applications do not break.

Because Kotlin is a new language, it has no backward compatibility issues that force certain language features to be included. 

Interoperability with Java means that we can use both Java and Kotlin code in the same project, but Kotlin and Java are compiled independently. There does not have to be source code compatibility between the two languages.

---

## Kotlin Platforms

One of the big changes since Java was created has been the emergence of mobile computing platforms like iOS and Android. Kotlin has been designed to support multiple implementation targets from a single codebase. This means that in addition to being interoperable with Java, Kotlin also needs to support a variety of platform native targets like mobile operating systems, embedded Linux, etc.

Kotlin code can be compiled to a number of target executables.

<sub>The Java Micro edition (JME) was originally supposed to be used on mobile platforms; however, it has become obsolete with the introduction of iOS and Android</sub>

### JVM

Kotlin can compile to Java bytecode to run on a JVM. By default, it compiles to Java 1.8 code for maximum backward compatibility, but it can compile to any JVM release. In our VMs, we will be generally compiling to Java 21 bytecode.

In this mode, Kotlin can easily call legacy Java code, and Java can call compatible Kotlin code.

There are some considerations to ensure that the [Kotlin calling Java integration works correctly](https://kotlinlang.org/docs/java-interop.html). As well as considerations to ensure [Java calling Kotlin works correctly](https://kotlinlang.org/docs/java-to-kotlin-interop.html)

The Kotlin compiler `kotlinc` can be used to generate standard Java library jar files.

### Native

Kotlin has a number of platform-specific compilers that compile Kotlin code directly to a native executable.

### JavaScript and WebAssembly

Kotlin also supports transpiling to JavaScript generating WASM, but these topics are optional in this course and may be covered in the multi-platform section if time allows.

---

## Some Language Basics

### Program Structure

To be able to create a hybrid Java/Kotlin project, the package and import system have to work basically the same in both languages.

In Kotlin, in addition to classes and interfaces, functions and variables can be defined at the top level of a package. Functions and variables do not need to be defined as members of a class.

The `main()` function and its command line variant `main(args: Array<String>)` is the program execution entry point and serves the same function as the `static void main()` function in Java. However, in Kotlin, it is a top level package function as opposed to a static member of a class.

A top level package variable or function can be referenced from any file in the package. The exceptions are those top level functions and variables that are marked `private`; they can only be seen in the file in which they are defined.

In the demo below, there are two files `Main.kt` and `Other.kt` in the same package work

First, consider the `Other.kt` file

```kotlin
package work
// Demo 1.1 Program Structure
// Other.kt file

// this is visible in all files in package work
val alternateGreeting = "Go Away!!!"

// this is visible ONLY in this file
private val rudeGreeting = "You must be a PHP programmer"

// This can access the rudeGreeting variable since it is in the same file
fun goAway() {
    println(rudeGreeting)
}
```

And the `Main.kt` file

```kotlin
package work
// Demo 1.1 Program Structure

// Main.kt file
// Note that the variable alternateGreeting is defined in another file
// Other.kt in the same package work

val greeting = "Hello World"

fun main() {
    println(greeting)  // top level variable in this file/package
    println(alternateGreeting) // top level variable in Other.kt
    // println(rudeGreeting) // not allowed: private top level variable in another file
    goAway()  // top level function in Other.kt
}
```
Which produces the output

```shell
Hello World
Go Away!!!
You must be a PHP programmer

Process finished with exit code 0

```
#### Implementation Details

Package-level functions and variables are implemented as static methods and fields in generated classes by the Kotlin compiler. This cleans up a lot of the syntactic clutter required when using static methods and fields in Java.

For example, if the class file generated from `Other.kt` and `Main.kt` were decompiled from Java bytecode they would look something like this

First the `Other.kt` file


```java
// Decompiled from OtherKt.class
package demo_1_1.work;

public final class OtherKt {
    public static final String alternateGreeting = "Go Away!!!";
    private static final String rudeGreeting = "You must be a PHP programmer";

    public static void goAway() {
        System.out.println( OtherKt.rudeGreeting);
    }
}
```
And `Main.kt`

```java
// Decompiled from MainKt.class
package demo_1_1.work;

public final class MainKt {
    public static final String greeting = "Hello World";

    public static void main(String[] args) {
        System.out.println(MainKt.reeting);
        System.out.println(OtherKt.getAlternateGreeting());
        OtherKt.goAway();
    }
}

```

Understanding how this underlying mechanism works will make learning a number of the other features of the language a lot more straightforward.

---
## Lab 1 - Set-up and Hello world
---



