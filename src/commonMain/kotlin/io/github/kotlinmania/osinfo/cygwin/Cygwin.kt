// Transliterated from: cygwin/mod.rs
package io.github.kotlinmania.osinfo.cygwin

import io.github.kotlinmania.log.formatArgs
import io.github.kotlinmania.log.trace
import io.github.kotlinmania.osinfo.Architecture
import io.github.kotlinmania.osinfo.Bitness
import io.github.kotlinmania.osinfo.Info
import io.github.kotlinmania.osinfo.OsType
import io.github.kotlinmania.osinfo.UnameField
import io.github.kotlinmania.osinfo.Version
import io.github.kotlinmania.osinfo.uname

internal fun currentPlatform(): Info {
    trace(formatArgs("cygwin::current_platform is called"))

    val version =
        uname(UnameField.Release)
            ?.let(Version::fromString)
            ?: Version.Unknown

    val info =
        Info(
            osType = OsType.Cygwin,
            version = version,
            bitness = Bitness.get(),
            architecture = Architecture.get(),
        )

    trace(formatArgs("Returning {:?}", info))
    return info
}
