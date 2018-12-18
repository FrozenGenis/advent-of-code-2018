package com.github.frozengenis.day4

import java.io.File

fun main() = File("""src\com\github\frozengenis\day4\input.txt""")
    .let(::parseInputToLogs)
    .let(::applyLogsToGuards)
    .maxBy { it.maxTimesAsleepOnSameMinute ?: -1 }
    ?.let(::calculateSolution)
    .let(::println)
