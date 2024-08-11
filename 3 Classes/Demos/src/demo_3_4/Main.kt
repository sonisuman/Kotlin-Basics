package demo_3_4

// Demo 3.4

class Person(name:String) {
    var myName: String

    init{
       this.myName = name.uppercase()
    }
}

class Person2(name:String) {
    val myName =  name.uppercase()

}

fun main() {
    val bob = Person("Robert")
    val ted = Person2("Theodore")
    println("bob's name is ${bob.myName}")
    println("ted's name is ${ted.myName}")
    // compile error since
   // println("bob's name is ${bob.name}")
}