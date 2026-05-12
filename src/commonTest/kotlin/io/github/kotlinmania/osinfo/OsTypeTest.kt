// port-lint: tests os_type.rs
package io.github.kotlinmania.osinfo

import kotlin.test.Test
import kotlin.test.assertEquals

class OsTypeTest {
    @Test
    fun default() {
        assertEquals(Type.Unknown, Type.default())
    }

    @Test
    fun display() {
        val data = listOf(
            Type.AIX to "AIX",
            Type.AlmaLinux to "AlmaLinux",
            Type.Alpaquita to "Alpaquita Linux",
            Type.Alpine to "Alpine Linux",
            Type.ALTLinux to "ALT Linux",
            Type.Amazon to "Amazon Linux AMI",
            Type.Android to "Android",
            Type.AOSC to "AOSC OS",
            Type.Arch to "Arch Linux",
            Type.Artix to "Artix Linux",
            Type.Bluefin to "Bluefin",
            Type.CachyOS to "CachyOS Linux",
            Type.CentOS to "CentOS",
            Type.Cygwin to "Cygwin",
            Type.Debian to "Debian",
            Type.DragonFly to "DragonFly BSD",
            Type.Elementary to "Elementary OS",
            Type.Emscripten to "Emscripten",
            Type.EndeavourOS to "EndeavourOS",
            Type.Fedora to "Fedora",
            Type.FreeBSD to "FreeBSD",
            Type.Garuda to "Garuda Linux",
            Type.Gentoo to "Gentoo Linux",
            Type.HardenedBSD to "HardenedBSD",
            Type.Illumos to "illumos",
            Type.InstantOS to "instantOS",
            Type.Ios to "iOS",
            Type.Kali to "Kali Linux",
            Type.Linux to "Linux",
            Type.Mabox to "Mabox",
            Type.Macos to "Mac OS",
            Type.Manjaro to "Manjaro",
            Type.Mariner to "Mariner",
            Type.MidnightBSD to "Midnight BSD",
            Type.Mint to "Linux Mint",
            Type.NetBSD to "NetBSD",
            Type.NixOS to "NixOS",
            Type.Nobara to "Nobara Linux",
            Type.OpenCloudOS to "OpenCloudOS",
            Type.OpenBSD to "OpenBSD",
            Type.OpenEuler to "EulerOS",
            Type.OpenSUSE to "openSUSE",
            Type.OracleLinux to "Oracle Linux",
            Type.PikaOS to "PikaOS",
            Type.Pop to "Pop!_OS",
            Type.Raspbian to "Raspberry Pi OS",
            Type.Redhat to "Red Hat Linux",
            Type.RedHatEnterprise to "Red Hat Enterprise Linux",
            Type.Redox to "Redox",
            Type.RockyLinux to "Rocky Linux",
            Type.Solus to "Solus",
            Type.SUSE to "SUSE Linux Enterprise Server",
            Type.Ubuntu to "Ubuntu",
            Type.Ultramarine to "Ultramarine Linux",
            Type.Unknown to "Unknown",
            Type.Uos to "UOS",
            Type.Void to "Void Linux",
            Type.Zorin to "Zorin OS",
            Type.Windows to "Windows",
        )

        for ((t, expected) in data) {
            assertEquals(expected, t.toString())
        }
    }
}
