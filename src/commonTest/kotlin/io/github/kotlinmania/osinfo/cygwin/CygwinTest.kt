// port-lint: tests cygwin/mod.rs
package io.github.kotlinmania.osinfo.cygwin

import io.github.kotlinmania.osinfo.OsType
import kotlin.test.Test
import kotlin.test.assertEquals

class CygwinTest {
    @Test
    fun osType() {
        val version = currentPlatform { "3.3.4" }
        assertEquals(OsType.Cygwin, version.osType)
    }
}
