package com.github.jbduncan.adventofcode2023.star17

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
    inner class GivenExampleStar6Input {

        @Nested
        inner class WhenRunningApp {

            @Test
            fun thenItReturns467_835() {
                val puzzleInput =
                    """
                    467..114..
                    ...*......
                    ..35..633.
                    ......#...
                    617*......
                    .....+.58.
                    ..592.....
                    ......755.
                    ...${'$'}.*....
                    .664.598..
                    """.trimIndent()
                val out = executeAndReturnStdOut(puzzleInput)
                assertThat(out).isEqualTo("467835")
            }
        }
    }
}