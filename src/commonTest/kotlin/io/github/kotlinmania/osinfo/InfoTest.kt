// port-lint: tests info.rs
package io.github.kotlinmania.osinfo

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class InfoTest {
    @Test
    fun unknown() {
        val info = Info.unknown()
        assertEquals(OsType.Unknown, info.osType)
        assertEquals(Version.Unknown, info.version)
        assertNull(info.edition)
        assertNull(info.codename)
        assertEquals(Bitness.Unknown, info.bitness)
        assertNull(info.architecture)
    }

    @Test
    fun withType() {
        val types =
            listOf(
                OsType.AIX,
                OsType.Redox,
                OsType.Alpaquita,
                OsType.Alpine,
                OsType.ALTLinux,
                OsType.Amazon,
                OsType.Android,
                OsType.AOSC,
                OsType.Arch,
                OsType.Artix,
                OsType.Bluefin,
                OsType.CachyOS,
                OsType.CentOS,
                OsType.Debian,
                OsType.Emscripten,
                OsType.EndeavourOS,
                OsType.Fedora,
                OsType.Gentoo,
                OsType.Linux,
                OsType.Macos,
                OsType.Manjaro,
                OsType.Mariner,
                OsType.NixOS,
                OsType.Nobara,
                OsType.Uos,
                OsType.OpenCloudOS,
                OsType.OpenEuler,
                OsType.OpenSUSE,
                OsType.OracleLinux,
                OsType.PikaOS,
                OsType.Pop,
                OsType.Redhat,
                OsType.RedHatEnterprise,
                OsType.Redox,
                OsType.Solus,
                OsType.SUSE,
                OsType.Ubuntu,
                OsType.Ultramarine,
                OsType.Void,
                OsType.Mint,
                OsType.Unknown,
                OsType.Windows,
            )

        for (t in types) {
            val info = Info.withType(t)
            assertEquals(t, info.osType)
        }
    }

    @Test
    fun default() {
        assertEquals(Info.default(), Info.unknown())
    }

    @Test
    fun display() {
        val data =
            listOf(
                // All unknown.
                Info.unknown() to "Unknown [unknown bitness]",
                // Type.
                Info(osType = OsType.Redox) to "Redox [unknown bitness]",
                // Type and version.
                Info(osType = OsType.Linux, version = Version.Semantic(2uL, 3uL, 4uL)) to "Linux 2.3.4 [unknown bitness]",
                Info(osType = OsType.AOSC, version = Version.Semantic(12uL, 1uL, 3uL)) to "AOSC OS 12.1.3 [unknown bitness]",
                Info(osType = OsType.Arch, version = Version.Rolling(null)) to "Arch Linux Rolling Release [unknown bitness]",
                Info(osType = OsType.Artix, version = Version.Rolling(null)) to "Artix Linux Rolling Release [unknown bitness]",
                Info(osType = OsType.Manjaro, version = Version.Rolling("2020.05.24")) to "Manjaro Rolling Release (2020.05.24) [unknown bitness]",
                Info(osType = OsType.Windows, version = Version.Custom("Special Version")) to "Windows Special Version [unknown bitness]",
                // Bitness.
                Info(bitness = Bitness.X32) to "Unknown [32-bit]",
                Info(bitness = Bitness.X64) to "Unknown [64-bit]",
                // All info.
                Info(
                    osType = OsType.Macos,
                    version = Version.Semantic(10uL, 2uL, 0uL),
                    edition = "edition",
                    codename = "codename",
                    bitness = Bitness.X64,
                    architecture = "architecture",
                ) to "Mac OS 10.2.0 (edition) (codename) [64-bit]",
            )

        for ((info, expected) in data) {
            assertEquals(expected, info.toString())
        }
    }
}
