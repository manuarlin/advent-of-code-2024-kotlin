import kotlin.math.abs

fun main() {

    fun checkIfReportIsSafe(report: List<Int>): Boolean {
        var isIncreasing = report[1] - report[0] > 0

        for (i in 0..report.size - 2) {
            var levelDiff = report[i + 1] - report[i]
            if (
                (levelDiff > 0 && !isIncreasing || levelDiff < 0 && isIncreasing)
                || (abs(levelDiff) < 1 || abs(levelDiff) > 3)
            ) return false
        }
        return true
    }

    fun checkIfReportIsSafeWithDampener(report: List<Int>): Boolean {
        if (checkIfReportIsSafe(report)) {
            return true
        }

        for (i in report.indices) {
            val reportWithoutLevel = report.toMutableList()
            reportWithoutLevel.removeAt(i)
            if (checkIfReportIsSafe(reportWithoutLevel)) {
                return true
            }
        }
        return false
    }

    fun part1(input: List<String>): Int {
        val reports = input.map { it.extractNumbers("\\s".toRegex()) }
        return reports.count { checkIfReportIsSafe(it) }

    }

    fun part2(input: List<String>): Int {
        val reports = input.map { it.extractNumbers("\\s".toRegex()) }
        return reports.count { checkIfReportIsSafeWithDampener(it) }
    }

    // Test if implementation meets criteria from the description, like:
    check(part1(listOf("7 6 4 2 1")) == 1)
    check(part2(listOf("8 6 4 4 1")) == 1)

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
