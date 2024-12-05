fun main() {

    fun List<Int>.isUpdateCorrect(orderingRules: List<Pair<Int, Int>>): Boolean {
        for (page in this) {
            val indexOfPage = this.indexOf(page)
            val applyingOrderingRules = orderingRules.filter { rule -> rule.first == page }
            for (rule in applyingOrderingRules) {
                val indexOfRulePage = this.indexOf(rule.second)
                if (indexOfRulePage == -1) {
                    continue
                }
                if (indexOfPage > indexOfRulePage) {
                    return false
                }
            }
        }
        return true
    }

    fun List<Int>.middle(): Int = this[size / 2]


    fun part1(input: List<String>): Int {
        val indexOfEmptyLine = input.indexOfFirst { it.isBlank() }
        val orderingRules = input.subList(0, indexOfEmptyLine)
            .map { it.extractPairOfNumbers("|") }

        val updates = input.subList(indexOfEmptyLine + 1, input.size)
            .map { it.split(",").map { it.toInt() }.toList() }
        return updates.filter { it.isUpdateCorrect(orderingRules) }
            .sumOf { it.middle() }
    }

    fun part2(input: List<String>): Int {
        TODO()
    }

    // Test if implementation meets criteria from the description, like:
    check(
        part1(
            listOf(
                "47|53",
                " ",
                "75,47,61,53,29"
            )
        ) == 61
    )

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == 143)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day05")
    part1(input).println()
//    part2(input).println()
}
