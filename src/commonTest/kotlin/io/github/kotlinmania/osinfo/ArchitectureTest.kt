// port-lint: tests architecture.rs
package io.github.kotlinmania.osinfo

import kotlin.test.Test
import kotlin.test.assertTrue

class ArchitectureTest {
    @Test
    fun unameNonempty() {
        val valResult = getArchitecture()
        if (valResult != null) {
            assertTrue(valResult.isNotEmpty())
        }
    }
}
