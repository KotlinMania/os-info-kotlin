// port-lint: tests cygwin/mod.rs
package io.github.kotlinmania.osinfo.cygwin

import io.github.kotlinmania.osinfo.OsType
import io.github.kotlinmania.prettyassertions.assertEq
import kotlin.test.Test

class CygwinTest {
    @Test
    fun osType() {
        val version = currentPlatform { "3.3.4" }
        assertEq(OsType.Cygwin, version.osType)
    }
}
