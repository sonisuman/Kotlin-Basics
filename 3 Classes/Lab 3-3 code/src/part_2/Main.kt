package part_2

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
