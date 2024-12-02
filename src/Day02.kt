import kotlin.math.abs

fun main() {

    fun checkIfReportIsSafe(report: List<Int>): Boolean {
        var isIncreasing = report[1] - report[0] > 0

        for (i in 0..report.size - 2) {
            var newDiff = report[i + 1] - report[i]
            if (newDiff > 0 && !isIncreasing || newDiff < 0 && isIncreasing) return false
            if (abs(newDiff) < 1 || abs(newDiff) > 3) return false
        }
        return true
    }

    fun part1(input: List<String>): Int {
        val reports = input.map { it.extractNumbers("\\s".toRegex()) }
        return reports.count { checkIfReportIsSafe(it) }

    }

    fun part2(input: List<String>): Int {
        return input.size

    }

    // Test if implementation meets criteria from the description, like:
    check(part1(listOf("7 6 4 2 1")) == 1)

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 2)
//    check(part2(testInput) == 31)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day02")
    part1(input).println()
//    part2(input).println()
}
