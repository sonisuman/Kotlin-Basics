package demo_2_6

fun Any.printClassName() {
    println(this::class.simpleName)
}

fun main() {
    // Define an 'Any variable' and an 'Any?' variable
    var anyValue: Any


    // Assign it an Integer value and autocast to Integer
    anyValue = 10
    anyValue.printClassName()

    // Assign a string value and autocast to
    anyValue = "Hi there"
    anyValue.printClassName()

}