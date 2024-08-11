package demo_2_9

fun main() {
    val b: String? = "Kotlin"
    //if ( b.length > 0) {
        if ( b != null && b.length > 0) {
        print("String of length ${b.length}")
    } else {
        print("Empty string")
    }
}

