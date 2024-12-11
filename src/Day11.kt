fun main() {

    fun blink(arrangement: List<Long>): List<Long> {
        val newArrangement = mutableListOf<Long>()
        for (stone in arrangement) {
            if (stone == 0L) {
                newArrangement.add(1)
            } else if (stone.toString().length % 2 == 0) {
                val leftStone = stone.toString().substring(0, stone.toString().length / 2).toLong()
                val rightStone = stone.toString().substring(stone.toString().length / 2).toLong()
                newArrangement.add(leftStone)
                newArrangement.add(rightStone)
            } else {
                newArrangement.add(stone * 2024)
            }
        }
        return newArrangement
    }

    fun part1(input: List<String>): Int {
        var arrangement = input.first().extractNumbers("""\s""".toRegex()).map { it.toLong() }

        (1..25).forEach { i ->
            arrangement = blink(arrangement)
        }
        return arrangement.size
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day11_test")
    check(part1(testInput) == 55312)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day11")
    part1(input).println()
//    part2(input).println()
}
