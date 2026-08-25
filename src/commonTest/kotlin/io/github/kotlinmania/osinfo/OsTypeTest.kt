// port-lint: tests os_type.rs
package io.github.kotlinmania.osinfo

import kotlin.test.Test
import kotlin.test.assertEquals

class OsTypeTest {
    @Test
    fun default() {
        assertEquals(OsType.Unknown, OsType.Unknown)
    }

    @Test
    fun display() {
        val data =
            listOf(
                OsType.AIX to "AIX",
                OsType.AlmaLinux to "AlmaLinux",
                OsType.Alpaquita to "Alpaquita Linux",
                OsType.Alpine to "Alpine Linux",
                OsType.ALTLinux to "ALT Linux",
                OsType.Amazon to "Amazon Linux AMI",
                OsType.Android to "Android",
                OsType.AOSC to "AOSC OS",
                OsType.Arch to "Arch Linux",
                OsType.Artix to "Artix Linux",
                OsType.Bluefin to "Bluefin",
                OsType.CachyOS to "CachyOS Linux",
                OsType.CentOS to "CentOS",
                OsType.Cygwin to "Cygwin",
                OsType.Debian to "Debian",
                OsType.DragonFly to "DragonFly BSD",
                OsType.Elementary to "Elementary OS",
                OsType.Emscripten to "Emscripten",
                OsType.EndeavourOS to "EndeavourOS",
                OsType.Fedora to "Fedora",
                OsType.FreeBSD to "FreeBSD",
                OsType.Garuda to "Garuda Linux",
                OsType.Gentoo to "Gentoo Linux",
                OsType.HardenedBSD to "HardenedBSD",
                OsType.Illumos to "illumos",
                OsType.InstantOS to "instantOS",
                OsType.Ios to "iOS",
                OsType.Kali to "Kali Linux",
                OsType.Linux to "Linux",
                OsType.Mabox to "Mabox",
                OsType.Macos to "Mac OS",
                OsType.Manjaro to "Manjaro",
                OsType.Mariner to "Mariner",
                OsType.MidnightBSD to "Midnight BSD",
                OsType.Mint to "Linux Mint",
                OsType.NetBSD to "NetBSD",
                OsType.NixOS to "NixOS",
                OsType.Nobara to "Nobara Linux",
                OsType.OpenCloudOS to "OpenCloudOS",
                OsType.OpenBSD to "OpenBSD",
                OsType.OpenEuler to "EulerOS",
                OsType.OpenSUSE to "openSUSE",
                OsType.OracleLinux to "Oracle Linux",
                OsType.PikaOS to "PikaOS",
                OsType.Pop to "Pop!_OS",
                OsType.Raspbian to "Raspberry Pi OS",
                OsType.Redhat to "Red Hat Linux",
                OsType.RedHatEnterprise to "Red Hat Enterprise Linux",
                OsType.Redox to "Redox",
                OsType.RockyLinux to "Rocky Linux",
                OsType.Solus to "Solus",
                OsType.SUSE to "SUSE Linux Enterprise Server",
                OsType.Ubuntu to "Ubuntu",
                OsType.Ultramarine to "Ultramarine Linux",
                OsType.Unknown to "Unknown",
                OsType.Uos to "UOS",
                OsType.Void to "Void Linux",
                OsType.Zorin to "Zorin OS",
                OsType.Windows to "Windows",
            )

        for ((t, expected) in data) {
            assertEquals(expected, t.toString())
        }
    }
}
