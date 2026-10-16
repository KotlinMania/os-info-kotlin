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
 * println("Type: ${info.osType()}")
 * println("Version: ${info.version()}")
 * println("Edition: ${info.edition()}")
 * println("Codename: ${info.codename()}")
 * println("Bitness: ${info.bitness()}")
 * println("Architecture: ${info.architecture()}")
 * ```
 */
fun get(): Info = currentPlatform()

internal fun currentPlatform(): Info =
    when (uname(UnameField.Sysname)) {
        "AIX" -> {
            io.github.kotlinmania.osinfo.aix
                .currentPlatform()
        }

        "DragonFly" -> {
            io.github.kotlinmania.osinfo.dragonfly
                .currentPlatform()
        }

        "Emscripten" -> {
            io.github.kotlinmania.osinfo.emscripten
                .currentPlatform()
        }

        "FreeBSD", "MidnightBSD" -> {
            io.github.kotlinmania.osinfo.freebsd
                .currentPlatform()
        }

        "SunOS", "illumos" -> {
            io.github.kotlinmania.osinfo.illumos
                .currentPlatform()
        }

        "Linux" -> {
            io.github.kotlinmania.osinfo.linux
                .currentPlatform()
        }

        "Darwin" -> {
            io.github.kotlinmania.osinfo.macos
                .currentPlatform()
        }

        "NetBSD" -> {
            io.github.kotlinmania.osinfo.netbsd
                .currentPlatform()
        }

        "OpenBSD" -> {
            io.github.kotlinmania.osinfo.openbsd
                .currentPlatform()
        }

        "CYGWIN_NT" -> {
            io.github.kotlinmania.osinfo.cygwin
                .currentPlatform()
        }

        "Redox" -> {
            io.github.kotlinmania.osinfo.redox
                .currentPlatform()
        }

        "Windows_NT" -> {
            io.github.kotlinmania.osinfo.windows
                .currentPlatform()
        }

        else -> {
            io.github.kotlinmania.osinfo.unknown
                .currentPlatform()
        }
    }
