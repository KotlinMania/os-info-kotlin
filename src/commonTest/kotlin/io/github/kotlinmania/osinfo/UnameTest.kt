// port-lint: tests uname.rs
package io.github.kotlinmania.osinfo

import kotlin.test.Test
import kotlin.test.assertTrue

class UnameTest {
    @Test
    fun unameNonempty() {
        val value = checkNotNull(uname(UnameField.Sysname)) { "uname failed" }
        assertTrue(value.isNotEmpty())
    }
}
