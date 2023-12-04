fun main() {
    val symbols = "*/=@#%&+$-"
    val symbol = '*'

    fun findNumber(row: List<Char>, startIndex: Int): Int {
        var number = ""

        // Going left to find number's first digit's index
        tailrec fun findFirstIndex(row: List<Char>, startIndex: Int): Int {
            return if (startIndex != 0 && "\\d".toRegex().matches(row[startIndex - 1].toString())) {
                findFirstIndex(row, startIndex - 1)
            } else {
                startIndex
            }
        }

        // Going right for whole number from firstIndex
        var firstDigitIndex = findFirstIndex(row, startIndex)
        while (true) {
            number += row[firstDigitIndex]
            if (firstDigitIndex == row.lastIndex || "\\D".toRegex().matches(row[firstDigitIndex + 1].toString())) {
                break
            } else {
                firstDigitIndex++
            }
        }

        return number.toInt()
    }

    // Find the adjacent number sets from the symbols in the grid
    fun findNumberSets(grid: List<List<Char>>, rowIndex: Int, columnIndex: Int): Set<Int> {
        val numbersSet = mutableSetOf<Int>()

        // Checking left
        if (columnIndex != 0) {
            if (grid[rowIndex][columnIndex - 1].isDigit()) {
                numbersSet.add(findNumber(grid[rowIndex], columnIndex - 1))
            }
        }

        // Checking right
        if (columnIndex != grid[0].lastIndex) {
            if (grid[rowIndex][columnIndex + 1].isDigit()) {
                numbersSet.add(findNumber(grid[rowIndex], columnIndex + 1))
            }
        }

        // Checking top
        if (rowIndex != 0) {
            if (grid[rowIndex - 1][columnIndex].isDigit()) {
                numbersSet.add(findNumber(grid[rowIndex - 1], columnIndex))
            }
        }

        // Checking Bottom
        if (rowIndex != grid.lastIndex) {
            if (grid[rowIndex + 1][columnIndex].isDigit()) {
                numbersSet.add(findNumber(grid[rowIndex + 1], columnIndex))
            }
        }

        // Checking top-left diagonal
        if (rowIndex != 0 && columnIndex != 0) {
            if (grid[rowIndex - 1][columnIndex - 1].isDigit()) {
                numbersSet.add(findNumber(grid[rowIndex - 1], columnIndex - 1))
            }
        }

        // Checking bottom-left diagonal
        if (rowIndex != grid.lastIndex && columnIndex != 0) {
            if (grid[rowIndex + 1][columnIndex - 1].isDigit()) {
                numbersSet.add(findNumber(grid[rowIndex + 1], columnIndex - 1))
            }
        }

        // Checking top-right diagonal
        if (rowIndex != 0 && columnIndex != grid[0].lastIndex) {
            if (grid[rowIndex - 1][columnIndex + 1].isDigit()) {
                numbersSet.add(findNumber(grid[rowIndex - 1], columnIndex + 1))
            }
        }

        // Checking bottom-right diagonal
        if (rowIndex != grid.lastIndex && columnIndex != grid[0].lastIndex) {
            if (grid[rowIndex + 1][columnIndex + 1].isDigit()) {
                numbersSet.add(findNumber(grid[rowIndex + 1], columnIndex + 1))
            }
        }

        return numbersSet.toSet()
    }

    fun findSumFromSymbols(grid: List<List<Char>>): Int {
        var result = 0

        for (rowIndex in grid.indices) {
            for (columnIndex in grid[rowIndex].indices) {
                if (symbols.contains(grid[rowIndex][columnIndex])) {
                    result += findNumberSets(grid, rowIndex, columnIndex).sum()
                }
            }
        }

        return result
    }

    fun part1(input: List<String>): Int {
        val grid: List<List<Char>> = List(input.size) { row ->
            List(input[0].length) { column ->
                input[row][column]
            }
        }

        return findSumFromSymbols(grid)
    }

    fun findMulFromSymbols(grid: List<List<Char>>): Int {
        var gearRatioSum = 0

        for (rowIndex in grid.indices) {
            for (columnIndex in grid[rowIndex].indices) {
                if (symbol == grid[rowIndex][columnIndex]) {
                    val numberSet = findNumberSets(grid, rowIndex, columnIndex)
                    if (numberSet.size == 2) {
                        var gearRatio = 1
                        numberSet.forEach {
                            gearRatio *= it
                        }
                        gearRatioSum += gearRatio
                    }
                }
            }
        }

        return gearRatioSum
    }

    fun part2(input: List<String>): Int {
        val grid: List<List<Char>> = List(input.size) { row ->
            List(input[0].length) { column ->
                input[row][column]
            }
        }

        return findMulFromSymbols(grid)
    }

    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}