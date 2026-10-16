// Transliterated from: linux/mod.rs
package io.github.kotlinmania.osinfo.linux

import io.github.kotlinmania.log.formatArgs
import io.github.kotlinmania.log.trace
import io.github.kotlinmania.osinfo.Architecture
import io.github.kotlinmania.osinfo.Bitness
import io.github.kotlinmania.osinfo.Info
import io.github.kotlinmania.osinfo.OsType

internal fun currentPlatform(): Info {
    trace(formatArgs("linux::current_platform is called"))

    val baseInfo =
        LsbRelease.get()
            ?: get()
            ?: Info.withType(OsType.Linux)
    val info =
        baseInfo.copy(
            bitness = Bitness.get(),
            architecture = Architecture.get(),
        )

    trace(formatArgs("Returning {:?}", info))
    return info
}
