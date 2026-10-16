// port-lint: tests architecture.rs
package io.github.kotlinmania.osinfo

import kotlin.test.Test
import kotlin.test.assertTrue

class ArchitectureTest {
    @Test
    fun unameNonempty() {
        val value = checkNotNull(Architecture.get()) { "architecture::get() failed" }
        assertTrue(value.isNotEmpty())
    }
}
