// port-lint: source bitness.rs
package io.github.kotlinmania.osinfo

import kotlinx.serialization.Serializable

// spell-checker:ignore getconf

/**
 * Operating system architecture in terms of how many bits compose the basic values it can deal with.
 */
@Serializable
enum class Bitness {
    /** Unknown bitness (unable to determine). */
    Unknown,

    /** 32-bit. */
    X32,

    /** 64-bit. */
    X64,
    ;

    internal fun fmt(): String =
        when (this) {
            Unknown -> "unknown bitness"
            X32 -> "32-bit"
            X64 -> "64-bit"
        }

    override fun toString(): String = fmt()

    companion object {
        internal fun get(): Bitness =
            when (uname(UnameField.Machine)) {
                "amd64", "x86_64", "aarch64", "arm64", "sparc64" -> X64
                "i386", "earmv7hf", "x86", "arm" -> X32
                else -> X64
            }
    }
}
