// port-lint: source android/mod.rs
package io.github.kotlinmania.osinfo.android

import io.github.kotlinmania.androidsystemproperties.AndroidSystemProperties
import io.github.kotlinmania.log.formatArgs
import io.github.kotlinmania.log.trace
import io.github.kotlinmania.osinfo.Bitness
import io.github.kotlinmania.osinfo.Info
import io.github.kotlinmania.osinfo.OsType
import io.github.kotlinmania.osinfo.Version

fun currentPlatform(): Info {
    trace(formatArgs("android::current_platform is called"))

    val bitness = Bitness.X64

    val info =
        Info(
            osType = OsType.Android,
            version = version(),
            bitness = bitness,
        )
    trace(formatArgs("Returning {}", info))
    return info
}

fun version(): Version {
    val androidSystemProperties = AndroidSystemProperties.new()

    val release = androidSystemProperties.get("ro.build.version.release")
    return if (release != null) Version.fromString(release) else Version.Unknown
}
