// port-lint: tests emscripten/mod.rs
package io.github.kotlinmania.osinfo.emscripten

import io.github.kotlinmania.osinfo.OsType
import io.github.kotlinmania.prettyassertions.assertEq
import kotlin.test.Test

class EmscriptenTest {
    @Test
    fun osType() {
        val version = currentPlatform()
        assertEq(OsType.Emscripten, version.osType)
    }
}
