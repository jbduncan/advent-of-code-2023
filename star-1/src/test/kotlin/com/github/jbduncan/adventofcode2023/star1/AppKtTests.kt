package com.github.jbduncan.adventofcode2023.star1

import com.github.jbduncan.adventofcode2023.star1.execute
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
    inner class GivenExampleStar1Input {

        @Nested
        inner class WhenRunningApp {

            @Test
            fun thenItReturns142() {
                val puzzleInput =
                    """
                    1abc2
                    pqr3stu8vwx
                    a1b2c3d4e5f
                    treb7uchet
                    """.trimIndent()
                val out = executeAndReturnStdOut(puzzleInput)
                assertThat(out).isEqualTo("142")
            }
        }
    }

    @Nested
    inner class GivenMyStar1Input {

        @Nested
        inner class WhenRunningApp {

            @Test
            fun thenItReturns54_605() {
                val puzzleInput = puzzleInput()
                val out = executeAndReturnStdOut(puzzleInput)
                assertThat(out).isEqualTo("54605")
            }
        }
    }
}