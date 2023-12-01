package com.github.jbduncan.adventofcode2023.day1

import java.io.PrintWriter
import java.lang.AssertionError
import kotlin.text.Charsets.UTF_8

fun main() {
    val out = PrintWriter(System.out, true)
    execute(out, puzzleInput())
}

internal fun execute(out: PrintWriter, puzzleInput: String) {
    val sum =
        puzzleInput.lineSequence()
            .sumOf { line -> (firstDigit(line) * 10) + lastDigit(line) }
    out.println(sum)
}

private fun firstDigit(line: String): Int {
    for (i in line.indices) {
        val possibleDigit = possibleDigit(line, i)
        if (possibleDigit != null) {
            return possibleDigit
        }
    }

    unreachable()
}

private fun lastDigit(line: String): Int {
    for (i in line.indices.reversed()) {
        val possibleDigit = possibleDigit(line, i)
        if (possibleDigit != null) {
            return possibleDigit
        }
    }

    unreachable()
}

private fun possibleDigit(line: String, i: Int): Int? {
    if (line[i].isDigit()) {
        return line[i].digitToInt()
    }

    when (line.safeSubstring(startingAt = i, maxLength = 3)) {
        "one" -> return 1
        "two" -> return 2
        "six" -> return 6
    }
    when (line.safeSubstring(startingAt = i, maxLength = 4)) {
        "four" -> return 4
        "five" -> return 5
        "nine" -> return 9
    }
    when (line.safeSubstring(startingAt = i, maxLength = 5)) {
        "three" -> return 3
        "seven" -> return 7
        "eight" -> return 8
    }

    return null
}

private fun String.safeSubstring(startingAt: Int, maxLength: Int): String {
    val start = startingAt.coerceAtLeast(0)
    val end = (startingAt + maxLength).coerceAtMost(this.length)
    return this.substring(start, end)
}

private fun puzzleInput(): String = //
    object {}::class.java //
        .getResourceAsStream("/puzzle-input.txt")!! //
        .bufferedReader(UTF_8) //
        .readText()

private fun unreachable(): Nothing = throw AssertionError("unreachable")