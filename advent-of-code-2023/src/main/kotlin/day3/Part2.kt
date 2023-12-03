package day3

import java.io.File

fun main() {
    val engine = File("src/main/kotlin/day3/puzzle.txt").readLines()
    println(getAllAdjacentNumbers(engine))
}

fun getAllAdjacentNumbers(lines: List<String>): Int {
    var sum = 0
    lines.forEachIndexed{index, line ->
        val adjacentLines = mutableListOf<String>()
        if (index > 0) {
            adjacentLines.add(lines[index-1])
        }
        adjacentLines.add(line)
        if (index < lines.lastIndex) {
            adjacentLines.add(lines[index+1])
        }
        sum += getAdjacentNumbers(line, adjacentLines)
    }
    return sum
}

fun getAdjacentNumbers(line: String, adjacentLines: List<String>): Int {
    var sum = 0
    line.forEachIndexed { index, char ->
        if (char == '*') {
            var adjacentNumbers = mutableListOf<Int>()
            adjacentLines.forEach{
                adjacentNumbers.addAll(getAdjacentNumbersForIndexAndLine(it, index))
            }
            if (adjacentNumbers.size == 2) {
                sum += adjacentNumbers[0] * adjacentNumbers[1]
            }
        }
    }
    return sum
}

fun getAdjacentNumbersForIndexAndLine(line: String, index: Int): List<Int> {
    val adjacentNumbers = mutableListOf<Int>()
    if (line[index].isDigit()) {
        val number = getNumberAtGivenIndex(line, index)
        adjacentNumbers.add(number.toInt())
    }
    else {
        // cas où on est en diagonale
        if (index > 0 && line[index-1].isDigit()) {
            val numberAtPreviousIndex = getNumberAtPreviousIndex(line, index-1)
            adjacentNumbers.add(numberAtPreviousIndex.toInt())
        }
        if (index < line.lastIndex && line[index+1].isDigit()) {
            val numberAtNextIndex = getNumberAtNextIndex(line, index+1)
            adjacentNumbers.add(numberAtNextIndex.toInt())
        }
    }
    return adjacentNumbers
}

fun getNumberAtGivenIndex(line: String, index: Int): String {
    val leftPart = if (index == 0) "" else getLeftPart(line.substring(0, index))
    val rightPart = if (index == line.lastIndex) "" else getRightPart(line.substring(index+1))
    return leftPart + line[index] + rightPart
}

fun getLeftPart(subString: String): String {
    if (!subString[subString.lastIndex].isDigit()) {
        return ""
    }
    return getLeftPart(subString.substring(0, subString.lastIndex)) + subString[subString.lastIndex]
}

fun getRightPart(subString: String): String {
    if (!subString[0].isDigit()) {
        return ""
    }
    return subString[0] + getRightPart(subString.substring(1))
}

fun getNumberAtPreviousIndex(line: String, index: Int): String {
    var number = line[index].toString()
    for (i in index-1 downTo 0) {
        if (line[i].isDigit()) {
            number = line[i] + number
        }
        else {
            break
        }
    }
    return number
}

fun getNumberAtNextIndex(line: String, index: Int): String {
    var number = line[index].toString()
    for (i in index+1..line.lastIndex) {
        if (line[i].isDigit()) {
            number += line[i]
        }
        else {
            break
        }
    }
    return number
}