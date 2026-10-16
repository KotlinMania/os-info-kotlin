// port-lint: source lib.rs
package io.github.kotlinmania.osinfo

/**
 * Returns information about the current operating system (type, version, edition, etc.).
 *
 * # Examples
 *
 * ```
 * val info = get()
 *
 * // Print full information:
 * println("OS information: $info")
 *
 * // Print information separately:
 * println("Type: ${info.osType}")
 * println("Version: ${info.version}")
 * println("Edition: ${info.edition}")
 * println("Codename: ${info.codename}")
 * println("Bitness: ${info.bitness}")
 * println("Architecture: ${info.architecture}")
 * ```
 */
fun get(): Info = currentPlatform()

fun currentPlatform(): Info =
    io.github.kotlinmania.osinfo.unknown
        .currentPlatform()
