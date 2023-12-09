package day7

import java.io.File

val types = listOf("Five", "Four", "FullHouse", "Three", "TwoP", "OneP", "High")
val labels = mutableListOf('A', 'K', 'Q', 'J', 'T')

fun main() {
    for (i in 9 downTo 2) {
        labels.add(i.digitToChar())
    }

    val puzzle = File("src/main/kotlin/day7/puzzle.txt").readLines()
    val hands = puzzle.map {
        Hand(it.split(" ")[0], it.split(" ")[1].toInt())
    }

    val handComparator = Comparator<Hand> { hand1, hand2 -> compareHands(hand1.content, hand2.content) }
    val sortedHands = hands.sortedWith(handComparator)
    println(sortedHands.sumOf { it.bid * (sortedHands.indexOf(it) + 1) })
   }

fun compareHands(hand1: String, hand2: String): Int {
    val hand1Type = getHandType(hand1)
    val hand2Type = getHandType(hand2)
    if (types.indexOf(hand1Type) < types.indexOf(hand2Type)) {
        return 1
    } else if (types.indexOf(hand1Type) > types.indexOf(hand2Type)) {
        return -1
    }
    return compareHandsWithSameType(hand1, hand2)
}

fun compareHandsWithSameType(hand1: String, hand2: String): Int {
    var i = 0
    while (i < hand1.length) {
        if (labels.indexOf(hand1[i]) < labels.indexOf(hand2[i])) {
            return 1
        } else if (labels.indexOf(hand1[i]) > labels.indexOf(hand2[i])) {
            return -1
        }
        i++
    }
    return 0
}

fun getHandType(hand: String): String {
    return when {
        isHandN(hand, 5) -> "Five"
        isHandN(hand, 4) -> "Four"
        isHandFullHouse(hand) -> "FullHouse"
        isHandN(hand, 3) -> "Three"
        isHandNPairs(hand, 2) -> "TwoP"
        isHandNPairs(hand, 1) -> "OneP"
        else -> "High"
    }
}

fun createMap(hand: String): Map<Char, Int> {
    val handMap: MutableMap<Char, Int> = mutableMapOf()
    hand.forEach {
        if (handMap[it] != null) {
            handMap[it] = handMap[it]!! + 1
        } else {
            handMap[it] = 1
        }
    }
    return handMap
}

fun isHandN(hand: String, number: Int): Boolean = createMap(hand).any { it.value == number }

fun isHandFullHouse(hand: String): Boolean {
    val handMap = createMap(hand)
    return handMap.containsValue(3).and(handMap.containsValue(2))
}

fun isHandNPairs(hand: String, n: Int): Boolean {
    val handMap = createMap(hand)
    val pairs = handMap.filter { it.value == 2 }
    return pairs.size == n
}