// port-lint: source redox/mod.rs
package io.github.kotlinmania.osinfo.redox

import io.github.kotlinmania.osinfo.Bitness
import io.github.kotlinmania.osinfo.Info
import io.github.kotlinmania.osinfo.OsType
import io.github.kotlinmania.osinfo.Version

// spell-checker:ignore uname

fun currentPlatform(versionProvider: (() -> String?)? = null): Info {
    val version =
        getVersion(versionProvider)
            ?.let { Version.fromString(it) }
            ?: Version.Unknown

    return Info(
        osType = OsType.Redox,
        version = version,
        bitness = Bitness.Unknown,
    )
}

fun getVersion(versionProvider: (() -> String?)? = null): String? =
    versionProvider?.invoke()
