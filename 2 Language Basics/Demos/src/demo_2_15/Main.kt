package demo_2_15

fun main() {
    val p = listOf(2,3,5,7,11)
    val x = 45
    val result = when {
        x > 11 -> "The number is too big"
        isEven(x) -> "An even number"
        x in p -> "A prime"
        x == 9 -> "Just a nine"
        else -> "No Matches"
    }
  println(result)
}

fun isEven(x:Int) : Boolean = 0 == x % 2