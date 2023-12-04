package com.github.jbduncan.adventofcode2023.star4

import java.io.PrintWriter
import java.lang.AssertionError
import kotlin.text.Charsets.UTF_8

fun main() {
    val out = PrintWriter(System.out, true)
    execute(out, puzzleInput())
}

internal fun execute(out: PrintWriter, puzzleInput: String) {
    val result =
        puzzleInput.lineSequence()
            .map { minPossibleCubeSet(it) }
            .sumOf(::powerOf)
    out.println(result)
}

private fun minPossibleCubeSet(game: String): Set<Cube> {
    val gameIdRegex = Regex("""Game (\d+): """)
    val cubes =
        gameIdRegex.replace(game, "")
            .splitToSequence(Regex("(, |; )"))
            .map { cube ->
                val (number, colour) = cube.split(" ", limit = 2)
                when (colour) {
                    "blue" -> Cube.Blue(number.toInt())
                    "red" -> Cube.Red(number.toInt())
                    "green" -> Cube.Green(number.toInt())
                    else -> unreachable()
                }
            }
            .toList()
    val maxBlueCube =
        cubes.filterIsInstance<Cube.Blue>()
            .maxByOrNull { it.number }
            ?: Cube.Blue(0)
    val maxRedCube =
        cubes.filterIsInstance<Cube.Red>()
            .maxByOrNull { it.number }
            ?: Cube.Red(0)
    val maxGreenCube =
        cubes.filterIsInstance<Cube.Green>()
            .maxByOrNull { it.number }
            ?: Cube.Green(0)
    return setOf(maxBlueCube, maxRedCube, maxGreenCube)
}

private fun powerOf(cubes: Iterable<Cube>): Int =
    cubes.fold(1) { acc, cube -> acc * cube.number }

private sealed interface Cube {
    val number: Int

    data class Blue(override val number: Int) : Cube
    data class Red(override val number: Int) : Cube
    data class Green(override val number: Int) : Cube
}

private fun puzzleInput(): String = //
    object {}::class.java //
        .getResourceAsStream("/puzzle-input.txt")!! //
        .bufferedReader(UTF_8) //
        .readText()

private fun unreachable(): Nothing = throw AssertionError("unreachable")
