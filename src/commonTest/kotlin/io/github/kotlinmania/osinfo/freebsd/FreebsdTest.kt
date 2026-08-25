// port-lint: tests freebsd/mod.rs
package io.github.kotlinmania.osinfo.freebsd

import io.github.kotlinmania.osinfo.OsType
import kotlin.test.Test
import kotlin.test.assertEquals

class FreebsdTest {
    @Test
    fun osType() {
        val version = currentPlatform(sysnameProvider = { "FreeBSD" }, releaseProvider = { "13.0-RELEASE" })
        assertEquals(OsType.FreeBSD, version.osType)
    }
}
