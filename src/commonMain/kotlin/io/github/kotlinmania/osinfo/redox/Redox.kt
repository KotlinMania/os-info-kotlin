// Transliterated from: redox/mod.rs
package io.github.kotlinmania.osinfo.redox

import io.github.kotlinmania.libc.unix.fclose
import io.github.kotlinmania.libc.unix.fgets
import io.github.kotlinmania.libc.unix.fopen
import io.github.kotlinmania.log.error
import io.github.kotlinmania.log.formatArgs
import io.github.kotlinmania.log.trace
import io.github.kotlinmania.osinfo.Bitness
import io.github.kotlinmania.osinfo.Info
import io.github.kotlinmania.osinfo.OsType
import io.github.kotlinmania.osinfo.Version

// spell-checker:ignore uname

private const val UNAME_FILE: String = "sys:uname"

internal fun currentPlatform(): Info {
    trace(formatArgs("redox::current_platform is called"))

    val version =
        getVersion()
            ?.let(Version::fromString)
            ?: Version.Unknown
    val info =
        Info(
            osType = OsType.Redox,
            version = version,
            bitness = Bitness.Unknown,
        )
    trace(formatArgs("Returning {:?}", info))
    return info
}

internal fun getVersion(): String? {
    val file = fopen(UNAME_FILE, "r")
    if (file == null) {
        error(formatArgs("Unable to open {} file", UNAME_FILE))
        return null
    }

    val version = StringBuilder()
    while (true) {
        val line = fgets(null, 1024, file) ?: break
        version.append(line)
    }
    fclose(file)
    return version.toString().takeIf { it.isNotEmpty() }
}
