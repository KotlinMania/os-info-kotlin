// port-lint: source linux/lsb_release.rs
package io.github.kotlinmania.osinfo.linux

import io.github.kotlinmania.osinfo.Info
import io.github.kotlinmania.osinfo.Matcher
import io.github.kotlinmania.osinfo.OsType
import io.github.kotlinmania.osinfo.Version

// spell-checker:ignore codename, noarch, rhel, ootpa, maipo

data class LsbRelease(
    val distribution: String?,
    val version: String?,
    val codename: String?,
)

fun get(outputProvider: (() -> String?)? = null): Info? {
    val release = retrieve(outputProvider) ?: return null

    val version =
        when (val v = release.version) {
            "rolling" -> Version.Rolling(null)
            null -> Version.Unknown
            else -> Version.fromString(v)
        }

    val osType =
        when (release.distribution) {
            "Alpaquita" -> OsType.Alpaquita
            "ALT Linux" -> OsType.ALTLinux
            "Amazon", "AmazonAMI" -> OsType.Amazon
            "AOSC" -> OsType.AOSC
            "Arch" -> OsType.Arch
            "Artix" -> OsType.Artix
            "Bluefin" -> OsType.Bluefin
            "cachyos" -> OsType.CachyOS
            "CentOS" -> OsType.CentOS
            "Debian" -> OsType.Debian
            "Elementary" -> OsType.Elementary
            "EndeavourOS" -> OsType.EndeavourOS
            "Fedora", "Fedora Linux" -> OsType.Fedora
            "Garuda" -> OsType.Garuda
            "Gentoo" -> OsType.Gentoo
            "Kali" -> OsType.Kali
            "Linuxmint" -> OsType.Mint
            "MaboxLinux" -> OsType.Mabox
            "ManjaroLinux", "Manjaro-ARM" -> OsType.Manjaro
            "Mariner" -> OsType.Mariner
            "NixOS" -> OsType.NixOS
            "NobaraLinux" -> OsType.Nobara
            "Uos" -> OsType.Uos
            "OpenCloudOS" -> OsType.OpenCloudOS
            "openEuler" -> OsType.OpenEuler
            "openSUSE" -> OsType.OpenSUSE
            "OracleServer" -> OsType.OracleLinux
            "Pika" -> OsType.PikaOS
            "Pop" -> OsType.Pop
            "Raspbian" -> OsType.Raspbian
            "RedHatEnterprise", "RedHatEnterpriseServer" -> OsType.RedHatEnterprise
            "Solus" -> OsType.Solus
            "SUSE" -> OsType.SUSE
            "Ubuntu" -> OsType.Ubuntu
            "UltramarineLinux" -> OsType.Ultramarine
            "VoidLinux" -> OsType.Void
            "Zorin" -> OsType.Zorin
            else -> return null
        }

    return Info(
        osType = osType,
        version = version,
        codename = release.codename,
    )
}

fun retrieve(outputProvider: (() -> String?)? = null): LsbRelease? {
    val output = outputProvider?.invoke() ?: return null
    return parse(output)
}

fun parse(output: String): LsbRelease {
    val distribution = Matcher.PrefixedWord("Distributor ID:").find(output)
    val codename = Matcher.PrefixedWord("Codename:").find(output)?.takeIf { it != "n/a" }
    val version = Matcher.PrefixedVersion("Release:").find(output)

    return LsbRelease(
        distribution = distribution,
        version = version,
        codename = codename,
    )
}
