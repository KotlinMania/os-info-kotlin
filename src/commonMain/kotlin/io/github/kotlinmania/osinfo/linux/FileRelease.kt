// port-lint: source linux/file_release.rs
package io.github.kotlinmania.osinfo.linux

import io.github.kotlinmania.osinfo.Bitness
import io.github.kotlinmania.osinfo.Info
import io.github.kotlinmania.osinfo.Matcher
import io.github.kotlinmania.osinfo.OsType
import io.github.kotlinmania.osinfo.Version

// spell-checker:ignore sles, AOSCOS

/**
 * Returns OS information from the release file if available.
 */
fun get(fileReleaseProvider: ((String) -> String?)? = null): Info? =
    retrieve(DISTRIBUTIONS, "/", fileReleaseProvider)

/**
 * Retrieves OS information from the specified distribution table.
 */
fun retrieve(
    distributions: List<ReleaseInfo>,
    root: String,
    fileReleaseProvider: ((String) -> String?)? = null,
): Info? {
    for (releaseInfo in distributions) {
        val path = if (root.endsWith("/")) "$root${releaseInfo.path}" else "$root/${releaseInfo.path}"
        val fileContent = fileReleaseProvider?.invoke(path) ?: continue

        val osType = releaseInfo.osType(fileContent) ?: continue
        val version = releaseInfo.version(fileContent) ?: Version.Unknown

        return Info(
            osType = osType,
            version = version,
            bitness = Bitness.Unknown,
        )
    }

    return null
}

/**
 * Struct containing information on how to parse distribution info from a release file.
 */
data class ReleaseInfo(
    /** Relative path to the release file this struct corresponds to from root. */
    val path: String,
    /** A function that determines the OS type from the release file contents. */
    val osType: (String) -> OsType?,
    /** A function that determines the OS version from the release file contents. */
    val version: (String) -> Version?,
) {
    fun fmt(): String = "ReleaseInfo(path=$path)"
}

/**
 * List of all supported distributions and the information on how to parse their version from the
 * release file.
 */
val DISTRIBUTIONS: List<ReleaseInfo> =
    listOf(
        // Keep this first; most modern distributions have this file.
        ReleaseInfo(
            path = "etc/os-release",
            osType = { release ->
                Matcher.KeyValue("ID").find(release)?.let { id ->
                    when (id) {
                        "almalinux" -> OsType.AlmaLinux
                        "alpaquita" -> OsType.Alpaquita
                        "alpine" -> OsType.Alpine
                        "altlinux" -> OsType.ALTLinux
                        "amzn" -> OsType.Amazon
                        "aosc" -> OsType.AOSC
                        "arch", "archarm" -> OsType.Arch
                        "artix" -> OsType.Artix
                        "bluefin" -> OsType.Bluefin
                        "cachyos" -> OsType.CachyOS
                        "centos" -> OsType.CentOS
                        "debian" -> OsType.Debian
                        "elementary" -> OsType.Elementary
                        "fedora" -> OsType.Fedora
                        "instantos" -> OsType.InstantOS
                        "kali" -> OsType.Kali
                        "manjaro-arm" -> OsType.Manjaro
                        "linuxmint" -> OsType.Mint
                        "mariner" -> OsType.Mariner
                        "nixos" -> OsType.NixOS
                        "nobara" -> OsType.Nobara
                        "uos", "Uos" -> OsType.Uos
                        "opencloudos" -> OsType.OpenCloudOS
                        "openeuler", "openEuler" -> OsType.OpenEuler
                        "ol" -> OsType.OracleLinux
                        "opensuse", "opensuse-leap", "opensuse-microos", "opensuse-tumbleweed" -> OsType.OpenSUSE
                        "pika" -> OsType.PikaOS
                        "rhel" -> OsType.RedHatEnterprise
                        "rocky" -> OsType.RockyLinux
                        "sled", "sles", "sles_sap" -> OsType.SUSE
                        "ubuntu" -> OsType.Ubuntu
                        "ultramarine" -> OsType.Ultramarine
                        "void" -> OsType.Void
                        "zorin" -> OsType.Zorin
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
