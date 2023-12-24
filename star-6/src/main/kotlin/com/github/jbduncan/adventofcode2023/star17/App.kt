package com.github.jbduncan.adventofcode2023.star17

import java.io.PrintWriter
import java.lang.AssertionError
import kotlin.text.Charsets.UTF_8

fun main() {
    val out = PrintWriter(System.out, true)
    execute(out, puzzleInput())
}

internal fun execute(out: PrintWriter, puzzleInput: String) {
    val result = //
        Grid(puzzleInput).gearToTwoPartNumbers() //
            .asSequence() //
            .map { (_, partNumbers) -> partNumbers.first.value * partNumbers.second.value } //
            .sum()
    out.println(result)
}

class Grid(puzzleInput: String) {
    private val height = puzzleInput.lineSequence().count()
    private val width = puzzleInput.lineSequence().first().length

    private val grid: Array<CharArray> = Array(height) { CharArray(width) }

    init {
        puzzleInput.lineSequence().forEachIndexed { y, line ->
            line.forEachIndexed { x, char ->
                grid[y][x] = char
            }
        }
    }

    fun gearToTwoPartNumbers(): Map<Gear, Pair<PartNumber, PartNumber>> {
        val gearToPartNumbers = mutableMapOf<Gear, MutableList<PartNumber>>()

        for (y in (0 until height)) {
            var x = 0
            while (x < width) {
                if (grid[y][x].isDigit()) {
                    val start = x
                    while (x < width && grid[y][x].isDigit()) {
                        x++
                    }
                    val end = x - 1

                    possibleNearbyGear(y, start, end)?.let { gear ->
                        val digits = (start..end).joinToString(separator = "") { grid[y][it].toString() }
                        val partNumber = PartNumber(digits.toInt())
                        gearToPartNumbers.getOrPut(gear) { mutableListOf() }.add(partNumber)
                    }
                } else {
                    x++
                }
            }
        }

        return buildMap {
            for ((gear, partNumbers) in gearToPartNumbers) {
                if (partNumbers.size == 2) {
                    put(gear, Pair(partNumbers[0], partNumbers[1]))
                }
            }
        }
    }

    private fun possibleNearbyGear(height: Int, start: Int, end: Int): Gear? {
        (start..end).forEach { x ->
            neighbours(x, height).forEach { neighbour ->
                if (neighbour.value == '*') {
                    return Gear(neighbour.x, neighbour.y)
                }
            }
        }
        return null
    }

    private fun neighbours(x: Int, y: Int): List<Cell> {
        return buildList {
            if (y - 1 >= 0 && x - 1 >= 0) {
                add(Cell(grid[y - 1][x - 1], y - 1, x - 1))
            }
            if (x - 1 >= 0) {
                add(Cell(grid[y][x - 1], y, x - 1))
            }
            if (y + 1 < width && x - 1 >= 0) {
                add(Cell(grid[y + 1][x - 1], y + 1, x - 1))
            }
            if (y + 1 < width) {
                add(Cell(grid[y + 1][x], y + 1, x))
            }
            if (y + 1 < width && x + 1 < height) {
                add(Cell(grid[y + 1][x + 1], y + 1, x + 1))
            }
            if (x + 1 < height) {
                add(Cell(grid[y][x + 1], y, x + 1))
            }
            if (y - 1 >= 0 && x + 1 < height) {
                add(Cell(grid[y - 1][x + 1], y - 1, x + 1))
            }
            if (y - 1 >= 0) {
                add(Cell(grid[y - 1][x], y - 1, x))
            }
        }
    }
}

data class PartNumber(
    val value: Int
)

data class Gear(
    val x: Int, val y: Int
)

data class Cell(
    val value: Char, val x: Int, val y: Int
)

private fun puzzleInput(): String = //
    object {}::class.java //
        .getResourceAsStream("/puzzle-input.txt")!! //
        .bufferedReader(UTF_8) //
        .readText()

private fun unreachable(): Nothing = throw AssertionError("unreachable")
