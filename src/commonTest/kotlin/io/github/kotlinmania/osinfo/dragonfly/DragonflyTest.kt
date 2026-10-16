// port-lint: tests dragonfly/mod.rs
package io.github.kotlinmania.osinfo.dragonfly

import io.github.kotlinmania.osinfo.OsType
import io.github.kotlinmania.prettyassertions.assertEq
import kotlin.test.Test

class DragonflyTest {
    @Test
    fun osType() {
        val version = currentPlatform()
        assertEq(OsType.DragonFly, version.osType())
    }
}
