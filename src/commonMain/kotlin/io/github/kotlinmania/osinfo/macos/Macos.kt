// port-lint: source macos/mod.rs
package io.github.kotlinmania.osinfo.macos

import io.github.kotlinmania.osinfo.Bitness
import io.github.kotlinmania.osinfo.Info
import io.github.kotlinmania.osinfo.Matcher
import io.github.kotlinmania.osinfo.OsType
import io.github.kotlinmania.osinfo.Version
import io.github.kotlinmania.osinfo.getArchitecture
import io.github.kotlinmania.osinfo.getBitness

fun currentPlatform(
    fileReleaseProvider: (() -> String?)? = null,
    commandReleaseProvider: (() -> String?)? = null,
): Info {
    val version = version { productVersion(fileReleaseProvider, commandReleaseProvider) }
    val architecture = getArchitecture()

    val bitness =
        if (architecture == "arm64" || architecture == "x86_64") {
            Bitness.X64
        } else if (architecture == "i386") {
            Bitness.X32
        } else {
            getBitness()
        }

    return Info(
        osType = OsType.Macos,
        version = version,
        bitness = bitness,
        architecture = architecture,
    )
}

fun version(productVersionProvider: (() -> String?)? = null): Version =
    (productVersionProvider?.invoke() ?: productVersion())
        ?.let { Version.fromString(it) }
        ?: Version.Unknown

fun productVersion(
    fileReleaseProvider: (() -> String?)? = null,
    commandReleaseProvider: (() -> String?)? = null,
): String? = productVersionFromFile(fileReleaseProvider) ?: commandReleaseProvider?.invoke()

fun productVersionFromFile(fileReleaseProvider: (() -> String?)? = null): String? = fileReleaseProvider?.invoke()

fun parse(swVersOutput: String): String? = Matcher.PrefixedVersion("ProductVersion:").find(swVersOutput)
