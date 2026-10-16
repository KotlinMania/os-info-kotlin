// Transliterated from: freebsd/mod.rs
package io.github.kotlinmania.osinfo.freebsd

import io.github.kotlinmania.log.error
import io.github.kotlinmania.log.formatArgs
import io.github.kotlinmania.log.trace
import io.github.kotlinmania.osinfo.Bitness
import io.github.kotlinmania.osinfo.Info
import io.github.kotlinmania.osinfo.OsType
import io.github.kotlinmania.osinfo.UnameField
import io.github.kotlinmania.osinfo.Version
import io.github.kotlinmania.osinfo.uname

internal fun currentPlatform(): Info {
    trace(formatArgs("freebsd::current_platform is called"))

    val version =
        uname(UnameField.Release)
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

internal fun getOs(hardeningCheck: String? = null): OsType =
    when (uname(UnameField.Sysname)) {
        "MidnightBSD" -> OsType.MidnightBSD
        "FreeBSD" -> {
            val checkHardening =
                if (hardeningCheck == null) {
                    error(formatArgs("Failed to invoke '/sbin/sysctl'"))
                    return OsType.FreeBSD
                } else {
                    hardeningCheck
                }
            when (checkHardening) {
                "0\n" -> OsType.HardenedBSD
                else -> OsType.FreeBSD
            }
        }
        else -> OsType.FreeBSD
    }
