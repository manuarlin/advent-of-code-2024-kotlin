fun main() {

    fun ignoreOutOfBoundException(process: () -> Unit) {
        try {
            process()
        } catch (_: IndexOutOfBoundsException) {
        }
    }

    fun countHorizontal(chars: Array<CharArray>): Int {
        var count = 0
        for (i in chars.indices) {
            for (j in chars[i].indices) {
                ignoreOutOfBoundException {
                    if (chars[i][j] == 'X'
                        && chars[i][j + 1] == 'M'
                        && chars[i][j + 2] == 'A'
                        && chars[i][j + 3] == 'S'
                    ) {
                        count++
                    }
                }
                ignoreOutOfBoundException {
                    if (chars[i][j] == 'X'
                        && chars[i][j - 1] == 'M'
                        && chars[i][j - 2] == 'A'
                        && chars[i][j - 3] == 'S'
                    ) {
                        count++
                    }
                }
            }
        }
        return count
    }

    fun countVertical(chars: Array<CharArray>): Int {
        var count = 0
        for (i in chars.indices) {
            for (j in chars[i].indices) {
                ignoreOutOfBoundException {
                    if (chars[i][j] == 'X'
                        && chars[i + 1][j] == 'M'
                        && chars[i + 2][j] == 'A'
                        && chars[i + 3][j] == 'S'
                    ) {
                        count++
                    }
                }
                ignoreOutOfBoundException {
                    if (chars[i][j] == 'X'
                        && chars[i - 1][j] == 'M'
                        && chars[i - 2][j] == 'A'
                        && chars[i - 3][j] == 'S'
                    ) {
                        count++
                    }
                }
            }
        }
        return count
    }

    fun countDiagonal(chars: Array<CharArray>): Int {
        var count = 0
        for (i in chars.indices) {
            for (j in chars[i].indices) {
                ignoreOutOfBoundException {
                    if (chars[i][j] == 'X'
                        && chars[i + 1][j + 1] == 'M'
                        && chars[i + 2][j + 2] == 'A'
                        && chars[i + 3][j + 3] == 'S'
                    ) {
                        count++
                    }
                }
                ignoreOutOfBoundException {
                    if (chars[i][j] == 'X'
                        && chars[i + 1][j - 1] == 'M'
                        && chars[i + 2][j - 2] == 'A'
                        && chars[i + 3][j - 3] == 'S'
                    ) {
                        count++
                    }
                }
                ignoreOutOfBoundException {
                    if (chars[i][j] == 'X'
                        && chars[i - 1][j - 1] == 'M'
                        && chars[i - 2][j - 2] == 'A'
                        && chars[i - 3][j - 3] == 'S'
                    ) {
                        count++
                    }
                }
                ignoreOutOfBoundException {
                    if (chars[i][j] == 'X'
                        && chars[i - 1][j + 1] == 'M'
                        && chars[i - 2][j + 2] == 'A'
                        && chars[i - 3][j + 3] == 'S'
                    ) {
                        count++
                    }
                }
            }
        }
        return count
    }

    fun countXmas(chars: Array<CharArray>): Int {
        var count = 0
        for (i in chars.indices) {
            for (j in chars[i].indices) {
                ignoreOutOfBoundException {
                    if (chars[i][j] == 'A'
                        && (chars[i - 1][j - 1] == 'M' && chars[i + 1][j + 1] == 'S'
                                || chars[i - 1][j - 1] == 'S' && chars[i + 1][j + 1] == 'M'
                                )
                        && (chars[i - 1][j + 1] == 'M' && chars[i + 1][j - 1] == 'S'
                                || chars[i - 1][j + 1] == 'S' && chars[i + 1][j - 1] == 'M'
                                )
                    ) {
                        count++
                    }
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
        val chars = input.toTypedArray().map { it.toCharArray() }.toTypedArray()
        return countXmas(chars)
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
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 18)
    check(part2(testInput) == 9)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}
