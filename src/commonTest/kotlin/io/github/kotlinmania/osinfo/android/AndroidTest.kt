// port-lint: tests android/mod.rs
package io.github.kotlinmania.osinfo.android

import io.github.kotlinmania.osinfo.OsType
import io.github.kotlinmania.prettyassertions.assertEq
import kotlin.test.Test

class AndroidTest {
    @Test
    fun osType() {
        val version = currentPlatform()
        assertEq(OsType.Android, version.osType())
    }
}
