package part_3

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
    val email1 = Email("example@example.com")
    val email2 = Email("example@example.com")

    println("Email1 equals Email2: ${email1 == email2}")
    //println("Email1 same object as Email2: ${email1 === email2}")
}