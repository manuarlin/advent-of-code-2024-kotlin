import kotlin.math.abs

fun main() {
    fun getOrderedTwoLists(input: List<String>): Pair<List<Int>, List<Int>> {
        val elements = input.map { it.extractNumbers("\\s+".toRegex()) }

        val orderedFirstList = elements.map { it[0] }.sorted()
        val orderedSecondList = elements.map { it[1] }.sorted()
        return Pair(orderedFirstList, orderedSecondList)
    }

    fun countSimilarities(number: Int, list: List<Int>): Int {
        val firstIndex = list.indexOf(number)

        return if (firstIndex == -1) {
            0
        } else {
            var count = 1
            var nextIndex = firstIndex + 1
            while (list[nextIndex] == number) {
                count++
                nextIndex++
            }
            count
        }

    }

    fun part1(input: List<String>): Int {
        val (orderedFirstList, orderedSecondList) = getOrderedTwoLists(input)

        return orderedSecondList
            .mapIndexed { index, e -> abs(e - orderedFirstList[index]) }
            .sum()
    }

    fun part2(input: List<String>): Int {
        val (orderedFirstList, orderedSecondList) = getOrderedTwoLists(input)

        return orderedFirstList
            .map { Pair(it, countSimilarities(it, orderedSecondList)) }
            .fold(0, { total, (first, second) -> total + first * second })
    }

    // Test if implementation meets criteria from the description, like:
    check(part1(listOf("2   5")) == 3)
    check(part1(listOf("5   2")) == 3)

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 11)
    check(part2(testInput) == 31)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
