package part_3

fun main() {

    //val intVal:Int = 43L;
    //val floatVal:Float = 34.4
    //val doubleVal:Double = 43.0F
    //val stringVal:String = 'A'
    //val charVal:Char = "A"
    /*
    val longVal:Long = 43;
    val intVal:Int = 43
    val doubleVal:Double = 43.0
    val floatVal:Float = 34.4F
    val charVal:Char = 'A'
    val stringVal:String = "A"*/


    val intNumber: Int = 42
    val longNumber: Long = intNumber.toLong()  // Int to Long
    val doubleNumber: Double = intNumber.toDouble()  // Int to Double
    val floatNumber: Float = doubleNumber.toFloat()  // Double to Float

    val stringNumber: String = intNumber.toString()  // Int to String
    val intFromString: Int = stringNumber.toInt()  // String to Int

    val charValue: Char = 'A'
    val intFromChar: Int = charValue.code  // Char to Int
    val charFromInt: Char = intFromChar.toChar()  // Int to Char

    println("Long: $longNumber")
    println("Double: $doubleNumber")
    println("Float: $floatNumber")
    println("String: $stringNumber")
    println("Int from String: $intFromString")
    println("Int from Char: $intFromChar")
    println("Char from Int: $charFromInt")
}


