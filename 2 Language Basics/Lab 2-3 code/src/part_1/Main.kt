package part_1

fun oldCheckNumberSign(number: Int) :String {
    if (number > 0) {
        return "$number is positive"
    } else if (number < 0) {
        return "$number is negative"
    } else {
        return "$number is zero"
    }
}

fun checkNumberSign(number: Int) :String {
     return if (number > 0) "$number is positive"
     else if (number < 0) "$number is negative"
     else "$number is zero"
    }


fun main() {
    println(checkNumberSign(-10))
    println(checkNumberSign(10))
    println(checkNumberSign(0))

}