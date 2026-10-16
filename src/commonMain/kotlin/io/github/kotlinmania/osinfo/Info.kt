// port-lint: source info.rs
package io.github.kotlinmania.osinfo

import kotlinx.serialization.Serializable

// spell-checker:ignore itertools, iproduct, bitnesses

/**
 * Holds information about operating system (type, version, etc.).
 *
 * The best way to get string representation of the operation system information is to use its
 * `toString` implementation.
 *
 * # Examples
 *
 * ```
 * val info = get()
 * println("OS information: $info")
 * ```
 */
@Serializable
data class Info(
    /** Operating system type. See `OsType` for details. */
    val osType: OsType = OsType.Unknown,
    /** Operating system version. See `Version` for details. */
    val version: Version = Version.Unknown,
    /** Operating system edition. */
    val edition: String? = null,
    /** Operating system codename. */
    val codename: String? = null,
    /**
     * Operating system architecture in terms of how many bits a pointer type can represent. See
     * `Bitness` for details.
     */
    val bitness: Bitness = Bitness.Unknown,
    /** Processor architecture. */
    val architecture: String? = null,
) : Comparable<Info> {
    /**
     * Returns operating system type. See `OsType` for details.
     *
     * # Examples
     *
     * ```
     * val info = get()
     * println("OS type: ${info.osType()}")
     * ```
     */
    internal fun osType(): OsType = osType

    /**
     * Returns operating system version. See `Version` for details.
     *
     * # Examples
     *
     * ```
     * val info = get()
     * println("OS version: ${info.version()}")
     * ```
     */
    internal fun version(): Version = version

    /**
     * Returns optional operation system edition.
     *
     * # Examples
     *
     * ```
     * val info = get()
     * println("OS edition: ${info.edition()}")
     * ```
     */
    internal fun edition(): String? = edition

    /**
     * Returns optional operation system "codename"
     *
     * # Examples
     *
     * ```
     * val info = get()
     * println("OS codename: ${info.codename()}")
     * ```
     */
    internal fun codename(): String? = codename

    /**
     * Returns operating system bitness. See `Bitness` for details.
     *
     * # Examples
     *
     * ```
     * val info = get()
     * println("OS bitness: ${info.bitness()}")
     * ```
     */
    internal fun bitness(): Bitness = bitness

    /**
     * Returns operating system architecture.
     *
     * # Examples
     *
     * ```
     * val info = get()
     * println("OS architecture: ${info.architecture()}")
     * ```
     */
    internal fun architecture(): String? = architecture

    internal fun fmt(): String {
        val sb = StringBuilder()
        sb.append(osType)
        if (version != Version.Unknown) {
            sb.append(" ").append(version)
        }
        if (edition != null) {
            sb.append(" (").append(edition).append(")")
        }
        if (codename != null) {
            sb.append(" (").append(codename).append(")")
        }
        sb.append(" [").append(bitness).append("]")
        return sb.toString()
    }

    override fun toString(): String = fmt()

    override fun compareTo(other: Info): Int {
        val t = osType.compareTo(other.osType)
        if (t != 0) return t
        val v = version.compareTo(other.version)
        if (v != 0) return v
        val e = compareNullableStrings(edition, other.edition)
        if (e != 0) return e
        val c = compareNullableStrings(codename, other.codename)
        if (c != 0) return c
        val b = bitness.compareTo(other.bitness)
        if (b != 0) return b
        return compareNullableStrings(architecture, other.architecture)
    }

    companion object {
        /**
         * Constructs a new [Info] instance with unknown type, version and bitness.
         */
        fun unknown(): Info =
            Info(
                osType = OsType.Unknown,
                version = Version.Unknown,
                edition = null,
                codename = null,
                bitness = Bitness.Unknown,
                architecture = null,
            )

        /**
         * Constructs a new [Info] instance with the specified operating system type.
         */
        fun withType(osType: OsType): Info =
            Info(
                osType = osType,
                version = Version.Unknown,
                edition = null,
                codename = null,
                bitness = Bitness.Unknown,
                architecture = null,
            )

        /**
         * Default value, equivalent to upstream `Default::default()`: returns unknown.
         */
        fun default(): Info = unknown()

        private fun compareNullableStrings(a: String?, b: String?): Int =
            when {
                a == null && b == null -> 0
                a == null -> -1
                b == null -> 1
                else -> a.compareTo(b)
            }
    }
}
