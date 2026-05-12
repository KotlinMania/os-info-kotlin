// port-lint: source src/bitness.rs
package io.github.kotlinmania.osinfo

/**
 * Operating system architecture in terms of how many bits compose the basic values it can deal with.
 *
 * Upstream is annotated `#[non_exhaustive]`: new entries may be added without breaking source
 * compatibility. The upstream Rust enum also derives `Serialize`, `Deserialize`, and `JsonSchema`
 * behind the `serde` / `schemars` feature flags; those are translated as serializer plug-ins on
 * the consumer side, not as derives baked into the enum here.
 *
 * The upstream module also exports a free function `get(): Bitness` whose body is
 * `target_os`-gated and shells out to `getconf`, `sysctl`, `isainfo`, or `prtconf` depending on
 * the host. That platform-specific runtime probe is not part of this data-layer port; it lives
 * with the platform-specific module ports under `linux/`, `freebsd/`, etc.
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
