package com.github.frozengenis.day4

import java.time.Duration
import java.time.LocalDateTime

class Guard(val id: Int) {

    val totalTimeAsleep: Duration = Duration.ZERO
    val minuteMostAsleep
        get() = schedule.entries.maxBy { it.value }?.key
    val maxTimesAsleepOnSameMinute
        get() = schedule.values.max()

    private lateinit var sleepTime: LocalDateTime
    private val schedule = mutableMapOf<Int, Int>()

    fun wakeUpAt(wakeTime: LocalDateTime) {
        val timeAsleep = Duration.between(sleepTime, wakeTime)
        totalTimeAsleep.plus(timeAsleep)

        for (minute in sleepTime.minute until wakeTime.minute) {
            schedule.computeIfPresent(minute) { _, value -> value + 1 } ?: schedule.put(minute, 1)
        }
    }

    fun sleepAt(time: LocalDateTime) {
        sleepTime = time
    }
}
