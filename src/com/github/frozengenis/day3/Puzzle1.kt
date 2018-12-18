package com.github.frozengenis.day3

import java.io.File
import java.util.*

fun main() = File("""src\com\github\frozengenis\day3\input.txt""")
    .let(::parseInputToFabric)
    .let(Fabric::overlap)
    .let(::println)

fun parseInputToFabric(input: File): Fabric {
    val scanner = Scanner(input)
    val result = Fabric()

    while (scanner.hasNext()) {
        scanner.nextLine()
            .let(::parseToClaim)
            .let(result::processClaim)
    }

    return result
}

fun parseToClaim(claim: String): Claim {
    val scanner = Scanner(claim).useDelimiter("[ #@,:x]")

    val id = scanner.nextInt()
    scanner.next()
    scanner.next()
    val x = scanner.nextInt()
    val y = scanner.nextInt()
    scanner.next()
    val width = scanner.nextInt()
    val height = scanner.nextInt()

    return Claim(id = id, x = x, y = y, width = width, height = height)
}
