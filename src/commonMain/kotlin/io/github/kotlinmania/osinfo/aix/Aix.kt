// port-lint: source aix/mod.rs
package io.github.kotlinmania.osinfo.aix

import io.github.kotlinmania.osinfo.Info
import io.github.kotlinmania.osinfo.OsType
import io.github.kotlinmania.osinfo.UnameField
import io.github.kotlinmania.osinfo.Version
import io.github.kotlinmania.osinfo.getBitness
import io.github.kotlinmania.osinfo.uname

fun currentPlatform(
    majorProvider: (() -> String?)? = null,
    minorProvider: (() -> String?)? = null,
    sysnameProvider: (() -> String?)? = null,
): Info {
    val version =
        getVersion(majorProvider, minorProvider)
            ?.let { Version.fromString(it) }
            ?: Version.Unknown

    return Info(
        osType = getOs(sysnameProvider),
        version = version,
        bitness = getBitness(),
    )
}

fun getVersion(
    majorProvider: (() -> String?)? = null,
    minorProvider: (() -> String?)? = null,
): String? {
    val major = majorProvider?.invoke() ?: uname(UnameField.Version) ?: return null
    val minor = minorProvider?.invoke() ?: uname(UnameField.Release) ?: "0"
    return "$major.$minor"
}

fun getOs(sysnameProvider: (() -> String?)? = null): OsType {
    val sysname = sysnameProvider?.invoke() ?: uname(UnameField.Sysname)
    return when (sysname) {
        "AIX" -> OsType.AIX
        else -> OsType.AIX
    }
}
