// port-lint: tests ios/mod.rs
package io.github.kotlinmania.osinfo.ios

import io.github.kotlinmania.osinfo.OsType
import kotlin.test.Test
import kotlin.test.assertEquals

class IosTest {
    @Test
    fun osType() {
        val version = currentPlatform { "17.0" }
        assertEquals(OsType.Ios, version.osType)
    }
}
