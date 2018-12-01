package com.github.frozengenis.day1

import java.io.File
import java.util.*

fun main() {
    val inputOfFrequencies = File("""C:\Users\Leroy\Workspace\advent-of-code-2018\src\com\github\frozengenis\day1\input.txt""")
    val result = sumFrequencies(inputOfFrequencies)
    println(result)
}

fun sumFrequencies(input: File) : Int {
    val scanner = Scanner(input)
    var result = 0

    while (scanner.hasNext()) {
        val token = scanner.next()
        result += parseFrequency(token)
    }

    return result
}

fun parseFrequency(input: String) : Int {
    val frequency = input.trim()
    val scanner = Scanner(frequency)
    val token = scanner.useDelimiter("").next()
    val number = scanner.reset().nextInt()

    return if (token == "+") number else -number
}
