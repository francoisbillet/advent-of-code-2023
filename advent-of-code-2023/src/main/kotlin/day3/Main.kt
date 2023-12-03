package day3

import java.io.File

fun getNumbersFromString(string: String): List<Int> {
    val numbers = mutableListOf<Int>()
    var number = ""
    for (i in string) {
        if (i.isDigit()) {
            number = "$number$i"
        }
        else {
            if (number.isNotEmpty()) {
                numbers.add(number.toInt())
                number = ""
            }
        }
    }
    return numbers
}

fun getAdjacentIndexes(number: Int, string: String): Pair<Int, Int> {
    var firstDigitIndex = string.indexOf(number.toString())
    var lastDigitIndex = firstDigitIndex + number.toString().length - 1
    if (firstDigitIndex > 0) {
        firstDigitIndex -= 1
    }
    if (lastDigitIndex < string.length-1) {
        lastDigitIndex += 1
    }
    return Pair(firstDigitIndex, lastDigitIndex)
}

fun isSymbolWithinIndexes(indexes: Pair<Int, Int>, string: String): Boolean {
    val regex = Regex("\\*|\\&|\\/|\\=|\\%|\\$|\\+|\\-|\\@|\\#")
    for (i in indexes.first..indexes.second) {
        if (regex.matches(string[i].toString())) {
            return true
        }
    }
    return false
}

fun isNumberAdjacentToSymbol(indexes: Pair<Int, Int>, string: List<String>): Boolean {
    string.forEach {
        if (isSymbolWithinIndexes(indexes, it)) {
            return true
        }
    }
    return false
}

fun getSumOfNumbersWithoutAdjacentSymbols(engineLines: List<String>): Int {
    var sum = 0
    engineLines.forEachIndexed { index, line ->
        var lineCopy = line
        val numbers = getNumbersFromString(line)
        numbers.forEach { number ->
            println("number is $number")
            val indexes = getAdjacentIndexes(number, lineCopy)
            val numberLength = number.toString().length
            var newString = ""
            for (i in 1..numberLength) newString = "$newString$."
            lineCopy = line.replaceFirst(number.toString(), newString)

            println("indexes are $indexes")
            val adjacentLines = mutableListOf<String>()

            if (index > 0) {
                val previousLine = engineLines[index - 1]
                val previousLineChars = previousLine.substring(indexes.first, indexes.second+1)
                adjacentLines.add(previousLineChars)
            }
            adjacentLines.add(line.substring(indexes.first, indexes.second+1))
            if (index < engineLines.lastIndex) {
                val nextLine = engineLines[index + 1]
                val nextLineChars = nextLine.substring(indexes.first, indexes.second+1)
                adjacentLines.add(nextLineChars)
            }
            println("adjacentLines are $adjacentLines")

//            if (isNumberAdjacentToSymbol(adjacentLines)) {
//                println("number is adjacent to a symbol")
//                sum += number
//            }
            println("----------------")
        }
    }
    return sum
}

fun getSumOfNumbersWithAdjacentSymbol(engineLines: List<String>): Int {
    var sum = 0
    engineLines.forEachIndexed {
        lineIndex, line ->
        var number = ""
        var numberIndex: Int? = null
        line.forEachIndexed { index, char ->
            if (char.isDigit()) {
                if (index != line.lastIndex) {
                    if (number == "") {
                        numberIndex = index
                    }
                    number += char
                }
                else {
                    number += char
                    numberIndex = numberIndex!! - 1

                    val adjacentLines = mutableListOf<String>()

                    if (lineIndex > 0) {
                        adjacentLines.add(engineLines[lineIndex - 1])
                    }
                    adjacentLines.add(line)
                    if (lineIndex < engineLines.lastIndex) {
                        adjacentLines.add(engineLines[lineIndex + 1])
                    }
                    var numberLastIndex = if (index == line.lastIndex) numberIndex!! + number.length else numberIndex!! + number.length+1
                    if (isNumberAdjacentToSymbol(Pair(numberIndex!!, numberLastIndex), adjacentLines)) {
                        sum += number.toInt()
                    }
                }
            }
            else {
                if (number.isNotEmpty()) {
                    if (numberIndex != null && numberIndex != 0) {
                        numberIndex = numberIndex!! - 1
                    }
                    val adjacentLines = mutableListOf<String>()

                    if (lineIndex > 0) {
                        adjacentLines.add(engineLines[lineIndex - 1])
                    }
                    adjacentLines.add(line)
                    if (lineIndex < engineLines.lastIndex) {
                        adjacentLines.add(engineLines[lineIndex + 1])
                    }
                    var numberLastIndex = if (index == line.lastIndex) numberIndex!! + number.length else numberIndex!! + number.length+1
                    if (isNumberAdjacentToSymbol(Pair(numberIndex!!, numberLastIndex), adjacentLines)) {
                        sum += number.toInt()
                    }
                    numberIndex = null
                }
                number = ""
            }
        }
    }
    return sum
}

fun main() {
    val engine = File("src/main/kotlin/day3/puzzle.txt").readLines()
    println(getSumOfNumbersWithAdjacentSymbol(engine))
}