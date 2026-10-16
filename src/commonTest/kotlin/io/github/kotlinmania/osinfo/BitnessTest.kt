// port-lint: tests bitness.rs
package io.github.kotlinmania.osinfo

import io.github.kotlinmania.prettyassertions.assertEq
import io.github.kotlinmania.prettyassertions.assertNe
import kotlin.test.Test

class BitnessTest {
    @Test
    fun getBitness() {
        assertNe(
            Bitness.Unknown,
            io.github.kotlinmania.osinfo
                .getBitness(),
        )
    }

    @Test
    fun display() {
        assertEq("unknown bitness", Bitness.Unknown.toString())
        assertEq("32-bit", Bitness.X32.toString())
        assertEq("64-bit", Bitness.X64.toString())
    }
}
