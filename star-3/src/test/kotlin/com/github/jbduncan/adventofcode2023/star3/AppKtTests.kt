package com.github.jbduncan.adventofcode2023.star3

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

    @Nested
    inner class GivenExampleStar3Input {

        @Nested
        inner class WhenRunningApp {

            @Test
            fun thenItReturns8() {
                val puzzleInput =
                    """
                    Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
                    Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
                    Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
                    Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
                    Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
                    """.trimIndent()
                val out = executeAndReturnStdOut(puzzleInput)
                assertThat(out).isEqualTo("8")
            }
        }
    }
}