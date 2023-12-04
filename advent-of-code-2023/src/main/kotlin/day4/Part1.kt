package day4

import java.io.File
import kotlin.math.pow

fun getOccurencesForWinningNumber(winningNumber: Int, numbers: List<String>): Int {
    return numbers.filter{
        it.isNotEmpty() && it.toInt() == winningNumber
    }.size
}

fun getTotalWinningNumbersForCard(winningNumbers: List<String>, numbers: List<String>): Int {
    var sum = 0.0
    winningNumbers.forEach {
        if (it.isNotEmpty()) {
            sum += getOccurencesForWinningNumber(it.toInt(), numbers)
        }
    }
    return 2.0.pow(sum - 1).toInt()
}

fun getTotalWinningNumbers(allCards: List<String>): Int {
    var sum = 0
    allCards.forEach {
        val (winningNumbers, numbers) = it.split(" | ")
        sum += getTotalWinningNumbersForCard(winningNumbers.split(" "), numbers.split(" "))
    }
    return sum
}

fun main() {
    val cards = File("src/main/kotlin/day4/puzzle.txt").readLines()
    val cardsWithoutFirstPart = mutableListOf<String>()
    cards.forEach {
        cardsWithoutFirstPart.add(it.split(": ")[1])
    }
    println(getTotalWinningNumbers(cardsWithoutFirstPart))
}