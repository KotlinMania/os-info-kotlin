// Transliterated from: macos/mod.rs
package io.github.kotlinmania.osinfo.macos

import io.github.kotlinmania.libc.unix.bsd.popen
import io.github.kotlinmania.libc.unix.fclose
import io.github.kotlinmania.libc.unix.fgets
import io.github.kotlinmania.libc.unix.fopen
import io.github.kotlinmania.libc.unix.pclose
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

internal fun productVersionFromFile(): String? {
    val file = fopen("/System/Library/CoreServices/SystemVersion.plist", "r")
    if (file == null) {
        warn(formatArgs("Failed to read SystemVersion.plist"))
        return null
    }
    val sb = StringBuilder()
    while (true) {
        val line = fgets(null, 1024, file) ?: break
        sb.append(line)
    }
    fclose(file)
    val buffer = sb.toString()
    if (!buffer.contains("<plist") || !buffer.contains("<dict>")) {
        warn(formatArgs("Failed to parse SystemVersion.plist"))
        return null
    }
    trace(formatArgs("Parsed SystemVersion.plist with format: XMLFormat_v1_0"))

    val productVersion =
        Regex("<key>ProductVersion</key>\\s*<string>([^<]+)</string>")
            .find(buffer)
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
        trace(formatArgs("ProductVersion from SystemVersion.plist: {:?}", version))
        return version
    }

    val stream = popen("sw_vers", "r")
    if (stream != null) {
        val sb = StringBuilder()
        while (true) {
            val line = fgets(null, 1024, stream) ?: break
            sb.append(line)
        }
        val status = pclose(stream)
        val output = sb.toString()
        if (status == 0 && output.isNotEmpty()) {
            trace(formatArgs("sw_vers command returned {:?}", output))
            return parse(output)
        }
    }

    warn(formatArgs("sw_vers command failed"))
    return uname(UnameField.Release)?.let { parse("ProductVersion:\t$it\n") }
}

internal fun parse(swVersOutput: String): String? =
    Matcher.PrefixedVersion(prefix = "ProductVersion:").find(swVersOutput)
