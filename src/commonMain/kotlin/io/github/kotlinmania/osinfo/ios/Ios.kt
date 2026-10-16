// Transliterated from: ios/mod.rs
package io.github.kotlinmania.osinfo.ios

import io.github.kotlinmania.libc.unix.fclose
import io.github.kotlinmania.libc.unix.fgets
import io.github.kotlinmania.libc.unix.fopen
import io.github.kotlinmania.log.formatArgs
import io.github.kotlinmania.log.trace
import io.github.kotlinmania.osinfo.Bitness
import io.github.kotlinmania.osinfo.Info
import io.github.kotlinmania.osinfo.OsType
import io.github.kotlinmania.osinfo.UnameField
import io.github.kotlinmania.osinfo.Version
import io.github.kotlinmania.osinfo.uname

internal fun currentPlatform(): Info {
    trace(formatArgs("ios::current_platform is called"))

    val bitness =
        when (uname(UnameField.Machine)) {
            "x86", "arm" -> Bitness.X32
            "x86_64", "aarch64", "arm64" -> Bitness.X64
            else -> Bitness.Unknown
        }

    val info =
        Info(
            osType = OsType.Ios,
            version = version(),
            bitness = bitness,
        )
    trace(formatArgs("Returning {:?}", info))
    return info
}

internal fun version(): Version =
    when (val v = systemVersion()) {
        null -> Version.Unknown
        else -> Version.fromString(v)
    }

internal fun systemVersion(): String? {
    val file = fopen("/System/Library/CoreServices/SystemVersion.plist", "r")
    if (file != null) {
        val sb = StringBuilder()
        while (true) {
            val line = fgets(null, 1024, file) ?: break
            sb.append(line)
        }
        fclose(file)
        val ver =
            Regex("<key>ProductVersion</key>\\s*<string>([^<]+)</string>")
                .find(sb.toString())
                ?.groupValues
                ?.get(1)
        if (ver != null) {
            return ver
        }
    }
    return uname(UnameField.Release)
}
