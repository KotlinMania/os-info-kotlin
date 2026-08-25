// port-lint: tests unknown/mod.rs
package io.github.kotlinmania.osinfo.unknown

import io.github.kotlinmania.osinfo.OsType
import kotlin.test.Test
import kotlin.test.assertEquals

class UnknownTest {
    @Test
    fun osType() {
        val version = currentPlatform()
        assertEquals(OsType.Unknown, version.osType)
    }
}
