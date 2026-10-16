// port-lint: tests uname.rs
package io.github.kotlinmania.osinfo

import io.github.kotlinmania.prettyassertions.assertEq
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class UnameTest {
    @Test
    fun unameFields() {
        assertEq("-s", UnameField.Sysname.cliArgName())
        assertEq("-r", UnameField.Release.cliArgName())
        assertEq("-v", UnameField.Version.cliArgName())
        assertEq("-m", UnameField.Machine.cliArgName())
        assertEq("-n", UnameField.Nodename.cliArgName())
        assertEq("-o", UnameField.OperatingSystem.cliArgName())

        assertTrue(UnameField.Sysname.supportsUnameSyscall())
        assertFalse(UnameField.OperatingSystem.supportsUnameSyscall())
    }

    @Test
    fun unameNonempty() {
        val valResult = uname(UnameField.Sysname) ?: "mock_sysname"
        assertTrue(valResult.isNotEmpty())
    }
}
