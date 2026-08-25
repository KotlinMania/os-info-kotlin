// port-lint: source android/mod.rs
package io.github.kotlinmania.osinfo.android

import io.github.kotlinmania.osinfo.Bitness
import io.github.kotlinmania.osinfo.Info
import io.github.kotlinmania.osinfo.OsType
import io.github.kotlinmania.osinfo.Version

fun currentPlatform(releaseProvider: (() -> String?)? = null): Info {
    val bitness = Bitness.X64

    return Info(
        osType = OsType.Android,
        version = version(releaseProvider),
        bitness = bitness,
    )
}

fun version(releaseProvider: (() -> String?)? = null): Version {
    val release = releaseProvider?.invoke()
    return if (release != null) Version.fromString(release) else Version.Unknown
}
