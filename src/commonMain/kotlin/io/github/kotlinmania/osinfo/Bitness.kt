// port-lint: source src/bitness.rs
package io.github.kotlinmania.osinfo

// spell-checker:ignore getconf

/**
 * Operating system architecture in terms of how many bits compose the basic values it can deal
 * with.
 *
 * Upstream Rust derives `Debug`, `Copy`, `Clone`, `PartialEq`, `Eq`, `PartialOrd`, `Ord`, and
 * `Hash`, and is marked `#[non_exhaustive]`. The Kotlin enum gets value equality, `hashCode`, and
 * `compareTo` for free from `enum class`; the `non_exhaustive` annotation is a Rust crate-stability
 * marker with no Kotlin equivalent. `Serialize`/`Deserialize`/`JsonSchema` derives behind the
 * `serde`/`schemars` feature flags are translated as consumer-side serializer plug-ins, not as
 * derives baked in here.
 */
enum class Bitness {
    /** Unknown bitness (unable to determine). */
    Unknown,

    /** 32-bit. */
    X32,

    /** 64-bit. */
    X64;

    override fun toString(): String = when (this) {
        Unknown -> "unknown bitness"
        X32 -> "32-bit"
        X64 -> "64-bit"
    }
}
