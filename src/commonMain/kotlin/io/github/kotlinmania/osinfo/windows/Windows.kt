// Transliterated from: windows/mod.rs
package io.github.kotlinmania.osinfo.windows

import io.github.kotlinmania.log.formatArgs
import io.github.kotlinmania.log.trace
import io.github.kotlinmania.osinfo.Info

internal fun currentPlatform(): Info {
    trace(formatArgs("windows::current_platform is called"))
    val info = get()
    trace(formatArgs("Returning {:?}", info))
    return info
}
