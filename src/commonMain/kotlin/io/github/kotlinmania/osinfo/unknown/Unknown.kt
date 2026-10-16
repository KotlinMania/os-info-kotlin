// Transliterated from: unknown/mod.rs
package io.github.kotlinmania.osinfo.unknown

import io.github.kotlinmania.log.formatArgs
import io.github.kotlinmania.log.trace
import io.github.kotlinmania.osinfo.Info

internal fun currentPlatform(): Info {
    trace(formatArgs("unknown::current_platform is called"))
    return Info.unknown()
}
