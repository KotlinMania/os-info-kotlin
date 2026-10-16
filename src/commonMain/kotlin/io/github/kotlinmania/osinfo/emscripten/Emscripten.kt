// Transliterated from: emscripten/mod.rs
package io.github.kotlinmania.osinfo.emscripten

import io.github.kotlinmania.log.formatArgs
import io.github.kotlinmania.log.trace
import io.github.kotlinmania.osinfo.Info
import io.github.kotlinmania.osinfo.OsType

internal fun currentPlatform(): Info {
    trace(formatArgs("emscripten::current_platform is called"))

    val info = Info.withType(OsType.Emscripten)
    trace(formatArgs("Returning {:?}", info))
    return info
}
