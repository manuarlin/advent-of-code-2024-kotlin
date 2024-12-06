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
            val nextPotentialPosition = currentPosition.next(currentMovingWay)
            val symbol = map.getOrDefault(nextPotentialPosition, null)

            if (symbol != null && symbol == '#') {
                currentMovingWay = currentMovingWay.turnOnRight()
            } else {
                currentPosition = nextPotentialPosition
            }
        }
        return allPositions
    }

    fun willGardBeStuck(map: PuzzleMap, maxX: Int, maxY: Int, newObstacle: Position): Boolean {
        val allPositionsWithMovingWay = mutableListOf<PositionWithMovingWay>()

        var currentMovingWay = MovingWay.UP
        var currentPosition = map.filterValues { it == '^' }.keys.first()

        while (currentPosition.first >= 0 && currentPosition.second >= 0
            && currentPosition.first <= maxX && currentPosition.second <= maxY
        ) {
            if (allPositionsWithMovingWay.contains(Pair(currentPosition, currentMovingWay))) {
                return true
            }
            allPositionsWithMovingWay.add(Pair(currentPosition, currentMovingWay))
            val nextPotentialPosition = currentPosition.next(currentMovingWay)
            val symbol = map.getOrDefault(nextPotentialPosition, null)

            if ((symbol != null && symbol == '#') || nextPotentialPosition == newObstacle) {
                currentMovingWay = currentMovingWay.turnOnRight()
            } else {
                currentPosition = nextPotentialPosition
            }
        }
        return false
    }

    fun part1(input: List<String>): Int {
        val map = getMap(input)
        val maxX = input.size - 1
        val maxY = input[0].length - 1
        return getAllPositions(map, maxX, maxY).distinct().size
    }

    fun part2(input: List<String>): Int {
        val map = getMap(input)
        val maxX = input.size - 1
        val maxY = input[0].length - 1
        val possibleNewObstaclePositions = map.filterValues { it == '.' }.keys
        val positionsForInfiniteLoop = mutableListOf<Position>()

        for (position in possibleNewObstaclePositions) {
            if (willGardBeStuck(map, maxX, maxY, position)) {
                positionsForInfiniteLoop.add(position)
            }
        }

        return positionsForInfiniteLoop.count()
    }


    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day06_test")
    check(part1(testInput) == 41)
    check(part2(testInput) == 6)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day06")
    part1(input).println()
    //TODO Improve running time ?
    part2(input).println()
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

fun Position.next(movingWay: MovingWay): Position = when (movingWay) {
    MovingWay.UP -> Pair(this.first - 1, this.second)
    MovingWay.RIGHT -> Pair(this.first, this.second + 1)
    MovingWay.DOWN -> Pair(this.first + 1, this.second)
    MovingWay.LEFT -> Pair(this.first, this.second - 1)
}

typealias Position = Pair<Int, Int>
typealias PositionWithMovingWay = Pair<Position, MovingWay>
typealias PuzzleMap = MutableMap<Position, Char>
typealias Positions = MutableList<Position>
