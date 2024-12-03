private const val MUL_INSTRUCTION = """mul\((\d{1,3}),(\d{1,3})\)"""
private const val DO_INSTRUCTION = """do\(\)"""
private const val DONT_INSTRUCTION = """don't\(\)"""

fun main() {

    fun extractInstructionsPart1(section: String): List<String> {
        val regex = MUL_INSTRUCTION.toRegex()
        return regex.findAll(section).map { it.value }.toList()
    }

    fun extractInstructionsPart2(section: String): List<String> {
        val regex = "$MUL_INSTRUCTION|$DO_INSTRUCTION|$DONT_INSTRUCTION".toRegex()
        return regex.findAll(section).map { it.value }.toList()
    }

    fun computeMulInstruction(instruction: String): Int {
        val (first, second) = MUL_INSTRUCTION.toRegex()
            .find(instruction)!!
            .destructured
        return first.toInt() * second.toInt()
    }

    fun part1(input: List<String>): Int {
        val instructions = input.flatMap{extractInstructionsPart1(it)}
        return instructions.sumOf { computeMulInstruction(it) }
    }

    fun part2(input: List<String>): Int {
        val instructions = input.flatMap{extractInstructionsPart2(it)}
        var processingMul = true
        var total = 0

        for (instruction in instructions) {
            when  {
                instruction.matches(DO_INSTRUCTION.toRegex()) -> processingMul = true
                instruction.matches(DONT_INSTRUCTION.toRegex()) -> processingMul = false
                instruction.matches(MUL_INSTRUCTION.toRegex()) -> if (processingMul) {
                    total += computeMulInstruction(instruction)
                }
            }
        }
        return total
    }

    // Test if implementation meets criteria from the description, like:
    check(part1(listOf("xmul(2,4)%&mul[3,7]not_mul(5,5)")) == 33)
    check(part2(listOf("xmul(2,4)&mul[3,7]!^don't()_mul(5,5)undo()?mul(8,5)")) == 48)

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInputPart1 = readInput("Day03_part_1_test")
    check(part1(testInputPart1) == 161)
    val testInputPart2 = readInput("Day03_part_2_test")
    check(part2(testInputPart2) == 48)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}
