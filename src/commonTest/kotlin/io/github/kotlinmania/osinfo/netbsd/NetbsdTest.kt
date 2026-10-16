// port-lint: tests netbsd/mod.rs
package io.github.kotlinmania.osinfo.netbsd

import io.github.kotlinmania.osinfo.OsType
import io.github.kotlinmania.prettyassertions.assertEq
import kotlin.test.Test

class NetbsdTest {
    @Test
    fun osType() {
        val version = currentPlatform { "NetBSD" }
        assertEq(OsType.NetBSD, version.osType)
    }
}
