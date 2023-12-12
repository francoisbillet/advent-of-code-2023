package day5

import java.io.File

fun main() {
    val puzzle = File("src/main/kotlin/day5/puzzle.txt").readText()
    val puzzleParts = puzzle.split("\n\n")
    val seeds = puzzleParts[0].split(": ")[1].split(" ")

    val maps = puzzleParts.subList(1, puzzleParts.size)
    var min: Long? = null
    for (i in seeds.indices step 2) {
        for (seed in seeds[i].toLong()..seeds[i].toLong() + seeds[i + 1].toLong()) {
            val seedLocation = getLocationForSeed(seed, maps)
            if (min == null || seedLocation < min) {
                min = seedLocation
            }
        }
    }
    println(min)
}