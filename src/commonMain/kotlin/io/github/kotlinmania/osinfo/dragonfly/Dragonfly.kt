// port-lint: source dragonfly/mod.rs
package io.github.kotlinmania.osinfo.dragonfly

import io.github.kotlinmania.osinfo.Info
import io.github.kotlinmania.osinfo.OsType
import io.github.kotlinmania.osinfo.UnameField
import io.github.kotlinmania.osinfo.Version
import io.github.kotlinmania.osinfo.getBitness
import io.github.kotlinmania.osinfo.uname

fun currentPlatform(releaseProvider: (() -> String?)? = null): Info {
    val version =
        (releaseProvider?.invoke() ?: uname(UnameField.Release))
            ?.let { Version.fromString(it) }
            ?: Version.Unknown

    return Info(
        osType = OsType.DragonFly,
        version = version,
        bitness = getBitness(),
    )
}
