// port-lint: tests freebsd/mod.rs
package io.github.kotlinmania.osinfo.freebsd

import io.github.kotlinmania.osinfo.OsType
import io.github.kotlinmania.prettyassertions.assertEq
import kotlin.test.Test

class FreebsdTest {
    @Test
    fun osType() {
        val version = currentPlatform(sysnameProvider = { "FreeBSD" }, releaseProvider = { "13.0-RELEASE" })
        assertEq(OsType.FreeBSD, version.osType)
    }
}
