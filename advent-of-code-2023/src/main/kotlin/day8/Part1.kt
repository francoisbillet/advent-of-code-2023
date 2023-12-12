package day8

import java.io.File

fun main() {
    val (directions, network) = File("src/main/kotlin/day8/puzzle.txt").readText().split("\n\n")
    val networkMap = mutableMapOf<String, Pair<String, String>>()
    network.split("\n").forEach {
        val (node, rawDirectionNodes) = it.split(" = ")
        val directionNodes = rawDirectionNodes.filterNot { it == '(' || it == ')' }.split(", ")
        networkMap[node] = Pair(directionNodes[0], directionNodes[1])
    }
    println(networkMap)
    println(directions)

    var count = 0
    var currentNode = "AAA"
    while (currentNode != "ZZZ") {
        val direction = directions[count % directions.length]
        currentNode = if (direction == 'L') networkMap[currentNode]!!.first else networkMap[currentNode]!!.second
        count++
    }

    println(count)
}