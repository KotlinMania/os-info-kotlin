// port-lint: tests aix/mod.rs
package io.github.kotlinmania.osinfo.aix

import io.github.kotlinmania.osinfo.OsType
import io.github.kotlinmania.prettyassertions.assertEq
import kotlin.test.Test

class AixTest {
    @Test
    fun osType() {
        val version = currentPlatform()
        assertEq(OsType.AIX, version.osType())
    }
}
