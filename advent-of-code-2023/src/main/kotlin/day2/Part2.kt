package day2

fun main() {
    println(getSumOfCubesProduct(getGames()))
}

fun getNumberOfCubesForSet(set: String): Triple<Int, Int, Int> {
    var red = 0
    var blue = 0
    var green = 0
    val cubes = set.split(", ")
    cubes.forEach {
        val (amount, color) = it.split(" ")
        when (color) {
            "blue" -> blue = amount.toInt()
            "red" -> red = amount.toInt()
            "green" -> green = amount.toInt()
        }
    }
    return Triple(blue, red, green)
}

fun getMinimumNumberOfCubesForGame(game: String): Triple<Int, Int, Int> {
    val sets = game.split("; ")
    var minimumNumberOfCubes = getNumberOfCubesForSet(sets[0])

    sets.subList(1, sets.size).forEach {
        val numberOfCubes = getNumberOfCubesForSet(it)

        var minRed = minimumNumberOfCubes.second
        var minBlue = minimumNumberOfCubes.first
        var minGreen = minimumNumberOfCubes.third
        if (numberOfCubes.first > minimumNumberOfCubes.first) minBlue = numberOfCubes.first
        if (numberOfCubes.second > minimumNumberOfCubes.second) minRed = numberOfCubes.second
        if (numberOfCubes.third > minimumNumberOfCubes.third) minGreen = numberOfCubes.third

        minimumNumberOfCubes = Triple(minBlue, minRed, minGreen)
    }
    return minimumNumberOfCubes
}

fun getProduct(minimumNumberOfCubes: Triple<Int, Int, Int>): Int = minimumNumberOfCubes.first * minimumNumberOfCubes.second * minimumNumberOfCubes.third

fun getSumOfCubesProduct(games: List<String>): Int {
    var sum = 0
    games.forEach {
        val gameContent = it.split(": ")[1]
        val minimumNumberOfCubes = getMinimumNumberOfCubesForGame(gameContent)
        sum += getProduct(minimumNumberOfCubes)
    }
    return sum
}