package com.github.frozengenis.day2

import java.io.File
import java.util.*

fun main() = File("""src\com\github\frozengenis\day2\input.txt""")
    .let(::parseInput)
    .let(::findPair)
    .let(::extractCommonLetters)
    .let(::println)

private fun parseInput(input: File): List<String> {
    val scanner = Scanner(input)
    val result = mutableListOf<String>()

    while (scanner.hasNext()) {
        val boxId = scanner.next()
        result.add(boxId)
    }

    return result
}

private fun findPair(boxIds: List<String>): Pair<String, String> {
    val result = boxIds.filter { boxId1 ->
        boxIds.any { hasDistanceOf(1, boxId1, it) }
    }
    if (result.size != 2) throw IllegalArgumentException("Invalid input")

    return Pair(result[0], result[1])
}

private fun hasDistanceOf(distance: Int, s1: String, s2: String): Boolean {
    var result = 0

    for ((index, value) in s1.withIndex()) {
        if (value != s2[index]) result += 1
        if (result > distance) return false
    }

    return result == distance
}

private fun extractCommonLetters(pairOfBoxIds: Pair<String, String>) = pairOfBoxIds.first.filterIndexed { index, c -> c == pairOfBoxIds.second[index] }