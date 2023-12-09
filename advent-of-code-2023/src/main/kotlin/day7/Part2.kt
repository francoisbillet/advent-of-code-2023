package day7

import java.io.File

val labelsPart2 = mutableListOf('A', 'K', 'Q', 'T')

fun main() {
    for (i in 9 downTo 2) {
        labelsPart2.add(i.digitToChar())
    }
    labelsPart2.add('J')

    val puzzle = File("src/main/kotlin/day7/puzzle.txt").readLines()
    val hands = puzzle.map { Hand(it.split(" ")[0], it.split(" ")[1].toInt()) }

    val handComparator = Comparator<Hand> { hand1, hand2 -> compareHands2(hand1.content, hand2.content) }
    val sortedHands = hands.sortedWith(handComparator)
    println(sortedHands.sumOf { it.bid * (sortedHands.indexOf(it) + 1) })
}

fun compareHands2(hand1: String, hand2: String): Int {
    val hand1Type = getHandType(getBestPossibleHandUsingJokers(hand1))
    val hand2Type = getHandType(getBestPossibleHandUsingJokers(hand2))
    if (types.indexOf(hand1Type) < types.indexOf(hand2Type)) {
        return 1
    }
    if (types.indexOf(hand1Type) > types.indexOf(hand2Type)) {
        return -1
    }
    return compareHandsWithSameType2(hand1, hand2)
}

fun getBestPossibleHandUsingJokers(hand: String): String {
    if (!hand.contains('J')) return hand

    val handMap = createMap(hand)
    val filteredHandMap = handMap.filter { it.key != 'J' }
    if (filteredHandMap.isEmpty()) {
        return "AAAAA"
    }
    val maxCharOccurrencesHand = filteredHandMap.maxBy { it.value }
    if (maxCharOccurrencesHand.value == 1) {
        var strongestCard = '2'
        for (char in filteredHandMap.keys) {
            if (labelsPart2.indexOf(char) < labelsPart2.indexOf(strongestCard)) strongestCard = char
        }
        return hand.replace('J', strongestCard)
    }
    return hand.replace('J', maxCharOccurrencesHand.key)
}

fun compareHandsWithSameType2(hand1: String, hand2: String): Int {
    var i = 0
    while (i < hand1.length) {
        if (labelsPart2.indexOf(hand1[i]) < labelsPart2.indexOf(hand2[i])) {
            return 1
        } else if (labelsPart2.indexOf(hand1[i]) > labelsPart2.indexOf(hand2[i])) {
            return -1
        }
        i++
    }
    return 0
}