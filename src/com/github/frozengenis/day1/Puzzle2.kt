package com.github.frozengenis.day1

import java.io.File
import java.util.*

var sum = 0
val sumTally = mutableSetOf<Int>()

fun main() {
    val inputOfFrequencies = File("""C:\Users\Leroy\Workspace\advent-of-code-2018\src\com\github\frozengenis\day1\input.txt""")
    var foundResult = false

    while (!foundResult) {
        foundResult = findDuplicateResultingFrequency(inputOfFrequencies)
    }
}

fun findDuplicateResultingFrequency(input: File) : Boolean{
    val scanner = Scanner(input)

    while (scanner.hasNext()) {
        val token = scanner.next()
        sum += parseFrequency(token)
        val isAdded = sumTally.add(sum)

        if (!isAdded) {
            println(sum)
            return true
        }
    }

    return false
}
