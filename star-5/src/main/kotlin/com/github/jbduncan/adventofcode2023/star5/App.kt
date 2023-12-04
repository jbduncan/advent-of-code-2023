package com.github.jbduncan.adventofcode2023.star5

import java.io.PrintWriter
import java.lang.AssertionError
import kotlin.text.Charsets.UTF_8

fun main() {
    val out = PrintWriter(System.out, true)
    execute(out, puzzleInput())
}

internal fun execute(out: PrintWriter, puzzleInput: String) {
    val result = Grid(puzzleInput).partNumbers().sumOf(PartNumber::value)
    out.println(result)
}

class Grid(private val puzzleInput: String) {
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

    private operator fun String.get(x: Int, y: Int): Char {
        return puzzleInput[x + (y * height)]
    }

    fun partNumbers(): List<PartNumber> {
        val result = mutableListOf<PartNumber>()

        for (y in (0 until height)) {
            var x = 0
            while (x < width) {
                if (grid[y][x].isDigit()) {
                    val start = x
                    while (x < width && grid[y][x].isDigit()) {
                        x++
                    }
                    val end = x - 1
                    if (isSurroundedBySymbol(y, start, end)) {
                        val digits = (start..end).map { grid[y][it] }.joinToString(separator = "")
                        result.add(PartNumber(digits.toInt()))
                    }
                } else {
                    x++
                }
            }
        }

        return result
    }

    private fun isSurroundedBySymbol(height: Int, start: Int, end: Int): Boolean {
        return (start..end).any { x ->
            neighbours(x, height).any {
                !it.isDigit() && it != '.'
            }
        }
    }

    private fun neighbours(x: Int, y: Int): List<Char> {
        return buildList {
            if (y - 1 >= 0 && x - 1 >= 0) {
                add(grid[y - 1][x - 1])
            }
            if (x - 1 >= 0) {
                add(grid[y][x - 1])
            }
            if (y + 1 < width && x - 1 >= 0) {
                add(grid[y + 1][x - 1])
            }
            if (y + 1 < width) {
                add(grid[y + 1][x])
            }
            if (y + 1 < width && x + 1 < height) {
                add(grid[y + 1][x + 1])
            }
            if (x + 1 < height) {
                add(grid[y][x + 1])
            }
            if (y - 1 >= 0 && x + 1 < height) {
                add(grid[y - 1][x + 1])
            }
            if (y - 1 >= 0) {
                add(grid[y - 1][x])
            }
        }
    }
}

data class PartNumber(
    val value: Int
)

private fun puzzleInput(): String = //
    object {}::class.java //
        .getResourceAsStream("/puzzle-input.txt")!! //
        .bufferedReader(UTF_8) //
        .readText()

private fun unreachable(): Nothing = throw AssertionError("unreachable")
