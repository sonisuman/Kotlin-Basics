package part_2

fun Any.getClassName() :String?  {
    return(this::class.simpleName)
}

fun main() {

    var anything:Any
    anything = "Hi there"
    println("$anything has type ${anything.getClassName()}")
    println("${anything.uppercase()}")

    anything = 34.7
    println("$anything has type ${anything.getClassName()}")
    //println("${anything.uppercase()}")


    var x = "Hi There"
    println("$x has type ${x.getClassName()}")

    // this is an error
    //x = 45



}