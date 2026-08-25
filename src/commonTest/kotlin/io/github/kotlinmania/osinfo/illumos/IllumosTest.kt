// port-lint: tests illumos/mod.rs
package io.github.kotlinmania.osinfo.illumos

import io.github.kotlinmania.osinfo.OsType
import kotlin.test.Test
import kotlin.test.assertEquals

class IllumosTest {
    @Test
    fun osType() {
        val version = currentPlatform(versionProvider = { "5.11" }, osProvider = { "illumos" })
        assertEquals(OsType.Illumos, version.osType)
    }
}
