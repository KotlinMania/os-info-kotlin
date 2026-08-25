// port-lint: source cygwin/mod.rs
package io.github.kotlinmania.osinfo.cygwin

import io.github.kotlinmania.osinfo.Info
import io.github.kotlinmania.osinfo.OsType
import io.github.kotlinmania.osinfo.UnameField
import io.github.kotlinmania.osinfo.Version
import io.github.kotlinmania.osinfo.getArchitecture
import io.github.kotlinmania.osinfo.getBitness
import io.github.kotlinmania.osinfo.uname

fun currentPlatform(releaseProvider: (() -> String?)? = null): Info {
    val version =
        (releaseProvider?.invoke() ?: uname(UnameField.Release))
            ?.let { Version.fromString(it) }
            ?: Version.Unknown

    return Info(
        osType = OsType.Cygwin,
        version = version,
        bitness = getBitness(),
        architecture = getArchitecture(),
    )
}
