class Rectangle(initialHeight: Int, initialWidth: Int) {

    private var stuff = 0
    var height: Int = initialHeight
        get() {
            println("Getting height: $field")
            return field
        }
        set(value) {
            println("Setting height to $value")
            field = if (value > 0) value else throw IllegalArgumentException("Height must be greater than 0")
        }

    var width: Int = initialWidth
        get() {
            println("Getting width: $field")
            return field
        }
        set(value) {
            println("Setting width to $value")
            field = if (value > 0) value else throw IllegalArgumentException("Width must be greater than 0")
        }
}

fun main() {
    // Creating an instance of Rectangle
    val rectangle = Rectangle(10, 20)

    // Accessing properties (triggers custom getter)
    println("Height: ${rectangle.height}")  // Output: Getting height: 10
    //         Height: 10

    println("Width: ${rectangle.width}")    // Output: Getting width: 20
    //         Width: 20

    // Modifying properties (triggers custom setter)
    rectangle.height = 15                  // Output: Setting height to 15
    rectangle.width = 25                   // Output: Setting width to 25

    println("New Height: ${rectangle.height}")  // Output: Getting height: 15
    //         New Height: 15

    println("New Width: ${rectangle.width}")    // Output: Getting width: 25
    //         New Width: 25
}
