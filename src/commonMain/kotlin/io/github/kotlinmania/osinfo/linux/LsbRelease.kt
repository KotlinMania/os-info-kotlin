// port-lint: source linux/lsb_release.rs
package io.github.kotlinmania.osinfo.linux

import io.github.kotlinmania.libc.unix.access
import io.github.kotlinmania.libc.unix.bsd.popen
import io.github.kotlinmania.libc.unix.fgets
import io.github.kotlinmania.libc.unix.linuxlike.F_OK
import io.github.kotlinmania.libc.unix.pclose
import io.github.kotlinmania.log.debug
import io.github.kotlinmania.log.formatArgs
import io.github.kotlinmania.log.trace
import io.github.kotlinmania.osinfo.Info
import io.github.kotlinmania.osinfo.Matcher
import io.github.kotlinmania.osinfo.OsType
import io.github.kotlinmania.osinfo.Version

// spell-checker:ignore codename, noarch, rhel, ootpa, maipo

internal data class LsbRelease(
    val distribution: String?,
    val version: String?,
    val codename: String?,
) {
    companion object {
        internal fun get(): Info? {
            val release = retrieve() ?: return null

            val version =
                when (val v = release.version) {
                    "rolling" -> Version.Rolling(null)
                    null -> Version.Unknown
                    else -> Version.fromString(v)
                }

            val osType =
                when (release.distribution) {
                    "Alpaquita" -> {
                        OsType.Alpaquita
                    }

                    "ALT Linux" -> {
                        OsType.ALTLinux
                    }

                    "Amazon", "AmazonAMI" -> {
                        OsType.Amazon
                    }

                    "AOSC" -> {
                        OsType.AOSC
                    }

                    "Arch" -> {
                        OsType.Arch
                    }

                    "Artix" -> {
                        OsType.Artix
                    }

                    "Bluefin" -> {
                        OsType.Bluefin
                    }

                    "cachyos" -> {
                        OsType.CachyOS
                    }

                    "CentOS" -> {
                        OsType.CentOS
                    }

                    "Debian" -> {
                        // Check if it's actually Raspberry Pi OS
                        if (access("/etc/rpi-issue", F_OK) == 0) {
                            OsType.Raspbian
                        } else {
                            OsType.Debian
                        }
                    }

                    "Elementary" -> {
                        OsType.Elementary
                    }

                    "EndeavourOS" -> {
                        OsType.EndeavourOS
                    }

                    "Fedora", "Fedora Linux" -> {
                        OsType.Fedora
                    }

                    "Garuda" -> {
                        OsType.Garuda
                    }

                    "Gentoo" -> {
                        OsType.Gentoo
                    }

                    "Kali" -> {
                        OsType.Kali
                    }

                    "Linuxmint" -> {
                        OsType.Mint
                    }

                    "MaboxLinux" -> {
                        OsType.Mabox
                    }

                    "ManjaroLinux", "Manjaro-ARM" -> {
                        OsType.Manjaro
                    }

                    "Mariner" -> {
                        OsType.Mariner
                    }

                    "NixOS" -> {
                        OsType.NixOS
                    }

                    "NobaraLinux" -> {
                        OsType.Nobara
                    }

                    "Uos" -> {
                        OsType.Uos
                    }

                    "OpenCloudOS" -> {
                        OsType.OpenCloudOS
                    }

                    "openEuler" -> {
                        OsType.OpenEuler
                    }

                    "openSUSE" -> {
                        OsType.OpenSUSE
                    }

                    "OracleServer" -> {
                        OsType.OracleLinux
                    }

                    "Pika" -> {
                        OsType.PikaOS
                    }

                    "Pop" -> {
                        OsType.Pop
                    }

                    "Raspbian" -> {
                        OsType.Raspbian
                    }

                    "RedHatEnterprise", "RedHatEnterpriseServer" -> {
                        OsType.RedHatEnterprise
                    }

                    "Solus" -> {
                        OsType.Solus
                    }

                    "SUSE" -> {
                        OsType.SUSE
                    }

                    "Ubuntu" -> {
                        OsType.Ubuntu
                    }

                    "UltramarineLinux" -> {
                        OsType.Ultramarine
                    }

                    "VoidLinux" -> {
                        OsType.Void
                    }

                    "Zorin" -> {
                        OsType.Zorin
                    }

                    // Return null here so FileRelease.get is then used.
                    else -> {
                        return null
                    }
                }

            return Info(
                osType = osType,
                version = version,
                codename = release.codename,
            )
        }
    }
}

internal fun retrieve(lsbOutput: String? = null): LsbRelease? {
    if (lsbOutput != null) {
        trace(formatArgs("lsb_release command returned {:?}", lsbOutput))
        return parse(lsbOutput)
    }
    val stream = popen("lsb_release -a 2>/dev/null", "r")
    if (stream == null) {
        debug(formatArgs("lsb_release command failed"))
        return null
    }
    val sb = StringBuilder()
    while (true) {
        val line = fgets(null, 1024, stream) ?: break
        sb.append(line)
    }
    val status = pclose(stream)
    val output = sb.toString()
    return if (status == 0 && output.isNotEmpty()) {
        trace(formatArgs("lsb_release command returned {:?}", output))
        parse(output)
    } else {
        debug(formatArgs("lsb_release command failed with status {:?}", status))
        null
    }
}

internal fun parse(output: String): LsbRelease {
    trace(formatArgs("Trying to parse {:?}", output))

    val distribution = Matcher.PrefixedWord(prefix = "Distributor ID:").find(output)

    val codename =
        Matcher
            .PrefixedWord(prefix = "Codename:")
            .find(output)
            ?.takeIf { c -> c != "n/a" }

    val version = Matcher.PrefixedVersion(prefix = "Release:").find(output)

    trace(
        formatArgs(
            "Parsed as '{:?}' distribution and '{:?}' version",
            distribution,
            version,
        ),
    )

    return LsbRelease(
        distribution = distribution,
        version = version,
        codename = codename,
    )
}
