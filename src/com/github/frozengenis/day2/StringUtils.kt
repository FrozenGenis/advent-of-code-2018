package com.github.frozengenis.day2

fun String.hasDistanceOf(distance: Int, that: String): Boolean {
    var result = 0

    for ((index, value) in this.withIndex()) {
        if (value != that[index]) result += 1
        if (result > distance) return false
    }

    return result == distance
}

fun String.filterCommon(that: String) = this.filterIndexed { index, char -> char == that[index] }