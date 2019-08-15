package com.github.frozengenis.day6

import java.util.*

const val START_ID = 'A'

class Grid(coordinates: Set<Coordinate>) {

    private val width = coordinates.map(Coordinate::x).max() ?: 0
    private val height = coordinates.map(Coordinate::y).max() ?: 0
    private val grid = Array(width) { Array(height) { ' ' } }
    private val coordinatesById = mutableMapOf<Char, Coordinate>() // TODO map or set?

    init {
        coordinates.forEachIndexed { index, coordinate ->
            run {
                val id = START_ID + index

                grid[coordinate.x][coordinate.y] = id
                coordinatesById[id] = coordinate
            }
        }
    }

    override fun toString(): String {
        return "Grid(width=$width, height=$height, grid=${Arrays.toString(grid)})"
    }
}