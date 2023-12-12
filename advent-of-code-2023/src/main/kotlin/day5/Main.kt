package day5

import java.io.File

fun main() {
    val puzzle = File("src/main/kotlin/day5/puzzle.txt").readText()
    val puzzleParts = puzzle.split("\n\n")

    val seeds = puzzleParts[0].split(": ")[1].split(" ")
    val seedToSoilMap = getMap(puzzleParts[1].split(":\n")[1].split("\n"))
    val soilToFertilizerMap = getMap(puzzleParts[2].split(":\n")[1].split("\n"))
    val fertilizerToWaterMap = getMap(puzzleParts[3].split(":\n")[1].split("\n"))
    val waterToLightMap = getMap(puzzleParts[4].split(":\n")[1].split("\n"))
    val lightToTemperatureMap = getMap(puzzleParts[5].split(":\n")[1].split("\n"))
    val temperatureToHumidityMap = getMap(puzzleParts[6].split(":\n")[1].split("\n"))
    val humidityToLocationMap = getMap(puzzleParts[7].split(":\n")[1].split("\n"))

    val results = mutableListOf<Long>()
    seeds.forEach {
        val seedToSoilResult = getResultInMapForSeed(it.toLong(), seedToSoilMap)
        val soilToFertilizerResult = getResultInMapForSeed(seedToSoilResult, soilToFertilizerMap)
        val fertilizerToWaterResult = getResultInMapForSeed(soilToFertilizerResult, fertilizerToWaterMap)
        val waterToLightResult = getResultInMapForSeed(fertilizerToWaterResult, waterToLightMap)
        val lightToTemperatureResult = getResultInMapForSeed(waterToLightResult, lightToTemperatureMap)
        val temperatureToHumidityResult = getResultInMapForSeed(lightToTemperatureResult, temperatureToHumidityMap)
        val humidityToLocationResult = getResultInMapForSeed(temperatureToHumidityResult, humidityToLocationMap)
        results.add(humidityToLocationResult)
    }
    println(results.min())
}

fun getResultInMapForSeed(seed: Long, map: Map<Long, Long>): Long = map[seed] ?: seed

fun getMap(puzzleMap: List<String>): Map<Long, Long> {
    val map = mutableMapOf<Long, Long>()
    puzzleMap.forEach {
        val (destinationRangeStart, sourceRangeStart, rangeLength) = it.split(" ")
        var i = rangeLength.toInt()
        while (i > 0) {
            val toAdd = rangeLength.toInt() - i
            map[sourceRangeStart.toLong() + toAdd] = destinationRangeStart.toLong() + toAdd
            i--
        }
    }
    return map
}