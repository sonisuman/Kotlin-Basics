package part_1

@JvmInline
value class Email(val email: String) {
    init {
        require(email.contains("@")) { "Invalid email address" }
    }

    override fun toString(): String = email
}

fun main() {
    val validEmail = Email("example@example.com")
    println("Valid Email: $validEmail")

    try {
        val invalidEmail = Email("example.com")
    } catch (e: IllegalArgumentException) {
        println("Caught an exception: ${e.message}")
    }
}
