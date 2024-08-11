package demo_2_17

fun main() {
    println("Demo of break with label:")
    breakDemo()

    println("\nDemo of continue with label:")
    continueDemo()
}

fun breakDemo() {
    outerLoop@ for (i in 1..3) {
        for (j in 1..3) {
            println("i = $i, j = $j")
            if (j == 2) {
                println("Breaking the outer loop when j == 2")
                break@outerLoop // breaks the outer loop
            }
        }
    }
    println("Exited the outer loop.")
}

fun continueDemo() {
    outerLoop@ for (i in 1..3) {
        for (j in 1..3) {
            if (j == 2) {
                println("Continuing the outer loop when j == 2")
                continue@outerLoop // skips to the next iteration of the outer loop
            }
            println("i = $i, j = $j")
        }
    }
    println("Finished the loops.")
}
