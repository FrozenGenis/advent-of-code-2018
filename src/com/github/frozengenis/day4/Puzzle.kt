package com.github.frozengenis.day4

import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

fun main() {
    val guards = File("""src\com\github\frozengenis\day4\input.txt""")
        .let(::parseInputToLogs)
        .let(::applyLogsToGuards)

    val guardMostSlept = guards.maxBy(Guard::totalTimeAsleep)
    val guardMostSleptOnSameMinute = guards.maxBy { it.maxFrequencyAsleepOnSameMinute ?: -1 }

    StringBuilder("Advent of Code 2018 - Day 4").appendln().appendln()
        .appendln("Answer part 1: ${guardMostSlept?.let(::calculateAnswer)} \t by $guardMostSlept")
        .appendln("Answer part 2: ${guardMostSleptOnSameMinute?.let(::calculateAnswer)} \t by $guardMostSleptOnSameMinute")
        .let(::print)
}

private fun parseInputToLogs(input: File): List<Log> {
    val scanner = Scanner(input)
    val result = mutableListOf<Log>()

    while (scanner.hasNext()) {
        scanner.nextLine()
            .let(::parseToLog)
            .let(result::add)
    }

    return result.sortedBy { it.timestamp }
}

private fun parseToLog(input: String): Log {
    val scanner = Scanner(input).useDelimiter("""[\[\]]""")

    val timestamp = scanner.next()
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    val time = LocalDateTime.parse(timestamp, formatter)

    val action = scanner.nextLine()
    return when {
        action.contains("begins shift") -> ShiftLog(time, action.filter { it.isDigit() }.toInt())
        action.contains("falls asleep") -> SleepLog(time)
        action.contains("wakes up") -> WakeUpLog(time)
        else -> throw IllegalArgumentException("Invalid input log")
    }
}

private fun applyLogsToGuards(logs: List<Log>): List<Guard> {
    var guardAtShift: Guard? = null
    val guardsById = mutableMapOf<Int, Guard>()

    logs.forEach { log ->
        when (log) {
            is ShiftLog -> guardAtShift = guardsById.getOrPut(log.guardId) { Guard(log.guardId) }
            is SleepLog -> guardAtShift?.sleepAt(log.timestamp) ?: throw IllegalArgumentException("Invalid input: a sleep log cannot appear before a shift log")
            is WakeUpLog -> guardAtShift?.wakeUpAt(log.timestamp) ?: throw IllegalArgumentException("Invalid input: a wake up log cannot appear before a shift log")
        }
    }

    return guardsById.values.toList()
}

private fun calculateAnswer(guard: Guard) = guard.minuteMostAsleep?.times(guard.id)
