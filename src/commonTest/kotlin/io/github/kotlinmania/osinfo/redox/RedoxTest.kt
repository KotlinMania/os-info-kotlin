// port-lint: tests redox/mod.rs
package io.github.kotlinmania.osinfo.redox

import io.github.kotlinmania.osinfo.OsType
import io.github.kotlinmania.prettyassertions.assertEq
import kotlin.test.Test

class RedoxTest {
    @Test
    fun osType() {
        val version = currentPlatform { "0.8.0" }
        assertEq(OsType.Redox, version.osType)
    }
}
