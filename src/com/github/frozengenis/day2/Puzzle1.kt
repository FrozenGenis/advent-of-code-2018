package com.github.frozengenis.day2

import java.io.File
import java.util.*

fun main() {
    val inputFile = File("""src\com\github\frozengenis\day2\input.txt""")
    val scanner = Scanner(inputFile)
    var sums = Pair(0, 0)

    while (scanner.hasNext()) {
        val boxId = scanner.next()
        sums = updateSums(boxId, sums)
    }

    val checksum = sums.first * sums.second
    println(checksum)
}

private fun updateSums(boxId: String, sums: Pair<Int, Int>): Pair<Int, Int> {
    val appearances = checkAppearances(boxId)
    if (!appearances.first && !appearances.second) return sums

    val firstSum = if (appearances.first) sums.first + 1 else sums.first
    val secondSum = if (appearances.second) sums.second + 1 else sums.second
    return sums.copy(firstSum, secondSum)
}

/**
 * Returns a [Pair] with the following conditions:
 * - the first value is true if [boxId] contains a letter which appears exactly twice; false otherwise
 * - the second value is true if [boxId] contains a letter which appears exactly three times; false otherwise.
 */
private fun checkAppearances(boxId: String): Pair<Boolean, Boolean> {
    val scanner = Scanner(boxId).useDelimiter("")
    val frequencyMap = mutableMapOf<Char, Int>()

    while (scanner.hasNext()) {
        val char = scanner.next()[0]
        frequencyMap.compute(char) { _, value -> if (value == null) 1 else value + 1 }
    }

    return Pair(frequencyMap.values.contains(2), frequencyMap.values.contains(3))
}