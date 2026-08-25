// port-lint: source freebsd/mod.rs
package io.github.kotlinmania.osinfo.freebsd

import io.github.kotlinmania.osinfo.Info
import io.github.kotlinmania.osinfo.OsType
import io.github.kotlinmania.osinfo.UnameField
import io.github.kotlinmania.osinfo.Version
import io.github.kotlinmania.osinfo.getBitness
import io.github.kotlinmania.osinfo.uname

fun currentPlatform(
    sysnameProvider: (() -> String?)? = null,
    releaseProvider: (() -> String?)? = null,
    sysctlProvider: (() -> String?)? = null,
): Info {
    val version =
        (releaseProvider?.invoke() ?: uname(UnameField.Release))
            ?.let { Version.fromString(it) }
            ?: Version.Unknown

    return Info(
        osType = getOs(sysnameProvider, sysctlProvider),
        version = version,
        bitness = getBitness(),
    )
}

fun getOs(
    sysnameProvider: (() -> String?)? = null,
    sysctlProvider: (() -> String?)? = null,
): OsType {
    val sysname = sysnameProvider?.invoke() ?: uname(UnameField.Sysname)
    return when (sysname) {
        "MidnightBSD" -> OsType.MidnightBSD
        "FreeBSD" -> {
            val checkHardening = sysctlProvider?.invoke()
            if (checkHardening == "0\n" || checkHardening == "0") {
                OsType.HardenedBSD
            } else {
                OsType.FreeBSD
            }
        }
        else -> OsType.FreeBSD
    }
}
