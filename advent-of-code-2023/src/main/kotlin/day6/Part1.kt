package day6

import java.io.File

fun buildRaceDistanceAccordingToTime(time: Int): List<Int> {
    val raceDistances = mutableListOf<Int>()
    for (i in 0..time) {
        raceDistances.add((time-i)*i)
    }
    return raceDistances
}
fun main() {
    val puzzle = File("src/main/kotlin/day6/puzzle.txt").readLines()

    val rawTimes = puzzle[0]
    val times  = rawTimes.split(": ")[1].split(" ").filter { it.isNotEmpty() }
    val rawDistances = puzzle[1]
    val distances = rawDistances.split(": ")[1].split(" ").filter { it.isNotEmpty() }


    var product = 1
    times.forEachIndexed { index, time ->
        var sum = 0
        val raceDistances = buildRaceDistanceAccordingToTime(time.toInt())
        sum += raceDistances.filter { it > distances[index].toInt() }.size
        product *= sum
    }
    println(product)
}