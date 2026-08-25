// port-lint: tests aix/mod.rs
package io.github.kotlinmania.osinfo.aix

import io.github.kotlinmania.osinfo.OsType
import kotlin.test.Test
import kotlin.test.assertEquals

class AixTest {
    @Test
    fun osType() {
        val version = currentPlatform(majorProvider = { "7" }, minorProvider = { "2" }, sysnameProvider = { "AIX" })
        assertEquals(OsType.AIX, version.osType)
    }
}
