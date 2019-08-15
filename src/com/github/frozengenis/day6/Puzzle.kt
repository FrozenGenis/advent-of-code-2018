package com.github.frozengenis.day6

import java.io.File
import java.util.*

fun main() {
    val coordinates = File("""src\com\github\frozengenis\day6\test.txt""")
        .let(::parseCoordinates)

    val largestAreaSize = calculateLargestAreaSize(coordinates)

    StringBuilder("Advent of Code 2018 - Day 6").appendln().appendln()
        .appendln("Size of the largest area: $largestAreaSize")
        .let(::print)
}

private fun parseCoordinates(inputFile: File): Set<Coordinate> {
    val scanner = inputFile.let(::Scanner)
    val result = mutableSetOf<Coordinate>()

    while (scanner.hasNext()) {
        scanner.nextLine()
            .let(::parseCoordinate)
            .let(result::add)
    }

    return result
}

private fun parseCoordinate(input: String): Coordinate {
    val scanner = input.let(::Scanner).useDelimiter(", ")

    val x = scanner.nextInt()
    val y = scanner.nextInt()

    return Coordinate(x, y)
}

private fun calculateLargestAreaSize(coordinates: Set<Coordinate>): Int {
    val grid = Grid(coordinates).run(::println)

    return -1
}
