// port-lint: tests windows/mod.rs
package io.github.kotlinmania.osinfo.windows

import io.github.kotlinmania.osinfo.OsType
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class WindowsTest {
    @Test
    fun osType() {
        val version = currentPlatform()
        assertEquals(OsType.Windows, version.osType)
        assertNotNull(version.edition)
    }
}
