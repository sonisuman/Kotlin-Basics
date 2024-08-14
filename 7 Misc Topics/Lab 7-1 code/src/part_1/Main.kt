package part_1

fun main() {
    // Immutable list of integers
    val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    // Mutable list of days of the week
    val daysOfWeek = mutableListOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")

    // Print the first and last elements of the numbers list
    println("First element: ${numbers.first()}, Last element: ${numbers.last()}")

    // Add and remove elements in the mutable list
    daysOfWeek.add("Funday")
    daysOfWeek.remove("Monday")

    // Sort and print the list
    daysOfWeek.sort()
    println("Sorted days of the week: $daysOfWeek")
}
