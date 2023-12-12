package day8

import java.io.File

fun main() {
    val (directions, network) = File("src/main/kotlin/day8/puzzle.txt").readText().split("\n\n")
    val networkMap = mutableMapOf<String, Pair<String, String>>()
    var currentNodes = mutableListOf<String>()
    network.split("\n").forEach {
        val (node, rawDirectionNodes) = it.split(" = ")
        if (node.endsWith('A')) currentNodes.add(node)
        val directionNodes = rawDirectionNodes.filterNot { it == '(' || it == ')' }.split(", ")
        networkMap[node] = Pair(directionNodes[0], directionNodes[1])
    }

    var count = 0L
    while (!currentNodes.all { it.endsWith('Z') }) {
        val direction = directions[(count % directions.length).toInt()]
        currentNodes = currentNodes.map{
            if (direction == 'L') networkMap[it]!!.first else networkMap[it]!!.second
        }.toMutableList()
        count++
    }
    println(count)
}