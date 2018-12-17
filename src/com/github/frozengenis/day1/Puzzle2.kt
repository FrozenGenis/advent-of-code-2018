package com.github.frozengenis.day1

import java.io.File
import java.util.*

var sum = 0
val sumTally = mutableSetOf<Int>()

fun main() {
    val inputOfFrequencies = File("""src\com\github\frozengenis\day1\input.txt""")
    var result: Int? = null

    while (result == null) {
        result = findDuplicateResultingFrequency(inputOfFrequencies)
        result?.let(::println)
    }
}

fun findDuplicateResultingFrequency(input: File): Int? {
    val scanner = Scanner(input)

    while (scanner.hasNext()) {
        val token = scanner.next()
        sum += parseFrequency(token)

        val isAdded = sumTally.add(sum)
        if (!isAdded) return sum
    }

    return null
}
