// port-lint: source info.rs
package io.github.kotlinmania.osinfo

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
data class Info(
    val osType: OsType = OsType.Unknown,
    val version: Version = Version.Unknown,
    val edition: String? = null,
    val codename: String? = null,
    val bitness: Bitness = Bitness.Unknown,
    val architecture: String? = null,
) : Comparable<Info> {
    override fun toString(): String {
        val sb = StringBuilder()
        sb.append(osType.toString())
        if (version != Version.Unknown) {
            sb.append(" ").append(version.toString())
        }
        if (edition != null) {
            sb.append(" (").append(edition).append(")")
        }
        if (codename != null) {
            sb.append(" (").append(codename).append(")")
        }
        sb.append(" [").append(bitness.toString()).append("]")
        return sb.toString()
    }

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
