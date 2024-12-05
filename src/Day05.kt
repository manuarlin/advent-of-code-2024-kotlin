fun main() {

    fun getOrderingRulesAndUpdates(input: List<String>): Pair<List<Pair<Int, Int>>, List<List<Int>>> {
        val indexOfEmptyLine = input.indexOfFirst { it.isBlank() }
        val orderingRules = input.subList(0, indexOfEmptyLine)
            .map { it.extractPairOfNumbers("|") }

        val updates = input.subList(indexOfEmptyLine + 1, input.size)
            .map { it.split(",").map { it.toInt() }.toList() }

        return orderingRules to updates
    }

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

    fun List<Int>.reorder(orderingRules: List<Pair<Int, Int>>): List<Int> {
        return toList().sortedWith { o1, o2 ->
            val applyingOrderingRules =
                orderingRules.firstOrNull { rule -> (rule.first == o1 && rule.second == o2) || (rule.first == o2 && rule.second == o1) }
            if (applyingOrderingRules == null) {
                0
            } else if (applyingOrderingRules.first == o1) {
                -1
            } else {
                1
            }
        }
    }

    fun List<Int>.middle(): Int = this[size / 2]

    fun part1(input: List<String>): Int {
        val (orderingRules, updates) = getOrderingRulesAndUpdates(input)

        return updates.filter { it.isUpdateCorrect(orderingRules) }
            .sumOf { it.middle() }
    }

    fun part2(input: List<String>): Int {
        val (orderingRules, updates) = getOrderingRulesAndUpdates(input)

        val reorderedUpdates = updates.filter { !it.isUpdateCorrect(orderingRules) }
            .map { it.reorder(orderingRules) }

        return reorderedUpdates.sumOf { it.middle() }
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
    check(
        part2(
            listOf(
                "47|75",
                " ",
                "75,47,61,53,29"
            )
        ) == 61
    )

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == 143)
    check(part2(testInput) == 123)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day05")
    part1(input).println()
    part2(input).println()
}
