// Transliterated from: aix/mod.rs
package io.github.kotlinmania.osinfo.aix

import io.github.kotlinmania.log.formatArgs
import io.github.kotlinmania.log.trace
import io.github.kotlinmania.osinfo.Bitness
import io.github.kotlinmania.osinfo.Info
import io.github.kotlinmania.osinfo.OsType
import io.github.kotlinmania.osinfo.UnameField
import io.github.kotlinmania.osinfo.Version
import io.github.kotlinmania.osinfo.uname

internal fun currentPlatform(): Info {
    trace(formatArgs("aix::current_platform is called"))

    val version =
        getVersion()
            ?.let(Version::fromString)
            ?: Version.Unknown

    val info =
        Info(
            osType = getOs(),
            version = version,
            bitness = Bitness.get(),
        )

    trace(formatArgs("Returning {:?}", info))
    return info
}

internal fun getVersion(): String? {
    val major = uname(UnameField.Version) ?: return null
    val minor = uname(UnameField.Release) ?: "0"
    return "$major.$minor"
}

internal fun getOs(): OsType =
    when (uname(UnameField.Sysname)) {
        "AIX" -> OsType.AIX
        else -> OsType.AIX
    }
