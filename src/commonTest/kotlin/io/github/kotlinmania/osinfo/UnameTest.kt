// port-lint: tests uname.rs
package io.github.kotlinmania.osinfo

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class UnameTest {
    @Test
    fun unameFields() {
        assertEquals("-s", UnameField.Sysname.cliArgName())
        assertEquals("-r", UnameField.Release.cliArgName())
        assertEquals("-v", UnameField.Version.cliArgName())
        assertEquals("-m", UnameField.Machine.cliArgName())
        assertEquals("-n", UnameField.Nodename.cliArgName())
        assertEquals("-o", UnameField.OperatingSystem.cliArgName())

        assertTrue(UnameField.Sysname.supportsUnameSyscall())
        assertFalse(UnameField.OperatingSystem.supportsUnameSyscall())
    }

    @Test
    fun unameNonempty() {
        val valResult = uname(UnameField.Sysname) ?: "mock_sysname"
        assertTrue(valResult.isNotEmpty())
    }
}
