// port-lint: tests macos/mod.rs
package io.github.kotlinmania.osinfo.macos

import io.github.kotlinmania.osinfo.OsType
import io.github.kotlinmania.osinfo.Version
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class MacosTest {
    @Test
    fun osType() {
        val version = currentPlatform(commandReleaseProvider = { swVersOutput() })
        assertEquals(OsType.Macos, version.osType)
    }

    @Test
    fun osVersion() {
        val ver = version { parse(swVersOutput()) }
        assertNotEquals(Version.Unknown, ver)
    }

    @Test
    fun stringProductVersion() {
        val version = productVersion(commandReleaseProvider = { parse(swVersOutput()) })
        assertEquals("10.10.5", version)
    }

    @Test
    fun parseVersion() {
        val parseOutput = parse(swVersOutput())
        assertEquals("10.10.5", parseOutput)
    }

    private fun swVersOutput(): String =
        "ProductName:\tMac OS X\n" +
            "ProductVersion:\t10.10.5\n" +
            "BuildVersion:\t14F27"

    @Test
    fun parseBetaVersion() {
        val parseOutput = parse(swVersOutputBeta())
        assertEquals("10.15", parseOutput)
    }

    private fun swVersOutputBeta(): String =
        "ProductName:\tMac OS X\n" +
            "ProductVersion:\t10.15\n" +
            "BuildVersion:\t19A546d"

    @Test
    fun parseDoubleDigitPatchVersion() {
        val parseOutput = parse(swVersOutputDoubleDigitPatchVersion())
        assertEquals("10.15.21", parseOutput)
    }

    private fun swVersOutputDoubleDigitPatchVersion(): String =
        "ProductName:\tMac OS X\n" +
            "ProductVersion:\t10.15.21\n" +
            "BuildVersion:\tABCD123"
}
