// port-lint: source ios/mod.rs
package io.github.kotlinmania.osinfo.ios

import io.github.kotlinmania.osinfo.Bitness
import io.github.kotlinmania.osinfo.Info
import io.github.kotlinmania.osinfo.OsType
import io.github.kotlinmania.osinfo.Version

fun currentPlatform(systemVersionProvider: (() -> String?)? = null): Info {
    val bitness = Bitness.X64

    return Info(
        osType = OsType.Ios,
        version = version(systemVersionProvider),
        bitness = bitness,
    )
}

fun version(systemVersionProvider: (() -> String?)? = null): Version {
    val ver = systemVersion(systemVersionProvider)
    return if (ver != null) Version.fromString(ver) else Version.Unknown
}

fun systemVersion(systemVersionProvider: (() -> String?)? = null): String? =
    systemVersionProvider?.invoke()
