package day5

import java.io.File

fun main() {
    val puzzle = File("src/main/kotlin/day5/puzzle.txt").readText()
    val puzzleParts = puzzle.split("\n\n")
    val seeds = puzzleParts[0].split(": ")[1].split(" ")

    val maps = puzzleParts.subList(1, puzzleParts.size)
    val results = mutableListOf<Long>()
    seeds.forEach {
        results.add(getLocationForSeed(it.toLong(), maps))
    }
    println(results.min())
}

fun getLocationForSeed(seed: Long, maps: List<String>): Long {
    var target = seed
    maps.forEach {
        var flag = false
        val actualMap = it.split(":\n")[1].split(("\n"))
        actualMap.forEach { line ->
            val (destinationRangeStart, sourceRangeStart, rangeLength) = line.split(" ")
            if (target in sourceRangeStart.toLong()..(sourceRangeStart.toLong() + (rangeLength.toLong() - 1)) && !flag) {
                val delta = target - sourceRangeStart.toLong()
                target = destinationRangeStart.toLong() + delta
                flag = true
            }
        }
    }
    return target
}
