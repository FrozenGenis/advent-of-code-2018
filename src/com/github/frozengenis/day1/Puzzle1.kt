package com.github.frozengenis.day1

import java.io.File
import java.util.*

fun main() = File("""src\com\github\frozengenis\day1\input.txt""")
    .let(::sumFrequencies)
    .let(::println)

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
