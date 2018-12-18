package com.github.frozengenis.day3

import java.io.File

fun main() = File("""src\com\github\frozengenis\day3\input.txt""")
    .let(::parseInputToFabric)
    .let(Fabric::findUniqueClaim)
    .let(::println)
