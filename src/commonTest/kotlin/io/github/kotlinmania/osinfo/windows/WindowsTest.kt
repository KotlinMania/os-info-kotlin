// port-lint: tests windows/mod.rs
package io.github.kotlinmania.osinfo.windows

import io.github.kotlinmania.osinfo.OsType
import io.github.kotlinmania.prettyassertions.assertEq
import kotlin.test.Test
import kotlin.test.assertNotNull

class WindowsTest {
    @Test
    fun osType() {
        val version = currentPlatform()
        assertEq(OsType.Windows, version.osType())
        assertNotNull(version.edition())
    }
}
