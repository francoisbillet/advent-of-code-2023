package day4

import java.io.File

fun main() {
    val cards = File("src/main/kotlin/day4/puzzle.txt").readLines()
    val cardsCount = setupCardsCount(cards)
    val cardsWithoutFirstPart = getCardsWithoutFirstPart(cards)

    updateCardsCount(cardsCount, cardsWithoutFirstPart)
    println("cardsCount: $cardsCount")
    println(cardsCount.values.sum())
}

fun setupCardsCount(cards: List<String>): MutableMap<Int, Int> {
    val cardsCount = mutableMapOf<Int,Int>()
    for (i in 1..cards.size) {
        cardsCount[i] = 1
    }
    return cardsCount
}

fun getCardsWithoutFirstPart(cards: List<String>): List<String> {
    val cardsWithoutFirstPart = mutableListOf<String>()
    cards.forEach {
        cardsWithoutFirstPart.add(it.split(": ")[1])
    }
    return cardsWithoutFirstPart
}

fun updateCardsCount(cardsCount: MutableMap<Int, Int>, cards: List<String>) {
    cards.forEachIndexed{ index, card ->
        val (winningNumbers, numbers) = card.split(" | ")

        var count = cardsCount[index+1]!!
        do {
            updateCardsCountForLine(cardsCount, index+1, winningNumbers.split(" "), numbers.split(" "))
            count--
        } while (count > 0)
    }
}

fun updateCardsCountForLine(cardsCount: MutableMap<Int,Int>, index: Int, winningNumbers: List<String>, numbers: List<String>) {
    var winningNumberOccurences = getTotalWinningNumbersForCard2(winningNumbers, numbers)
    while (winningNumberOccurences > 0) {
        cardsCount[index+winningNumberOccurences] = cardsCount[index+winningNumberOccurences]!! + 1
        winningNumberOccurences--
    }
}

fun getTotalWinningNumbersForCard2(winningNumbers: List<String>, numbers: List<String>): Int {
    var sum = 0
    winningNumbers.forEach {
        if (it.isNotEmpty()) {
            sum += getOccurencesForWinningNumber(it.toInt(), numbers)
        }
    }
    return sum
}