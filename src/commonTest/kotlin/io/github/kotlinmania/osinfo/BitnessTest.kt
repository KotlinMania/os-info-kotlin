// port-lint: tests bitness.rs
package io.github.kotlinmania.osinfo

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class BitnessTest {
    @Test
    fun getBitness() {
        assertNotEquals(
            Bitness.Unknown,
            io.github.kotlinmania.osinfo
                .getBitness(),
        )
    }

    @Test
    fun display() {
        assertEquals("unknown bitness", Bitness.Unknown.toString())
        assertEquals("32-bit", Bitness.X32.toString())
        assertEquals("64-bit", Bitness.X64.toString())
    }
}
