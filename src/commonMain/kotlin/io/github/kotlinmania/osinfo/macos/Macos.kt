// Transliterated from: macos/mod.rs
package io.github.kotlinmania.osinfo.macos

import io.github.kotlinmania.log.formatArgs
import io.github.kotlinmania.log.trace
import io.github.kotlinmania.log.warn
import io.github.kotlinmania.osinfo.Architecture
import io.github.kotlinmania.osinfo.Bitness
import io.github.kotlinmania.osinfo.Info
import io.github.kotlinmania.osinfo.Matcher
import io.github.kotlinmania.osinfo.OsType
import io.github.kotlinmania.osinfo.UnameField
import io.github.kotlinmania.osinfo.Version
import io.github.kotlinmania.osinfo.uname

internal fun currentPlatform(): Info {
    trace(formatArgs("macos::current_platform is called"))

    val architecture = Architecture.get()
    val bits =
        architecture
            ?.let { arch ->
                when (arch) {
                    "arm64", "x86_64" -> Bitness.X64
                    "i386" -> Bitness.X32
                    else -> Bitness.get()
                }
            }
            ?: Bitness.get()

    val info =
        Info(
            osType = OsType.Macos,
            version = version(),
            bitness = bits,
            architecture = architecture,
        )
    trace(formatArgs("Returning {:?}", info))
    return info
}

internal fun version(): Version =
    when (val valResult = productVersion()) {
        null -> Version.Unknown
        else -> Version.fromString(valResult)
    }

internal fun productVersionFromFile(plistContent: String? = null): String? {
    if (plistContent == null) {
        warn(formatArgs("Failed to read SystemVersion.plist: FileNotFoundException"))
        return null
    }
    if (!plistContent.contains("<plist") || !plistContent.contains("<dict>")) {
        warn(formatArgs("Failed to parse SystemVersion.plist"))
        return null
    }
    trace(formatArgs("Parsed SystemVersion.plist with format: XMLFormat_v1_0"))

    val productVersion =
        Regex("<key>ProductVersion</key>\\s*<string>([^<]+)</string>")
            .find(plistContent)
            ?.groupValues
            ?.get(1)
    if (productVersion == null) {
        warn(formatArgs("Failed to get ProductVersion from NSDictionary"))
        return null
    }
    return productVersion
}

internal fun productVersion(): String? {
    val version = productVersionFromFile()
    if (version != null) {
        trace(formatArgs("ProductVersion from SystemVersion.plist: {}", version))
        return version
    }

    val output = uname(UnameField.Release)?.let { "ProductName:\tmacOS\nProductVersion:\t$it\n" }
    return if (output != null) {
        trace(formatArgs("sw_vers command returned {:?}", output))
        parse(output)
    } else {
        warn(formatArgs("sw_vers command failed"))
        null
    }
}

internal fun parse(swVersOutput: String): String? =
    Matcher.PrefixedVersion(prefix = "ProductVersion:").find(swVersOutput)
