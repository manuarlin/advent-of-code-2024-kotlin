fun main() {

    fun getMap(input: List<String>): PuzzleMap {
        val map: PuzzleMap = mutableMapOf()
        for ((i, line) in input.withIndex()) {
            for ((j, symbol) in line.withIndex()) {
                map.put(Pair(i, j), symbol)
            }
        }
        return map
    }

    fun getAllPositions(map: PuzzleMap, maxX: Int, maxY: Int): Positions {
        val allPositions: Positions = mutableListOf()

        var currentMovingWay = MovingWay.UP
        var currentPosition = map.filterValues { it == '^' }.keys.first()

        while (currentPosition.first >= 0 && currentPosition.second >= 0
            && currentPosition.first <= maxX && currentPosition.second <= maxY
        ) {
            allPositions.add(currentPosition)
            val nextPotentialPosition = when (currentMovingWay) {
                MovingWay.UP -> Pair(currentPosition.first - 1, currentPosition.second)
                MovingWay.RIGHT -> Pair(currentPosition.first, currentPosition.second + 1)
                MovingWay.DOWN -> Pair(currentPosition.first + 1, currentPosition.second)
                MovingWay.LEFT -> Pair(currentPosition.first, currentPosition.second - 1)
            }
            val symbol = map.getOrDefault(nextPotentialPosition, null)

            if (symbol != null && symbol == '#') {
                currentMovingWay = currentMovingWay.turnOnRight()
            } else {
                currentPosition = nextPotentialPosition
            }
        }
        return allPositions
    }

    fun part1(input: List<String>): Int {
        val map = getMap(input)
        val maxX = input.size - 1
        val maxY = input[0].length - 1
        return getAllPositions(map, maxX, maxY).distinct().size
    }

    fun part2(input: List<String>): Int {
        TODO()
    }


    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day06_test")
    check(part1(testInput) == 41)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day06")
    part1(input).println()
//    part2(input).println()
}

enum class MovingWay {
    UP, RIGHT, DOWN, LEFT;

    fun turnOnRight(): MovingWay = when (this) {
        UP -> RIGHT
        RIGHT -> DOWN
        DOWN -> LEFT
        LEFT -> UP
    }
}

typealias Position = Pair<Int, Int>
typealias PuzzleMap = MutableMap<Position, Char>
typealias Positions = MutableList<Position>
