import kotlin.math.pow

fun main() {
    data class Card(val id: Int, val winningNumbers: List<Int>, val givenNumbers: List<Int>, var count: Int)

    fun getCard(cardLine: String): Card {
        val cardId = cardLine
            .split("|")
            .first()
            .split(":")
            .first()
            .split(" ")
            .last()
            .filterNot { it == ' ' }
            .toInt()

        val winningNumbers = cardLine
            .split("|")
            .first()
            .split(":")
            .last()
            .let { str ->
                "\\d\\d?".toRegex()
                    .findAll(str)
                    .map { it.value.toInt() }
                    .toList()
            }

        val givenNumbers = cardLine
            .split("|")
            .last()
            .let { str ->
                "\\d\\d?".toRegex()
                    .findAll(str)
                    .map { it.value.toInt() }
                    .toList()
            }

        return Card(cardId, winningNumbers, givenNumbers, 1)
    }

    fun calculatePoint(matchCount: Int): Int {
        if (matchCount == 0) return 0
        return 2.0.pow((matchCount - 1).toDouble()).toInt()
    }

    fun matchCount(card: Card): Int {
        var matchCount = 0

        card.givenNumbers.forEach {
            if (card.winningNumbers.contains(it)) matchCount++
        }

        return matchCount
    }

    fun part1(input: List<String>): Int {
        var finalPoint = 0
        input.forEach { cardLine ->
            val card = getCard(cardLine)
            val matchCount = matchCount(card)
            val point = calculatePoint(matchCount)
            finalPoint += point
        }

        return finalPoint
    }

    var allCardCounter = 0

    fun isCardWin(card: Card): Boolean {
        return matchCount(card) != 0
    }

    fun runCard(card: Card, listOfCards: List<Card>) {
        if (isCardWin(card)) {
            val matchCount = matchCount(card)
            allCardCounter += matchCount
            val range = (card.id..((card.id) + matchCount - 1).coerceAtMost(listOfCards.lastIndex))

            range.forEach {
                runCard(listOfCards[it], listOfCards)
            }
        }
    }

    fun part2(input: List<String>): Int {
        val cards = List(input.size) { getCard(input[it]) }

        cards.forEach {
            runCard(it, cards)
        }

        return allCardCounter + input.size
    }

    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}