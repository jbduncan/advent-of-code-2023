package com.github.jbduncan.adventofcode2023.star16

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
    inner class GivenFirstExampleStar6Input {

        @Nested
        inner class WhenRunningApp {

            @Test
            fun thenItReturns2() {
                val puzzleInput =
                    """
                    RL

                    AAA = (BBB, CCC)
                    BBB = (DDD, EEE)
                    CCC = (ZZZ, GGG)
                    DDD = (DDD, DDD)
                    EEE = (EEE, EEE)
                    GGG = (GGG, GGG)
                    ZZZ = (ZZZ, ZZZ)
                    """.trimIndent()
                val out = executeAndReturnStdOut(puzzleInput)
                assertThat(out).isEqualTo("2")
            }
        }
    }

    @Nested
    inner class GivenSecondExampleStar6Input {

        @Nested
        inner class WhenRunningApp {

            @Test
            fun thenItReturns6() {
                val puzzleInput =
                    """
                    LLR

                    AAA = (BBB, BBB)
                    BBB = (AAA, ZZZ)
                    ZZZ = (ZZZ, ZZZ)
                    """.trimIndent()
                val out = executeAndReturnStdOut(puzzleInput)
                assertThat(out).isEqualTo("6")
            }
        }
    }
}