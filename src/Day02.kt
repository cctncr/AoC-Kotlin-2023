fun main() {
    fun part1(input: List<String>): Int {
        val conditionForRedValues = 12
        val conditionForGreenValues = 13
        val conditionForBlueValues = 14

        val validGames = mutableListOf<Int>()
        var isGameValid = true

        input.forEach { gameLine ->
            val gameID = gameLine.split(":").first().split(" ").last().toInt()
            val sets = gameLine.split(":").last()

            val redValues =
                "\\d\\d? red".toRegex().findAll(sets).map { it.value.split(" ").first() }.toList()
            val greenValues =
                "\\d\\d? green".toRegex().findAll(sets).map { it.value.split(" ").first() }.toList()
            val blueValues =
                "\\d\\d? blue".toRegex().findAll(sets).map { it.value.split(" ").first() }.toList()

            redValues.forEach {
                if (it.split(" ").first().toInt() > conditionForRedValues) {
                    isGameValid = false
                }
            }

            greenValues.forEach {
                if (it.split(" ").first().toInt() > conditionForGreenValues) {
                    isGameValid = false
                }
            }

            blueValues.forEach {
                if (it.split(" ").first().toInt() > conditionForBlueValues) {
                    isGameValid = false
                }
            }

            if (isGameValid) validGames.add(gameID)
            isGameValid = true
        }

        return validGames.sum()
    }

    fun part2(input: List<String>): Int {
        var result = 0
        input.forEach { gameLine ->
            val sets = gameLine.split(":").last()

            val maxRedValue =
                "\\d\\d? red".toRegex().findAll(sets).map { it.value.split(" ").first().toInt() }.max()
            val maxGreenValue =
                "\\d\\d? green".toRegex().findAll(sets).map { it.value.split(" ").first().toInt() }.max()
            val maxBlueValue =
                "\\d\\d? blue".toRegex().findAll(sets).map { it.value.split(" ").first().toInt() }.max()

            val power = maxRedValue * maxGreenValue * maxBlueValue
            result += power
        }

        return result
    }

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}