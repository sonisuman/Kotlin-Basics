package demo_4_8

//  Builder Demo

// Sandwich class with basic properties
class Sandwich(
    var bread: String = "",
    var meat: String = "",
    var cheese: String = "",
    var vegetables: List<String> = emptyList()
) {
    override fun toString(): String {
        return "Sandwich(bread='$bread', meat='$meat', cheese='$cheese', vegetables=$vegetables)"
    }
}

// Builder function for creating and configuring a Sandwich
fun sandwich(builder: Sandwich.() -> Unit): Sandwich {
    val sandwich = Sandwich()
    sandwich.builder()
    return sandwich
}

fun main() {
    // Using the builder function to create a Sandwich
    val mySandwich = sandwich {
        bread = "Whole Grain"
        meat = "Turkey"
        cheese = "Cheddar"
        vegetables = listOf("Lettuce", "Tomato", "Onion")
    }

    println(mySandwich)
}
