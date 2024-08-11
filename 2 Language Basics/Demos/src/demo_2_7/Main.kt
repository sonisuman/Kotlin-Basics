package demo_2_7

fun main() {
    val rv = test(5)
    println("Result: $rv")
}

fun test(value: Int): Int {
    return if (value > 0) {
        value
    } else {
        // This branch returns Nothing
        throwError("Value must be greater than zero")
    }
}

// Nothing is used her to document that nothing is returned ever from this function
// Note that this is different that UNIT which is a void type
// There real difference is that Nothing indicates that this function never returns using
// the usual call stack mechanism.
fun throwError(message: String): Nothing {
    throw IllegalArgumentException(message)
}