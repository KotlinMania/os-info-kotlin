// port-lint: tests emscripten/mod.rs
package io.github.kotlinmania.osinfo.emscripten

import io.github.kotlinmania.osinfo.OsType
import kotlin.test.Test
import kotlin.test.assertEquals

class EmscriptenTest {
    @Test
    fun osType() {
        val version = currentPlatform()
        assertEquals(OsType.Emscripten, version.osType)
    }
}
