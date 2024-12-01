import kotlin.math.abs

fun main() {
    fun getTwoLists(input: List<String>): Pair<List<Int>, List<Int>> {
        val elements = input.map { it.split("[\\s]+".toRegex()) }
            .map { Pair(it.get(0).toInt(), it.get(1).toInt()) }

        val orderedFirstList = elements.map { it.first }.sorted()
        val orderedSecondList = elements.map { it.second }.sorted()
        return Pair(orderedFirstList, orderedSecondList)
    }

    fun part1(input: List<String>): Int {
        val (orderedFirstList, orderedSecondList) = getTwoLists(input)

        val diffList = mutableListOf<Int>()

        for (i in 0..<orderedSecondList.size) {
            diffList.add(abs(orderedSecondList.get(i) - orderedFirstList.get(i)))
        }

        return diffList.sum()
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // Test if implementation meets criteria from the description, like:
    check(part1(listOf("2   5")) == 3)
    check(part1(listOf("5   2")) == 3)

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 11)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
