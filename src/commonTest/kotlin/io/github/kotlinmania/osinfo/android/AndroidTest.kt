// port-lint: tests android/mod.rs
package io.github.kotlinmania.osinfo.android

import io.github.kotlinmania.osinfo.OsType
import kotlin.test.Test
import kotlin.test.assertEquals

class AndroidTest {
    @Test
    fun osType() {
        val version = currentPlatform { "14.0.0" }
        assertEquals(OsType.Android, version.osType)
    }
}
