package day6

import java.io.File

fun buildRaceDistanceAccordingToTime2(time: Long): List<Long> {
    val raceDistances = mutableListOf<Long>()
    for (i in 0..time) {
        raceDistances.add((time-i)*i)
    }
    return raceDistances
}
fun main() {
    val puzzle = File("src/main/kotlin/day6/puzzle.txt").readLines()
    val rawTime = puzzle[0].split(": ")[1]
    val rawDistance = puzzle[1].split(": ")[1]

    val time = rawTime.split(" ").filter { it.isNotEmpty() }.joinToString("")
    val distance = rawDistance.split(" ").filter { it.isNotEmpty() }.joinToString("")

    val raceDistances = buildRaceDistanceAccordingToTime2(time.toLong())
    println(raceDistances.filter { it.toLong() > distance.toLong() }.size)
}