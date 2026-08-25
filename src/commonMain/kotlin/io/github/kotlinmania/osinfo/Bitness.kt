// port-lint: source bitness.rs
package io.github.kotlinmania.osinfo

// spell-checker:ignore getconf

/**
 * Operating system architecture in terms of how many bits compose the basic values it can deal
 * with.
 */
enum class Bitness {
    /** Unknown bitness (unable to determine). */
    Unknown,

    /** 32-bit. */
    X32,

    /** 64-bit. */
    X64,
    ;

    override fun toString(): String =
        when (this) {
            Unknown -> "unknown bitness"
            X32 -> "32-bit"
            X64 -> "64-bit"
        }
}

fun getBitness(): Bitness = platformBitness()

fun bitness(): Bitness = platformBitness()

internal fun platformBitness(): Bitness = Bitness.X64
