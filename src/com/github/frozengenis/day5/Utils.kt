package com.github.frozengenis.day5

fun Char.isOppositeCase(c: Char) = this.isLowerCase() && c.isUpperCase() || this.isUpperCase() && c.isLowerCase()
