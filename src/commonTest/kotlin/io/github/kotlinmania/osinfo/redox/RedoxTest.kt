// port-lint: tests redox/mod.rs
package io.github.kotlinmania.osinfo.redox

import io.github.kotlinmania.osinfo.OsType
import kotlin.test.Test
import kotlin.test.assertEquals

class RedoxTest {
    @Test
    fun osType() {
        val version = currentPlatform { "0.8.0" }
        assertEquals(OsType.Redox, version.osType)
    }
}
