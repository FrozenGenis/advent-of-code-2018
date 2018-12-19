package com.github.frozengenis.day2

import java.io.File
import java.util.*

fun main() {
    val boxes = File("""src\com\github\frozengenis\day2\input.txt""")
        .let(::parseToBoxes)

    val checksum = calculateChecksum(boxes)

    StringBuilder("Advent of Code 2018 - Day 2").appendln().appendln()
        .appendln("Checksum: $checksum")
        .let(::print)
}

private fun parseToBoxes(inputFile: File): List<Box> {
    val scanner = Scanner(inputFile)
    val result = mutableListOf<Box>()

    while (scanner.hasNext()) {
        scanner.next()
            .let(::Box)
            .let(result::add)
    }

    return result
}

private fun calculateChecksum(boxes: List<Box>): Int =
    boxes
        .fold(Pair(0, 0)) { acc, e -> accumulateFrequencyCounts(acc, e) }
        .let { it.first * it.second }

private fun accumulateFrequencyCounts(acc: Pair<Int, Int>, box: Box): Pair<Int, Int> {
    val appearances = checkForFrequenciesIn(box.id)
    if (!appearances.first && !appearances.second) return acc

    val frequencyOfTwoCount = if (appearances.first) acc.first + 1 else acc.first
    val frequencyOfThreeCount = if (appearances.second) acc.second + 1 else acc.second
    return acc.copy(frequencyOfTwoCount, frequencyOfThreeCount)
}

/**
 * Returns a [Pair] with the following conditions:
 * - the first value is true if [boxId] contains a letter with a frequency of two; false otherwise
 * - the second value is true if [boxId] contains a letter with a frequency of three; false otherwise.
 */
private fun checkForFrequenciesIn(boxId: String): Pair<Boolean, Boolean> {
    val scanner = Scanner(boxId).useDelimiter("")
    val frequencyOfChars = mutableMapOf<Char, Int>()

    while (scanner.hasNext()) {
        val char = scanner.next()[0]

        val frequency = frequencyOfChars.getOrDefault(char, defaultValue = 0)
        if (frequency > 3) continue

        frequencyOfChars[char] = frequency + 1
    }

    return Pair(frequencyOfChars.values.contains(2), frequencyOfChars.values.contains(3))
}
