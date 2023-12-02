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

fun getCalibrations(): List<String> = File("src/main/kotlin/day1/puzzle.txt").readLines()

fun getFirstDigit(calibration: String): Char {
    val digits = calibration.filter { it.isDigit() }
    return if (digits.isNotEmpty()) digits[0] else '0'
}

fun getLastDigit(calibration: String): Char {
    val digits = calibration.filter { it.isDigit() }
    return if (digits.isNotEmpty()) digits[digits.length - 1] else '0'
}

val spelledNumbers = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
val numbers = listOf("1", "2", "3", "4", "5", "6", "7", "8", "9")

fun partTwo() {
    val calibrations = getCalibrations()
    var total = 0
    calibrations.forEach {
        val firstDigit = getFirstDigit2(it)
        val lastDigit = getLastDigit2(it)
        total += "$firstDigit$lastDigit".toInt()
    }
    println("result Part Two : $total")
}

fun getFirstDigit2(calibration: String): String {
    val firstDigit = calibration.findAnyOf(spelledNumbers + numbers)?.second ?: "0"
    val firstDigitIndex = spelledNumbers.indexOf(firstDigit)
    if (firstDigitIndex != -1) {
        return numbers[firstDigitIndex]
    }
    return firstDigit
}

fun getLastDigit2(calibration: String): String {
    val lastDigit = calibration.findLastAnyOf(spelledNumbers + numbers)?.second ?: "0"
    val lastDigitIndex = spelledNumbers.indexOf(lastDigit)
    if (lastDigitIndex != -1) {
        return numbers[lastDigitIndex]
    }
    return lastDigit
}