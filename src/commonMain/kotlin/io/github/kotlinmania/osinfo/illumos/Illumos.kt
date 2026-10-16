// Transliterated from: illumos/mod.rs
package io.github.kotlinmania.osinfo.illumos

import io.github.kotlinmania.log.formatArgs
import io.github.kotlinmania.log.trace
import io.github.kotlinmania.osinfo.Bitness
import io.github.kotlinmania.osinfo.Info
import io.github.kotlinmania.osinfo.OsType
import io.github.kotlinmania.osinfo.UnameField
import io.github.kotlinmania.osinfo.Version
import io.github.kotlinmania.osinfo.uname

internal fun currentPlatform(): Info {
    trace(formatArgs("illumos::current_platform is called"))

    val version =
        uname(UnameField.Version)
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

internal fun getOs(): OsType =
    when (uname(UnameField.OperatingSystem)) {
        "illumos" -> OsType.Illumos
        else -> OsType.Illumos
    }
