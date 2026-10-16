// port-lint: source illumos/mod.rs
package io.github.kotlinmania.osinfo.illumos

import io.github.kotlinmania.osinfo.Info
import io.github.kotlinmania.osinfo.OsType
import io.github.kotlinmania.osinfo.UnameField
import io.github.kotlinmania.osinfo.Version
import io.github.kotlinmania.osinfo.getBitness
import io.github.kotlinmania.osinfo.uname

fun currentPlatform(
    versionProvider: (() -> String?)? = null,
    osProvider: (() -> String?)? = null,
): Info {
    val version =
        (versionProvider?.invoke() ?: uname(UnameField.Version))
            ?.let { Version.fromString(it) }
            ?: Version.Unknown

    return Info(
        osType = getOs(osProvider),
        version = version,
        bitness = getBitness(),
    )
}

fun getOs(osProvider: (() -> String?)? = null): OsType {
    val os = osProvider?.invoke() ?: uname(UnameField.OperatingSystem)
    return when (os) {
        "illumos" -> OsType.Illumos
        else -> OsType.Illumos
    }
}
