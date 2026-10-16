// port-lint: source linux/file_release.rs
package io.github.kotlinmania.osinfo.linux

import io.github.kotlinmania.log.formatArgs
import io.github.kotlinmania.log.trace
import io.github.kotlinmania.log.warn
import io.github.kotlinmania.osinfo.Bitness
import io.github.kotlinmania.osinfo.Info
import io.github.kotlinmania.osinfo.Matcher
import io.github.kotlinmania.osinfo.OsType
import io.github.kotlinmania.osinfo.Version

// spell-checker:ignore sles, AOSCOS

internal fun get(): Info? = retrieve(DISTRIBUTIONS, "/")

internal fun retrieve(
    distributions: List<ReleaseInfo>,
    root: String,
    fileReleaseProvider: ((String) -> String?)? = null,
): Info? {
    for (releaseInfo in distributions) {
        val pathStr = if (root.endsWith("/")) "$root${releaseInfo.path}" else "$root/${releaseInfo.path}"
        val fileContent =
            if (fileReleaseProvider != null) {
                val provided = fileReleaseProvider(pathStr)
                if (provided == null) {
                    trace(formatArgs("Path '{}' doesn't exist", releaseInfo.path))
                    continue
                }
                provided
            } else {
                warn(formatArgs("Unable to read {:?} file", pathStr))
                continue
            }

        val osType = releaseInfo.osType(fileContent)

        // If osType is indeterminate, try the next releaseInfo
        if (osType == null) {
            continue
        }

        val version = releaseInfo.version(fileContent)

        return Info(
            // Non-null is OK here because of the osType null check above.
            osType = osType,
            version = version ?: Version.Unknown,
            bitness = Bitness.Unknown,
        )
    }

    // Failed to determine os info
    return null
}

/**
 * Struct containing information on how to parse distribution info from a release file.
 */
internal data class ReleaseInfo(
    /** Relative path to the release file this struct corresponds to from root. */
    val path: String,
    /** A closure that determines the os type from the release file contents. */
    val osType: (String) -> OsType?,
    /** A closure that determines the os version from the release file contents. */
    val version: (String) -> Version?,
) {
    fun fmt(): String = "ReleaseInfo(path=$path)"
}

/**
 * List of all supported distributions and the information on how to parse their version from the
 * release file.
 */
internal val DISTRIBUTIONS: List<ReleaseInfo> =
    listOf(
        // Keep this first; most modern distributions have this file.
        ReleaseInfo(
            path = "etc/os-release",
            osType = { release ->
                Matcher.KeyValue("ID").find(release)?.let { id ->
                    when (id) {
                        // os-release information collected from
                        // https://github.com/chef/os-release
                        "almalinux" -> OsType.AlmaLinux
                        "alpaquita" -> OsType.Alpaquita
                        "alpine" -> OsType.Alpine
                        "altlinux" -> OsType.ALTLinux
                        "amzn" -> OsType.Amazon
                        // "antergos" -> Antergos
                        "aosc" -> OsType.AOSC
                        "arch" -> OsType.Arch
                        "archarm" -> OsType.Arch
                        "artix" -> OsType.Artix
                        "bluefin" -> OsType.Bluefin
                        "cachyos" -> OsType.CachyOS
                        "centos" -> OsType.CentOS
                        // "clear-linux-os" -> ClearLinuxOS
                        // "clearos" -> ClearOS
                        // "coreos"
                        // "cumulus-linux" -> Cumulus
                        "debian" -> {
                            // Check if it's actually Raspberry Pi OS
                            if (release.contains("Raspbian")) {
                                OsType.Raspbian
                            } else {
                                OsType.Debian
                            }
                        }
                        // "devuan" -> Devuan
                        "elementary" -> OsType.Elementary
                        "fedora" -> OsType.Fedora
                        // "gentoo" -> Gentoo
                        "instantos" -> OsType.InstantOS
                        // "ios-xr" -> IosXr
                        "kali" -> OsType.Kali
                        // "mageia" -> Mageia
                        // "manjaro" -> Manjaro
                        "manjaro-arm" -> OsType.Manjaro
                        "linuxmint" -> OsType.Mint
                        "mariner" -> OsType.Mariner
                        // "nexus" -> Nexus
                        "nixos" -> OsType.NixOS
                        "nobara" -> OsType.Nobara
                        "uos", "Uos" -> OsType.Uos
                        "opencloudos" -> OsType.OpenCloudOS
                        "openeuler", "openEuler" -> OsType.OpenEuler
                        "ol" -> OsType.OracleLinux
                        "opensuse" -> OsType.OpenSUSE
                        "opensuse-leap" -> OsType.OpenSUSE
                        "opensuse-microos" -> OsType.OpenSUSE
                        "opensuse-tumbleweed" -> OsType.OpenSUSE
                        "pika" -> OsType.PikaOS
                        // "rancheros" -> RancherOS
                        // "raspbian" -> Raspbian
                        // note XBian also uses "raspbian"
                        "rhel" -> OsType.RedHatEnterprise
                        "rocky" -> OsType.RockyLinux
                        // "sabayon" -> Sabayon
                        // "scientific" -> Scientific
                        // "slackware" -> Slackware
                        "sled" -> OsType.SUSE // SUSE desktop
                        "sles" -> OsType.SUSE
                        "sles_sap" -> OsType.SUSE // SUSE SAP
                        "ubuntu" -> OsType.Ubuntu
                        "ultramarine" -> OsType.Ultramarine
                        // "virtuozzo" -> Virtuozzo
                        "void" -> OsType.Void
                        "zorin" -> OsType.Zorin
                        // "XCP-ng" -> xcp-ng
                        // "xenenterprise" -> xcp-ng
                        // "xenserver" -> xcp-ng
                        else -> null
                    }
                }
            },
            version = { release ->
                Matcher.KeyValue("VERSION_ID").find(release)?.let { Version.fromString(it) }
            },
        ),
        // Older distributions must have their specific release file parsed.
        ReleaseInfo(
            path = "etc/mariner-release",
            osType = { OsType.Mariner },
            version = { release ->
                Matcher.PrefixedVersion("CBL-Mariner").find(release)?.let { Version.fromString(it) }
            },
        ),
        ReleaseInfo(
            path = "etc/centos-release",
            osType = { OsType.CentOS },
            version = { release ->
                Matcher.PrefixedVersion("release").find(release)?.let { Version.fromString(it) }
            },
        ),
        ReleaseInfo(
            path = "etc/fedora-release",
            osType = { OsType.Fedora },
            version = { release ->
                Matcher.PrefixedVersion("release").find(release)?.let { Version.fromString(it) }
            },
        ),
        ReleaseInfo(
            path = "etc/alpine-release",
            osType = { OsType.Alpine },
            version = { release -> Matcher.AllTrimmed.find(release)?.let { Version.fromString(it) } },
        ),
        ReleaseInfo(
            path = "etc/redhat-release",
            osType = { OsType.RedHatEnterprise },
            version = { release ->
                Matcher.PrefixedVersion("release").find(release)?.let { Version.fromString(it) }
            },
        ),
    )
