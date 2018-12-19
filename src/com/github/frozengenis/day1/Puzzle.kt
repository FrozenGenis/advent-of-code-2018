package com.github.frozengenis.day1

import java.io.File
import java.util.*

fun main() {
    val inputFile = File("""src\com\github\frozengenis\day1\input.txt""")

    StringBuilder("Advent of Code 2018 - Day 1").appendln().appendln()
        .appendln("Resulting frequency: ${calculateResultingFrequency(inputFile)}")
        .appendln("Duplicate resulting frequency: ${findDuplicateResultingFrequency(inputFile)}")
        .let(::print)
}

private fun calculateResultingFrequency(inputFile: File): Int {
    val scanner = Scanner(inputFile)
    var result = 0

    while (scanner.hasNext()) {
        result += scanner.next().let(::parseFrequency)
    }

    return result
}

private fun findDuplicateResultingFrequency(inputFile: File): Int {
    var result: Int? = null
    var resultingFrequency = 0
    val resultingFrequencies = mutableSetOf<Int>()

    while (result == null) {
        val searchResult = searchForDuplicate(inputFile, resultingFrequency, resultingFrequencies)
        if (searchResult.first == null) resultingFrequency = searchResult.second else result = searchResult.first
    }

    return result
}

private fun searchForDuplicate(inputFile: File, currentResultingFrequency: Int, resultingFrequencies: MutableSet<Int>): Pair<Int?, Int> {
    val scanner = Scanner(inputFile)
    var resultingFrequency = currentResultingFrequency

    while (scanner.hasNext()) {
        resultingFrequency += scanner.next().let(::parseFrequency)
        val isAdded = resultingFrequencies.add(resultingFrequency)
        if (!isAdded) return Pair(resultingFrequency, resultingFrequency)
    }

    return Pair(null, resultingFrequency)
}

private fun parseFrequency(input: String): Int {
    val scanner = input.trim().let(::Scanner)
    val token = scanner.useDelimiter("").next()
    val number = scanner.reset().nextInt()

    return if (token == "+") number else -number
}
