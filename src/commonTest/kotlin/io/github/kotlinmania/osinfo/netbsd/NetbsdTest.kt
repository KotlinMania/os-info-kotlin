// port-lint: tests netbsd/mod.rs
package io.github.kotlinmania.osinfo.netbsd

import io.github.kotlinmania.osinfo.OsType
import kotlin.test.Test
import kotlin.test.assertEquals

class NetbsdTest {
    @Test
    fun osType() {
        val version = currentPlatform { "NetBSD" }
        assertEquals(OsType.NetBSD, version.osType)
    }
}
