// port-lint: tests bitness.rs
package io.github.kotlinmania.osinfo

import io.github.kotlinmania.prettyassertions.assertEq
import io.github.kotlinmania.prettyassertions.assertNe
import kotlin.test.Test

class BitnessTest {
    @Test
    fun getBitness() {
        val b = Bitness.get()
        assertNe(b, Bitness.Unknown)
    }

    @Test
    fun display() {
        val data =
            listOf(
                Bitness.Unknown to "unknown bitness",
                Bitness.X32 to "32-bit",
                Bitness.X64 to "64-bit",
            )

        for ((bitness, expected) in data) {
            assertEq(bitness.toString(), expected)
        }
    }
}
