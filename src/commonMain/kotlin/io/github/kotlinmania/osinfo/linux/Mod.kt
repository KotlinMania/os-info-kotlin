// port-lint: source linux/mod.rs
package io.github.kotlinmania.osinfo.linux

import io.github.kotlinmania.osinfo.Info
import io.github.kotlinmania.osinfo.OsType
import io.github.kotlinmania.osinfo.Version
import io.github.kotlinmania.osinfo.getArchitecture
import io.github.kotlinmania.osinfo.getBitness

fun currentPlatform(
    lsbReleaseProvider: (() -> String?)? = null,
    fileReleaseProvider: ((String) -> String?)? = null,
): Info {
    val lsbInfo = getFromLsb(lsbReleaseProvider)
    val fileInfo = getFromFile(fileReleaseProvider)

    return Info(
        osType = lsbInfo?.osType ?: fileInfo?.osType ?: OsType.Linux,
        version = lsbInfo?.version ?: fileInfo?.version ?: Version.Unknown,
        edition = lsbInfo?.edition ?: fileInfo?.edition,
        codename = lsbInfo?.codename ?: fileInfo?.codename,
        bitness = getBitness(),
        architecture = getArchitecture(),
    )
}

fun getFromLsb(releaseProvider: (() -> String?)? = null): Info? = get(releaseProvider)

fun getFromFile(fileReleaseProvider: ((String) -> String?)? = null): Info? = get(fileReleaseProvider)
