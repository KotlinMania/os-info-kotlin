// port-lint: tests cygwin/mod.rs
package io.github.kotlinmania.osinfo.cygwin

import io.github.kotlinmania.osinfo.OsType
import io.github.kotlinmania.prettyassertions.assertEq
import kotlin.test.Test

class CygwinTest {
    @Test
    fun osType() {
        val version = currentPlatform()
        assertEq(OsType.Cygwin, version.osType())
    }
}
