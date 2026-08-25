// port-lint: tests dragonfly/mod.rs
package io.github.kotlinmania.osinfo.dragonfly

import io.github.kotlinmania.osinfo.OsType
import kotlin.test.Test
import kotlin.test.assertEquals

class DragonflyTest {
    @Test
    fun osType() {
        val version = currentPlatform { "6.0" }
        assertEquals(OsType.DragonFly, version.osType)
    }
}
