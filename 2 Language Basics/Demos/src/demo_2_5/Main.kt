package demo_2_5

fun main() {
    // Define an 'Any variable' and an 'Any?' variable
    var anyValue: Any
    val nullableAny: Any?

    // Assign it an Integer value and autocast to Integer
    anyValue = 10
    println("The type of anyValue is: ${anyValue::class} with value $anyValue")

    // Assign a string value and autocast to
    anyValue = "Hi there"
    println("The type of anyValue is: ${anyValue::class} with value $anyValue")

    //anyValue = null;  // This is a compile error
    nullableAny = null
    println("The value of nullableAny is: $nullableAny")

    // This is not allowed since the class method cannot be called on a potentially null object
    //println("The type of anyValue is: ${nullableAny::class} with value nullableAny")
}