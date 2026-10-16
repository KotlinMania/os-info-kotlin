// port-lint: source emscripten/mod.rs
package io.github.kotlinmania.osinfo.emscripten

import io.github.kotlinmania.osinfo.Info
import io.github.kotlinmania.osinfo.OsType

fun currentPlatform(): Info = Info.withType(OsType.Emscripten)
