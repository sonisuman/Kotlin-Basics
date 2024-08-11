package demo_2_11

fun main() {

    var s:String? = "Hi there"
    var elvis:String? = null

    println("The length of s is ${ s?.length ?: 0 }")
    println("The length of elvis is ${ elvis?.length ?: 0 }")
}