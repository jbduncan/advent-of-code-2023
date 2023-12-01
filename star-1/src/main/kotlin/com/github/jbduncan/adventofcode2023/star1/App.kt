package com.github.jbduncan.adventofcode2023.star1

import java.io.PrintWriter
import kotlin.text.Charsets.UTF_8

fun main() {
    val out = PrintWriter(System.out, true)
    execute(out, puzzleInput())
}

internal fun execute(out: PrintWriter, puzzleInput: String) {
    var sum = 0
    for (line in puzzleInput.lineSequence()) {
        val firstDigit = line.first(Char::isDigit).digitToInt()
        val lastDigit = line.last(Char::isDigit).digitToInt()
        sum += (firstDigit * 10) + lastDigit
    }
    out.println(sum)
}

private fun puzzleInput(): String = //
    object {}::class.java //
        .getResourceAsStream("/puzzle-input.txt")!! //
        .bufferedReader(UTF_8)
        .readText()