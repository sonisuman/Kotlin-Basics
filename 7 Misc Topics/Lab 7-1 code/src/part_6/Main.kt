package part_6

fun main() {
    val words = listOf("apple", "banana", "pear", "kiwi", "grape")

    // Map words to their lengths
    val wordLengths = words.associateWith { it.length }

    // Filter words with more than 3 letters
    val filteredWordLengths = wordLengths.filter { it.value > 3 }

    // Convert lengths to uppercase letters
    val lengthAsLetters = filteredWordLengths.mapValues { (_, length) -> ('A' + (length - 1)) }

    // Group words by their first letter
    val groupedByFirstLetter = words.groupBy { it.first() }

    println("Word lengths: $wordLengths")
    println("Filtered word lengths: $filteredWordLengths")
    println("Lengths as letters: $lengthAsLetters")
    println("Grouped by first letter: $groupedByFirstLetter")
}
