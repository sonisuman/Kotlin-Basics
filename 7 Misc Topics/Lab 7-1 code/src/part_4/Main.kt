package part_4

fun main() {
    val setA = setOf(1, 2, 3, 4, 5)
    val setB = setOf(4, 5, 6, 7, 8)

    // Union
    val unionSet = setA.union(setB)
    println("Union: $unionSet")

    // Intersection
    val intersectionSet = setA.intersect(setB)
    println("Intersection: $intersectionSet")

    // Difference
    val differenceSet = setA.subtract(setB)
    println("Difference: $differenceSet")

    // Convert to list and sort
    val sortedUnionList = unionSet.toList().sorted()
    println("Sorted union list: $sortedUnionList")
}
