package demo_2_18

fun main() {
    println("Demo of labeled return:")
    findNumber(listOf(1, 2, 3, 4, 5))

}

fun findNumber(numbers: List<Int>) {
    numbers.forEach numberLoop@{
        if (it == 4) {
            println("Found number 4, returning from lambda using labeled return.")
            return@numberLoop // returns from the lambda, not the enclosing function
            //println("Found number 4, returning from function.")
            //return
        }
        println("Processing number $it")
    }
    println("Finished processing all numbers.")
}
