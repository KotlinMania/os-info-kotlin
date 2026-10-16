// port-lint: tests openbsd/mod.rs
package io.github.kotlinmania.osinfo.openbsd

import io.github.kotlinmania.osinfo.OsType
import io.github.kotlinmania.prettyassertions.assertEq
import kotlin.test.Test

class OpenbsdTest {
    @Test
    fun osType() {
        val version = currentPlatform { "7.0" }
        assertEq(OsType.OpenBSD, version.osType)
    }
}
