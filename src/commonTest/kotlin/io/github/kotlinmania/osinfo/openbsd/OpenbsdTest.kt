// port-lint: tests openbsd/mod.rs
package io.github.kotlinmania.osinfo.openbsd

import io.github.kotlinmania.osinfo.OsType
import kotlin.test.Test
import kotlin.test.assertEquals

class OpenbsdTest {
    @Test
    fun osType() {
        val version = currentPlatform { "7.0" }
        assertEquals(OsType.OpenBSD, version.osType)
    }
}
