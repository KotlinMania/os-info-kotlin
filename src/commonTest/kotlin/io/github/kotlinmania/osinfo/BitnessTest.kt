// port-lint: tests bitness.rs
package io.github.kotlinmania.osinfo

import kotlin.test.Test
import kotlin.test.assertEquals

class BitnessTest {
    @Test
    fun display() {
        val data = listOf(
            Bitness.Unknown to "unknown bitness",
            Bitness.X32 to "32-bit",
            Bitness.X64 to "64-bit",
        )

        for ((bitness, expected) in data) {
            assertEquals(expected, bitness.toString())
        }
    }
}
