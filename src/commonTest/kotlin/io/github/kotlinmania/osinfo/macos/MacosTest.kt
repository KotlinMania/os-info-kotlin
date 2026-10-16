// port-lint: tests macos/mod.rs
package io.github.kotlinmania.osinfo.macos

import io.github.kotlinmania.osinfo.OsType
import io.github.kotlinmania.osinfo.Version
import io.github.kotlinmania.prettyassertions.assertEq
import io.github.kotlinmania.prettyassertions.assertNe
import kotlin.test.Test
import kotlin.test.assertNotNull

class MacosTest {
    @Test
    fun osType() {
        val version = currentPlatform()
        assertEq(OsType.Macos, version.osType())
    }

    @Test
    fun osVersion() {
        val version = version()
        assertNe(Version.Unknown, version)
    }

    @Test
    fun stringProductVersion() {
        val version = productVersion()
        assertNotNull(version)
    }

    @Test
    fun parseVersion() {
        val parseOutput = parse(swVersOutput())
        assertEq("10.10.5", parseOutput)
    }

    @Test
    fun parseBetaVersion() {
        val parseOutput = parse(swVersOutputBeta())
        assertEq("10.15", parseOutput)
    }

    @Test
    fun parseDoubleDigitPatchVersion() {
        val parseOutput = parse(swVersOutputDoubleDigitPatchVersion())
        assertEq("10.15.21", parseOutput)
    }
}

private fun swVersOutput(): String =
    "ProductName:\tMac OS X\n" +
        "ProductVersion:\t10.10.5\n" +
        "BuildVersion:\t14F27"

private fun swVersOutputBeta(): String =
    "ProductName:\tMac OS X\n" +
        "ProductVersion:\t10.15\n" +
        "BuildVersion:\t19A546d"

private fun swVersOutputDoubleDigitPatchVersion(): String =
    "ProductName:\tMac OS X\n" +
        "ProductVersion:\t10.15.21\n" +
        "BuildVersion:\tABCD123"
