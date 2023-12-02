package day2

import java.io.File

fun main() {
    partOne()
}

fun partOne() {
    val games = getGames()
    println(getTotalPossibleGames(games))
}

fun getGames() = File("src/main/kotlin/day2/puzzle.txt").readLines()

fun getTotalPossibleGames(games: List<String>): Int {
    var sum = 0
    games.forEach {
        val (gameNumber, gameContent) = it.split(": ")
        if (isGamePossible(gameContent)) {
            val gameId = gameNumber.split(" ")[1].toInt()
            sum += gameId
        }
    }
    return sum
}

fun isGamePossible(game: String): Boolean {
    val sets = game.split("; ")
    sets.forEach {
        if (!isSetPossible(it)) {
            return false
        }
    }
    return true
}

fun isSetPossible(set: String): Boolean {
    val grabbedCubes = set.split(", ")
    grabbedCubes.forEach {
        val (number, color) = it.split(" ")
        if (!isDrawPossible(number, color)) {
            return false
        }
    }
    return true
}

fun isDrawPossible(number: String, color: String): Boolean {
    return when (color) {
        "red" -> number.toInt() <= 12
        "green" -> number.toInt() <= 13
        "blue" -> number.toInt() <= 14
        else -> false
    }
}