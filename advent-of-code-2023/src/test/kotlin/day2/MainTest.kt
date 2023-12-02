package day2

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class MainTest : ShouldSpec({
    should("return true for a possible draw") {
        isDrawPossible("11", "red") shouldBe true
    }

    should("return false for an impossible draw") {
        isDrawPossible("15", "blue") shouldBe false
    }

    should("return true if set is possible") {
        isSetPossible("1 red, 2 green, 6 blue") shouldBe true
    }

    should("return false if set is impossible") {
        isSetPossible("13 red, 2 green, 6 blue") shouldBe false
    }

    should("return true if game is possible") {
        isGamePossible("3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green") shouldBe true
    }

    should("return false if game is impossible") {
        isGamePossible("3 blue, 4 red; 1 red, 2 green, 6 blue; 14 green") shouldBe false
    }

    should("return the sum of the possible games") {
        getTotalPossibleGames(listOf("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green",
            "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue",
            "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red",
            "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red",
            "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green")) shouldBe 8
    }
})