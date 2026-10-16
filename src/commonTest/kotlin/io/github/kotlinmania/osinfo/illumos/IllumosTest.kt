// port-lint: tests illumos/mod.rs
package io.github.kotlinmania.osinfo.illumos

import io.github.kotlinmania.osinfo.OsType
import io.github.kotlinmania.prettyassertions.assertEq
import kotlin.test.Test

class IllumosTest {
    @Test
    fun osType() {
        val version = currentPlatform()
        assertEq(OsType.Illumos, version.osType())
    }
}
