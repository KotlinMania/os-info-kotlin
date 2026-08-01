// port-lint: source src/version.rs
package io.github.kotlinmania.osinfo

/**
 * Operating system version.
 *
 * Upstream Rust derives `Debug`, `Clone`, `PartialEq`, `Eq`, `PartialOrd`, `Ord`, and `Hash`; the
 * sealed-class translation gets value equality and `hashCode` for free from `data class`/`data
 * object`, and lexicographic comparison for the `Semantic` variant is enabled by implementing
 * [Comparable]. `Serialize`/`Deserialize`/`JsonSchema` derives behind the `serde`/`schemars`
 * feature flags are translated as consumer-side serializer plug-ins, not as derives baked in
 * here.
 */
sealed class Version : Comparable<Version> {
    /** Unknown version. */
    data object Unknown : Version()

    /** Semantic version (major.minor.patch). */
    data class Semantic(
        val major: ULong,
        val minor: ULong,
        val patch: ULong,
    ) : Version()

    /** Rolling version. Optionally contains the release date in the string format. */
    data class Rolling(
        val date: String?,
    ) : Version()

    /** Custom version format. */
    data class Custom(
        val value: String,
    ) : Version()

    final override fun toString(): String =
        when (this) {
            is Unknown -> "Unknown"
            is Semantic -> "$major.$minor.$patch"
            is Rolling -> {
                val datePart = date?.let { " ($it)" } ?: ""
                "Rolling Release$datePart"
            }
            is Custom -> value
        }

    override fun compareTo(other: Version): Int {
        val a = ordinalOf(this)
        val b = ordinalOf(other)
        if (a != b) return a.compareTo(b)
        return when {
            this is Unknown && other is Unknown -> 0
            this is Semantic && other is Semantic -> {
                val maj = major.compareTo(other.major)
                if (maj != 0) return maj
                val min = minor.compareTo(other.minor)
                if (min != 0) return min
                patch.compareTo(other.patch)
            }
            this is Rolling && other is Rolling -> compareNullableStrings(date, other.date)
            this is Custom && other is Custom -> value.compareTo(other.value)
            else -> 0
        }
    }

    companion object {
        /**
         * Constructs `VersionType` from the given string.
         *
         * Returns `VersionType.Unknown` if the string is empty. If it can be parsed as a semantic
         * version, then `VersionType.Semantic`, otherwise `VersionType.Custom`.
         *
         * # Examples
         *
         * ```
         * import io.github.kotlinmania.osinfo.Version
         *
         * val v = Version.fromString("custom")
         * check(Version.Custom("custom") == v)
         *
         * val v2 = Version.fromString("1.2.3")
         * check(Version.Semantic(1u, 2u, 3u) == v2)
         * ```
         */
        fun fromString(s: String): Version {
            if (s.isEmpty()) return Unknown
            val parsed = parseVersion(s)
            return if (parsed != null) {
                Semantic(parsed.first, parsed.second, parsed.third)
            } else {
                Custom(s)
            }
        }

        /** Default value, equivalent to upstream `Default::default()`: returns [Unknown]. */
        fun default(): Version = Unknown

        private fun ordinalOf(v: Version): Int =
            when (v) {
                is Unknown -> 0
                is Semantic -> 1
                is Rolling -> 2
                is Custom -> 3
            }

        private fun compareNullableStrings(a: String?, b: String?): Int =
            when {
                a == null && b == null -> 0
                a == null -> -1
                b == null -> 1
                else -> a.compareTo(b)
            }
    }
}

private fun parseVersion(s: String): Triple<ULong, ULong, ULong>? {
    val parts =
        s.trim().split('.').let {
            // Mirror Rust's `split_terminator('.')` semantics: a trailing '.' produces no extra
            // empty element after the final non-empty segment.
            if (it.isNotEmpty() && it.last().isEmpty()) it.dropLast(1) else it
        }
    val iter = parts.iterator()

    val majorStr = if (iter.hasNext()) iter.next() else return null
    val major = majorStr.toULongOrNull() ?: return null

    val minor = if (iter.hasNext()) iter.next().toULongOrNull() ?: return null else 0uL
    val patch = if (iter.hasNext()) iter.next().toULongOrNull() ?: return null else 0uL

    if (iter.hasNext()) return null

    return Triple(major, minor, patch)
}
