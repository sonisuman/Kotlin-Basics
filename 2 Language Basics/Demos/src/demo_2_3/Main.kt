package demo_2_3

fun main() {


    val mutableList = mutableListOf(1, 2, 3)  // A mutable list
    val immutableList = listOf(10, 20, 30)  // An immutable list

    // Both of the variables are immutable so this is an error
    // mutableList = mutableListOf(0, 0, 0)

    // Modifying the list
    mutableList.add(4)  // Adds 4 to the list
    mutableList[0] = 10  // Updates the first element to 10
    mutableList.removeAt(1)  // Removes the element at index 1
    println(mutableList)

    // But this won't work
    // immutableList.add(4)
    // immutableList[0] = 10

}