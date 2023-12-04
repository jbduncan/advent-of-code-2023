package com.github.jbduncan.adventofcode2023.star2

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.io.PrintWriter
import java.io.StringWriter

class AppKtTests {

    private fun executeAndReturnStdOut(puzzleInput: String): String {
        val out = StringWriter()
        execute(PrintWriter(out, true), puzzleInput)
        return out.toString().trim()
    }

    private fun puzzleInput(): String = //
        object {}::class.java //
            .getResourceAsStream("/puzzle-input.txt")!! //
            .bufferedReader(Charsets.UTF_8)
            .readText()

    @Nested
    inner class GivenExampleStar2Input {

        @Nested
        inner class WhenRunningApp {

            @Test
            fun thenItReturns281() {
                val puzzleInput =
                    """
                    two1nine
                    eightwothree
                    abcone2threexyz
                    xtwone3four
                    4nineeightseven2
                    zoneight234
                    7pqrstsixteen
                    """.trimIndent()
                val out = executeAndReturnStdOut(puzzleInput)
                assertThat(out).isEqualTo("281")
            }
        }
    }

    @Nested
    inner class GivenMyStar2Input {

        @Nested
        inner class WhenRunningApp {

            @Test
            fun thenItReturns55_429() {
                val puzzleInput = puzzleInput()
                val out = executeAndReturnStdOut(puzzleInput)
                assertThat(out).isEqualTo("55429")
            }
        }
    }
}