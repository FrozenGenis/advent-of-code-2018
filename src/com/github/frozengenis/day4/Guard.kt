package com.github.frozengenis.day4

import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime

class Guard(val id: Int) {

    var totalTimeAsleep: Int = 0

    private lateinit var sleepTime: LocalDateTime
    private val schedule: MutableMap<Int, MutableSet<LocalDate>> = mutableMapOf()

    fun findMinuteMostSlept() = schedule.entries.maxBy { it.value.size }?.key

    fun wakeUpAt(wakeTime: LocalDateTime) {
        val timeAsleep = Duration.between(sleepTime, wakeTime)
        totalTimeAsleep += timeAsleep.toMinutesPart()

        for (minute in sleepTime.minute..wakeTime.minute) {
            val date = wakeTime.toLocalDate()
            schedule.putIfAbsent(minute, mutableSetOf(date))?.add(date)
        }
    }

    fun sleepAt(time: LocalDateTime) {
        sleepTime = time
    }
}
