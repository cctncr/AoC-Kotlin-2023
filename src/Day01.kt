fun main() {
    fun String.getCalibrationValue(): Int {
        var firstDigit = -1
        var lastDigit = -1
        for (i in this) {
            if (i.isDigit()) {
                if (firstDigit == -1) {
                    firstDigit = i.digitToInt()
                }
                lastDigit = i.digitToInt()
            }
        }
        return "$firstDigit$lastDigit".toInt()
    }

    fun part1(input: List<String>): Int {
        var result = 0
        input.forEach { result += it.getCalibrationValue() }
        return result
    }

    val map = mapOf(
        "one" to 1,
        "two" to 2,
        "three" to 3,
        "four" to 4,
        "five" to 5,
        "six" to 6,
        "seven" to 7,
        "eight" to 8,
        "nine" to 9,
    )

    fun String.getCVPart2(): Int {
        var first: Int = -1
        var last = -1
        var subStr = ""
        this.forEach { char ->
            if (char.isDigit()) {
                if (first == -1) {
                    first = char.digitToInt()
                }
                last = char.digitToInt()
                subStr = ""
            } else {
                subStr += char
                loop@for (i in map.keys) {
                    if (subStr.contains(i)) {
                        if (first == -1) {
                            first = map[i]!!
                        }
                        last = map[i]!!
                        subStr = "${subStr.last()}" // for situations like fiv(e)ight -> 58
                        break@loop
                    }
                }
            }
        }
        return "$first$last".toInt()
    }

    fun part2(input: List<String>): Int {
        var result = 0
        input.forEach { result += it.getCVPart2() }
        return result
    }

    // test if implementation meets criteria from the description, like:
    // val testInput = readInput("Day01_test")
    // check(part1(testInput) == 1)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}