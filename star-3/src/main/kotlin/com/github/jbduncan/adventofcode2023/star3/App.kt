package com.github.jbduncan.adventofcode2023.star3

import java.io.PrintWriter
import java.lang.AssertionError
import kotlin.text.Charsets.UTF_8

fun main() {
    val out = PrintWriter(System.out, true)
    execute(out, puzzleInput())
}

internal fun execute(out: PrintWriter, puzzleInput: String) {
    val result = puzzleInput.lineSequence().sumOf(::possibleGameIdOrZero)
    out.println(result)
}

private fun possibleGameIdOrZero(game: String): Int {
    val gameIdRegex = Regex("""Game (\d+): """)
    val gameId =
        gameIdRegex.matchAt(game, 0)!!
            .groupValues[1]
            .toInt()
    val possibleGameFound = gameIdRegex.replace(game, "")
        .splitToSequence(Regex("(, |; )"))
        .none(::isImpossible)
    return if (possibleGameFound) {
        gameId
    } else {
        0
    }
}

private fun isImpossible(cube: String): Boolean {
    val maxRedCubes = 12
    val maxGreenCubes = 13
    val maxBlueCubes = 14

    val (number, colour) = cube.split(" ", limit = 2)
    return when (colour) {
        "blue" -> {
            number.toInt() > maxBlueCubes
        }

        "red" -> {
            number.toInt() > maxRedCubes
        }

        "green" -> {
            number.toInt() > maxGreenCubes
        }

        else -> false
    }
}

private fun puzzleInput(): String = //
    object {}::class.java //
        .getResourceAsStream("/puzzle-input.txt")!! //
        .bufferedReader(UTF_8) //
        .readText()
