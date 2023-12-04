package day4

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class Part1Test: ShouldSpec ({
    should("return 0 if there's no occurence of the number in the list") {
        // When
        val result = getOccurencesForWinningNumber(41, listOf("83", "86", "6", "31", "17", "9", "48", "53"))

        // Then
        result shouldBe 0
    }

    should("return 1 if there's 1 occurence of the number in the list") {
        // When
        val result = getOccurencesForWinningNumber(83, listOf("83", "86", "6", "31", "17", "9", "48", "53"))

        // Then
        result shouldBe 1
    }

    should("return the sum of all winning numbers within a card") {
        // When
        val result = getTotalWinningNumbersForCard(listOf("41", "48","83","86","17"), listOf("83", "86", "6", "31", "17", "9", "48", "53"))

        // Then
        result shouldBe 8
    }

    should("return the sum of all winning numbers") {
        // When
        val result = getTotalWinningNumbers(listOf("41 48 83 86 17 | 83 86  6 31 17  9 48 53", "13 32 20 16 61 | 61 30 68 82 17 32 24 19", "1 21 53 59 44 | 69 82 63 72 16 21 14  1", "41 92 73 84 69 | 59 84 76 51 58  5 54 83", "87 83 26 28 32 | 88 30 70 12 93 22 82 36", "31 18 13 56 72 | 74 77 10 23 35 67 36 11"))

        // Then
        result shouldBe 13
    }
})