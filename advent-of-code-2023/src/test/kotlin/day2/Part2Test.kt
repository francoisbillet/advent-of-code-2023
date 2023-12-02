package day2

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class Part2Test: ShouldSpec({
    should("return the number of each color for a set") {
        getNumberOfCubesForSet("3 blue, 4 red") shouldBe Triple(3, 4, 0)
    }

    should("return the minimum number of each color for a game") {
        getMinimumNumberOfCubesForGame("3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green") shouldBe Triple(6, 4, 2)
    }

    should("return the product of the triple numbers") {
        getProduct(Triple(2, 3, 5)) shouldBe 30
    }

    should("return the sum of the product of minimum number of cubes for all games") {
        getSumOfCubesProduct(listOf("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green",
            "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue",
            "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red",
            "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red",
            "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green")) shouldBe 2286
    }
})