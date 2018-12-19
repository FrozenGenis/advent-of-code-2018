package com.github.frozengenis.day5

import java.io.File
import java.util.*

fun main() {
    val polymer = File("""src\com\github\frozengenis\day5\input.txt""")
        .let(::Scanner)
        .let(Scanner::next)
        .let(::Polymer)

    val result = StringBuilder("Advent of Code 2018 - Day 5").appendln().appendln()
        .appendln("Remaining units: ${polymer.react().length}")
        .appendln("Shortest polymer length with one type removal: ${polymer.calculateMinLengthAfterSingleTypeRemoval()}")

    print(result)
}
