fun main() {

    fun extractInstructions(section: String): List<String> {
        val regex = """mul\(\d{1,3},\d{1,3}\)""".toRegex()
        return regex.findAll(section).map { it.value }.toList()
    }

    fun computeInstruction(instruction: String): Int {
        val (first, second) = """mul\((\d{1,3}),(\d{1,3})\)""".toRegex()
            .find(instruction)!!
            .destructured
        return first.toInt() * second.toInt()
    }

    fun part1(input: List<String>): Int {
        val instructions = input.flatMap{extractInstructions(it)}
        return instructions.sumOf { computeInstruction(it) }
    }

    fun part2(input: List<String>): Int {
        return 0

    }

    // Test if implementation meets criteria from the description, like:
    check(part1(listOf("xmul(2,4)%&mul[3,7]not_mul(5,5)")) == 33)

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 161)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}
