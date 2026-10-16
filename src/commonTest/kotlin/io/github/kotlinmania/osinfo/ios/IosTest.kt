// port-lint: tests ios/mod.rs
package io.github.kotlinmania.osinfo.ios

import io.github.kotlinmania.osinfo.OsType
import io.github.kotlinmania.prettyassertions.assertEq
import kotlin.test.Test

class IosTest {
    @Test
    fun osType() {
        val version = currentPlatform()
        assertEq(OsType.Ios, version.osType())
    }
}
