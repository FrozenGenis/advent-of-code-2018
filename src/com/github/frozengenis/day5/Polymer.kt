package com.github.frozengenis.day5

class Polymer(private val sequence: CharSequence) {

    val length
        get() = sequence.length

    fun react(): Polymer = applyReactions(sequence).let(::Polymer)

    private fun applyReactions(inputSequence: CharSequence): CharSequence {
        var i = 0
        var result = inputSequence

        while (i < result.lastIndex) {
            val currentValue = result[i]
            val nextValue = result[i + 1]

            if (isReaction(currentValue, nextValue)) {
                result = result.removeRange(i, i + 2)
                i = if (i < 1) 0 else i - 1
            } else {
                i += 1
            }
        }

        return result
    }

    private fun isReaction(c1: Char, c2: Char) = c1.equals(c2, ignoreCase = true) && c1.isOppositeCase(c2)
}
