
```kotlin
fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("No arguments provided!")
        return
    }

    println("Arguments provided:")
    for (arg in args) {
        println(arg)
    }
}
```
The snippets below assume that the code above is saved as `App.kt`

```shell
kotlinc App.kt -include-runtime -d App.jar

kotlinc App.kt  -d AppLib.jar

kotlinc-native App.kt -o App
```