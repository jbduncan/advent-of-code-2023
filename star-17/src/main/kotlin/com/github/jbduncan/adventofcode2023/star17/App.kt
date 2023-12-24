package com.github.jbduncan.adventofcode2023.star17

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
    val numStarts = starts(startToDestinations).size
    val endIndices = (0 until numStarts).map { i ->
            traversalSequence(infiniteInstructions, startToDestinations) //
                .takeWhile { !it[i].endsWith('Z') } //
                .count() //
                .toLong()
        }
    val result = endIndices.reduce(::leastCommonMultiple)
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

private fun starts(startToDestinations: Map<String, Destinations>): List<String> =
    startToDestinations.keys.filter { it.endsWith('A') }

private fun traversalSequence(
    instructions: Sequence<Instruction>, startToDestinations: Map<String, Destinations>
): Sequence<List<String>> {
    val startingNodes = starts(startToDestinations)
    val instructionsIterator = instructions.iterator()
    return generateSequence(startingNodes) { starts ->
        val next = instructionsIterator.next()
        starts.map {
            val (left, right) = startToDestinations.getValue(it)
            when (next) {
                Instruction.LEFT -> left
                Instruction.RIGHT -> right
            }
        }
    }
}

private fun leastCommonMultiple(a: Long, b: Long): Long = //
    a / greatestCommonDivisor(a, b) * b

private fun greatestCommonDivisor(a: Long, b: Long): Long {
    var c = a
    var d = b
    while (d != 0L) {
        val tmp = d
        d = c % d
        c = tmp
    }
    return c
}

private fun puzzleInput(): String = //
    object {}::class.java //
        .getResourceAsStream("/puzzle-input.txt")!! //
        .bufferedReader(UTF_8) //
        .readText()

private fun unreachable(): Nothing = throw AssertionError("unreachable")
