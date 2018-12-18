package com.github.frozengenis.day3

class Fabric {

    enum class Area {
        CONFLICT, CLAIMED, FREE
    }

    val grid = Array(1000) { Array(1000) { Pair(Area.FREE, setOf<Int>()) } } // inches
    var overlap = 0 // inches

    fun findUniqueClaim(): Int? {
        val map = mutableMapOf<Int, MutableSet<Area>>()

        for (areas in grid) {
            for (areaWithIds in areas) {
                val ids = areaWithIds.second
                ids.forEach { map.putIfAbsent(it, mutableSetOf(areaWithIds.first))?.add(areaWithIds.first) }
            }
        }

        val filterResult = map.filterValues { areas -> areas.all { it == Area.CLAIMED } }
        return if (filterResult.size == 1) filterResult.keys.first() else null
    }

    fun processClaim(claim: Claim) {
        val (id, xStart, yStart) = claim
        val xEnd = xStart + (claim.width - 1)
        val yEnd = yStart + (claim.height - 1)

        for (i in xStart..xEnd) {
            for (j in yStart..yEnd) {
                val areaWithIds = grid[i][j]
                grid[i][j] = updateArea(areaWithIds, id)
            }
        }
    }

    private fun updateArea(areaWithIds: Pair<Area, Set<Int>>, id: Int): Pair<Area, Set<Int>> =
        when (areaWithIds.first) {
            Area.FREE -> Pair(Area.CLAIMED, setOf(id))
            Area.CLAIMED -> {
                overlap += 1
                Pair(Area.CONFLICT, areaWithIds.second + id)
            }
            Area.CONFLICT -> Pair(Area.CONFLICT, areaWithIds.second + id)
        }
}
