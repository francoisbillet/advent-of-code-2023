package day3

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class MainTest : ShouldSpec ({
    should("extract numbers from a string") {
        // When
        val numbers = getNumbersFromString("467..114..")

        // Then
        numbers shouldBe listOf(467, 114)
    }

    should("return the adjacent indexes of a number in a string") {
        // When
        val indexes = getAdjacentIndexes(114, "467..114..")

        // Then
        indexes.first shouldBe 4
        indexes.second shouldBe 8
    }

    should("return the adjacent indexes of a number at index 0 in a string") {
        // When
        val indexes = getAdjacentIndexes(467, "467..114..")

        // Then
        indexes.first shouldBe 0
        indexes.second shouldBe 3
    }

    should("return the adjacent indexes of a number at last index in a string") {
        // When
        val indexes = getAdjacentIndexes(49241, "4..49241")

        // Then
        indexes.first shouldBe 2
        indexes.second shouldBe 7
    }

    should("return true if a symbol lies within the given pair of indexes") {
        // When
        val result = isSymbolWithinIndexes(Pair(0, 3), "...*......")

        // Then
        result shouldBe true
    }

    should("return false if a symbol does not lie within the given pair of indexes") {
        // When
        val result = isSymbolWithinIndexes(Pair(4, 8), "...*......")

        // Then
        result shouldBe false
    }

    should("return true if number is adjacent to a symbol") {
        // When
        val result = isNumberAdjacentToSymbol(Pair(1,4), listOf(
            "...*......",
            "..35..633.",
            "......#..."))

        // Then
        result shouldBe true
    }

    should("return false if number is not adjacent to a symbol") {
        // When
        val result = isNumberAdjacentToSymbol(Pair(6,9), listOf(
            "617*......",
            ".....+.58.",
            "..592....."))

        // Then
        result shouldBe false
    }

    should("return the sum of numbers that have an adjacent symbol") {
        // When
        val result = getSumOfNumbersWithoutAdjacentSymbols(listOf("467..114..",
                "...*......",
                "..35..633.",
                "......#...",
                "617*......",
                ".....+.58.",
                "..592.....",
                "......755.",
                "...$.*....",
                ".664.598.."))

        // Then
        result shouldBe 4361
    }

    should("work !") {
        // When
        val result = getSumOfNumbersWithoutAdjacentSymbols(listOf("..827.137..*...*....39................*..............856..............767........522......$..773....619..............*...287....501.........",
                "..........726...511.............*.....320........476...............................*................%...899....72..731...........%....$.....",
                ".....861..............232....223.933...............*.@........424*618.858.......................................$.......338.205........535.."))

        // Then
        result shouldBe 4361
    }

    should("return the sum of numbers that have an adjacent symbol 2") {
        // When
        val result = getSumOfNumbersWithAdjacentSymbol(listOf("467..114..",
            "...*......",
            "..35..633.",
            "......#...",
            "617*......",
            ".....+.58.",
            "..592.....",
            "......755.",
            "...$.*....",
            ".664.598.."))

        // Then
        result shouldBe 4361
    }


})