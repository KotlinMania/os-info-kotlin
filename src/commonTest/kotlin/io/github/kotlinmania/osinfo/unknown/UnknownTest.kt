// port-lint: tests unknown/mod.rs
package io.github.kotlinmania.osinfo.unknown

import io.github.kotlinmania.osinfo.OsType
import io.github.kotlinmania.prettyassertions.assertEq
import kotlin.test.Test

class UnknownTest {
    @Test
    fun osType() {
        val version = currentPlatform()
        assertEq(OsType.Unknown, version.osType)
    }
}
