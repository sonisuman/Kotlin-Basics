package demo_2_13

fun processNames(names: Array<String> = emptyArray()) {
    if (names.isEmpty()) {
        println("No names provided")
    } else {
        names.forEach { println(it) }
    }
}

fun main() {
    processNames() // Prints: No names provided
    processNames(arrayOf("Alice", "Bob")) // Prints: Alice Bob
}
