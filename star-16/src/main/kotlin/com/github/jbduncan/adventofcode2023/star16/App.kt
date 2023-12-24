package com.github.jbduncan.adventofcode2023.star16

import java.io.PrintWriter
import java.lang.AssertionError
import kotlin.text.Charsets.UTF_8

fun main() {
    val out = PrintWriter(System.out, true)
    execute(out, puzzleInput())
}

internal fun execute(out: PrintWriter, puzzleInput: String) {
    val lines = puzzleInput.lines()
    val instructions = lines.first()
    val infiniteInstructions = //
        instructions.asSequence() //
            .map { if (it == 'L') Instruction.LEFT else Instruction.RIGHT } //
            .cycle()
    val destinationInputs = lines.subList(2, lines.size)
    val startToDestinations = startToDestinations(destinationInputs)

    val instructionsIterator = infiniteInstructions.iterator()
    val traversedDestinations = //
        generateSequence("AAA") { start ->
            val destinations = startToDestinations.getValue(start)
            when (instructionsIterator.next()) {
                Instruction.LEFT -> destinations.left
                Instruction.RIGHT -> destinations.right
            }
        }.takeWhile { it != "ZZZ" }

    val result = traversedDestinations.count()
    out.println(result)
}

enum class Instruction {
    LEFT, RIGHT
}

data class Destinations(val left: String, val right: String)

fun startToDestinations(inputs: List<String>): Map<String, Destinations> = //
    inputs.associate {
        val (_, start, left, right) = //
            Regex("""(\w+) = \((\w+), (\w+)\)""") //
                .find(it)!! //
                .groupValues
        start to Destinations(left, right)
    }

private fun <T> Sequence<T>.cycle(): Sequence<T> = //
    // Use `generateSequence(this) { it }` over `generateSequence { this }`
    // because the latter constrains the sequence to run only once, which is
    // too restrictive.
    generateSequence(this) { it }.flatten()

private fun puzzleInput(): String = //
    object {}::class.java //
        .getResourceAsStream("/puzzle-input.txt")!! //
        .bufferedReader(UTF_8) //
        .readText()

private fun unreachable(): Nothing = throw AssertionError("unreachable")
