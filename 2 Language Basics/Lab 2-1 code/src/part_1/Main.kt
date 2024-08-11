package part_1

fun Any.getClassName() :String?  {
   return(this::class.simpleName)
}

fun main() {

    println("This is a string".getClassName())
    println(true.getClassName())
    println(34.7.getClassName())
    println(null?.getClassName())
    println(Cat().getClassName())
    println(Any().getClassName())
    println(Unit.getClassName())

}

class Cat