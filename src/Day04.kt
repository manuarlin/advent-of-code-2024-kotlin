fun main() {

    fun countHorizontal(chars: Array<CharArray>): Int {
        var count = 0
        for (i in chars.indices) {
            for (j in chars[i].indices) {
                try {
                    if (chars[i][j] == 'X'
                        && chars[i][j + 1] == 'M'
                        && chars[i][j + 2] == 'A'
                        && chars[i][j + 3] == 'S'
                    ) {
                        count++
                    }
                } catch (_: IndexOutOfBoundsException) {
                }
                try {
                    if (chars[i][j] == 'X'
                        && chars[i][j - 1] == 'M'
                        && chars[i][j - 2] == 'A'
                        && chars[i][j - 3] == 'S'
                    ) {
                        count++
                    }
                } catch (_: IndexOutOfBoundsException) {
                }
            }
        }
        return count
    }

    fun countVertical(chars: Array<CharArray>): Int {
        var count = 0
        for (i in chars.indices) {
            for (j in chars[i].indices) {
                try {
                    if (chars[i][j] == 'X'
                        && chars[i + 1][j] == 'M'
                        && chars[i + 2][j] == 'A'
                        && chars[i + 3][j] == 'S'
                    ) {
                        count++
                    }
                } catch (_: IndexOutOfBoundsException) {
                }

                try {
                    if (chars[i][j] == 'X'
                        && chars[i - 1][j] == 'M'
                        && chars[i - 2][j] == 'A'
                        && chars[i - 3][j] == 'S'
                    ) {
                        count++
                    }
                } catch (_: IndexOutOfBoundsException) {
                }
            }
        }
        return count
    }

    fun countDiagonal(chars: Array<CharArray>): Int {
        var count = 0
        for (i in chars.indices) {
            for (j in chars[i].indices) {
                try {
                    if (chars[i][j] == 'X'
                        && chars[i + 1][j + 1] == 'M'
                        && chars[i + 2][j + 2] == 'A'
                        && chars[i + 3][j + 3] == 'S'
                    ) {
                        count++
                    }
                } catch (_: IndexOutOfBoundsException) {
                }
                try {
                    if (chars[i][j] == 'X'
                        && chars[i + 1][j - 1] == 'M'
                        && chars[i + 2][j - 2] == 'A'
                        && chars[i + 3][j - 3] == 'S'
                    ) {
                        count++
                    }
                } catch (_: IndexOutOfBoundsException) {

                }
                try {
                    if (chars[i][j] == 'X'
                        && chars[i - 1][j - 1] == 'M'
                        && chars[i - 2][j - 2] == 'A'
                        && chars[i - 3][j - 3] == 'S'
                    ) {
                        count++
                    }
                } catch (_: IndexOutOfBoundsException) {

                }
                try {
                    if (chars[i][j] == 'X'
                        && chars[i - 1][j + 1] == 'M'
                        && chars[i - 2][j + 2] == 'A'
                        && chars[i - 3][j + 3] == 'S'
                    ) {
                        count++
                    }
                } catch (_: IndexOutOfBoundsException) {

                }
            }
        }
        return count
    }

    fun part1(input: List<String>): Int {
        val chars = input.toTypedArray().map { it.toCharArray() }.toTypedArray()
        return countHorizontal(chars) + countVertical(chars) + countDiagonal(chars)
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // Test if implementation meets criteria from the description, like:
    check(
        part1(
            listOf(
                "..X...",
                ".SAMX.",
                ".A..A.",
                "XMAS.S",
                ".X...."
            )
        ) == 4
    )

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInputPart1 = readInput("Day04_test")
    check(part1(testInputPart1) == 18)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day04")
    part1(input).println()
//    part2(input).println()
}
