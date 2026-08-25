// port-lint: tests linux/mod.rs
package io.github.kotlinmania.osinfo.linux

import io.github.kotlinmania.osinfo.OsType
import kotlin.test.Test
import kotlin.test.assertTrue

class LinuxTest {
    @Test
    fun osType() {
        val version = currentPlatform()
        val validTypes =
            setOf(
                OsType.AlmaLinux,
                OsType.Alpaquita,
                OsType.Alpine,
                OsType.ALTLinux,
                OsType.Amazon,
                OsType.AOSC,
                OsType.Arch,
                OsType.Artix,
                OsType.Bluefin,
                OsType.CachyOS,
                OsType.CentOS,
                OsType.Debian,
                OsType.Elementary,
                OsType.EndeavourOS,
                OsType.Fedora,
                OsType.Garuda,
                OsType.Gentoo,
                OsType.Kali,
                OsType.Linux,
                OsType.Mabox,
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
                OsType.Raspbian,
                OsType.Redhat,
                OsType.RedHatEnterprise,
                OsType.RockyLinux,
                OsType.Solus,
                OsType.SUSE,
                OsType.Ubuntu,
                OsType.Ultramarine,
                OsType.Void,
                OsType.Zorin,
                OsType.Mint,
            )
        assertTrue(version.osType in validTypes, "Unexpected OS type: ${version.osType}")
    }
}
