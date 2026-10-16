// port-lint: tests aix/mod.rs
package io.github.kotlinmania.osinfo.aix

import io.github.kotlinmania.osinfo.OsType
import io.github.kotlinmania.prettyassertions.assertEq
import kotlin.test.Test

class AixTest {
    @Test
    fun osType() {
        val version = currentPlatform(majorProvider = { "7" }, minorProvider = { "2" }, sysnameProvider = { "AIX" })
        assertEq(OsType.AIX, version.osType)
    }
}
