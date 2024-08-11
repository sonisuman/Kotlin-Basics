package demo_2_8

fun main() {
    var name:String
    var nameOrNull:String?

    var age:Int
    var ageOrNull:Int?

    // these are errors
    //name = null
    //age = null

    name = "Bob"
    age = 32

    nameOrNull = "Ted"
    nameOrNull = null
    ageOrNull = 32
    ageOrNull = null

    println("Name is $name and age is $age")
    println("NullabeName is $nameOrNull and nullableAge is $ageOrNull")
    println("Yelled name is ${name.uppercase()}")
    // compile error
    //println("Yelled null is ${nameOrNull.uppercase()}")
}