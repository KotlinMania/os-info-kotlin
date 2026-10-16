// port-lint: tests freebsd/mod.rs
package io.github.kotlinmania.osinfo.freebsd

import io.github.kotlinmania.osinfo.OsType
import io.github.kotlinmania.prettyassertions.assertEq
import kotlin.test.Test

class FreebsdTest {
    @Test
    fun osType() {
        val version = currentPlatform()
        assertEq(OsType.FreeBSD, version.osType())
    }
}
