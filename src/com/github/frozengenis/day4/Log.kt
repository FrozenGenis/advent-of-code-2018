package com.github.frozengenis.day4

import java.time.LocalDateTime

sealed class Log(open val timestamp: LocalDateTime)
data class ShiftLog(override val timestamp: LocalDateTime, val guardId: Int) : Log(timestamp)
data class SleepLog(override val timestamp: LocalDateTime) : Log(timestamp)
data class WakeUpLog(override val timestamp: LocalDateTime) : Log(timestamp)
