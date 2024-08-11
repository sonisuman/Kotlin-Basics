package demo_3_1

// Abstract, final and open class

class Base {
    fun writeout() {
        println("This is the Base class")
    }
}

open class OpenBase {
    fun writeout() {
        println("This is the OpenBase class")
    }
}


// Subclassing "Base" is a compiler error
//class newBase : Base() {}
// Subclassing "OpenBase" is not
class NewBase : OpenBase() {}

//--------------------------------

abstract class AbstractClass {
    fun writeout() {
        println("This is the AbstractClass class")

    }
}

class ConcreteClass : AbstractClass() {}



fun main() {
    val base = Base()
    base.writeout()

    val openBase = OpenBase()
    openBase.writeout()

    val newBase = NewBase()
    println("NewBase")
    newBase.writeout()

    // This is a compiler error
    //val abstractClass = AbstractClass();

    val concreteClass = ConcreteClass();
    concreteClass.writeout()







}