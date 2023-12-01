import java.io.File

fun main() {
    partOne()
    partTwo()
}

fun partOne() {
    val calibrations = getCalibrations()
    var calibrationValuesTotal = 0
    calibrations.forEach {
        val firstDigit = getFirstDigit(it)
        val lastDigit = getLastDigit(it)
        val calibrationValue = "$firstDigit$lastDigit"
        calibrationValuesTotal += calibrationValue.toInt()
    }
    println("result Part One : $calibrationValuesTotal")
}

fun getCalibrations(): List<String> = File("src/puzzle.txt").readLines()

fun getFirstDigit(calibration: String): Char {
    val digits = calibration.filter { it.isDigit() }
    return if (digits.isNotEmpty()) digits[0] else '0'
}

fun getLastDigit(calibration: String): Char {
    val digits = calibration.filter { it.isDigit() }
    return if (digits.isNotEmpty()) digits[digits.length - 1] else '0'
}

fun partTwo() {
    val calibrations = listOf("two1nine", "eightwothree", "abcone2threexyz", "xtwone3four", "4nineeightseven2", "zoneight234", "7pqrstsixteen")
    // replace one, two, etc. by 1, 2, etc.
    calibrations.forEach {
        val updatedCalibration = replaceAllSpelledDigits(it)
        println(updatedCalibration)
    }
}

fun replaceAllSpelledDigits(calibration: String): String = calibration.replace("one", "1")
        .replace("two", "2")
        .replace("three", "3")
        .replace("four", "4")
        .replace("five", "5")
        .replace("six", "6")
        .replace("seven", "7")
        .replace("eight", "8")
        .replace("nine", "9")