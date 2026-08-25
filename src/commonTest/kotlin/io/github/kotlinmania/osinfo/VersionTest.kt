// port-lint: tests version.rs
package io.github.kotlinmania.osinfo

import kotlin.test.Test
import kotlin.test.assertEquals

class VersionTest {
    @Test
    fun parseSemanticVersion() {
        val data: List<Pair<String, Triple<ULong, ULong, ULong>?>> =
            listOf(
                "" to null,
                "version" to null,
                "1" to Triple(1uL, 0uL, 0uL),
                "1." to Triple(1uL, 0uL, 0uL),
                "1.2" to Triple(1uL, 2uL, 0uL),
                "1.2." to Triple(1uL, 2uL, 0uL),
                "1.2.3" to Triple(1uL, 2uL, 3uL),
                "1.2.3." to Triple(1uL, 2uL, 3uL),
                "1.2.3.  " to Triple(1uL, 2uL, 3uL),
                "   1.2.3." to Triple(1uL, 2uL, 3uL),
                "   1.2.3.  " to Triple(1uL, 2uL, 3uL),
                "1.2.3.4" to null,
                "1.2.3.4.5.6.7.8.9" to null,
            )

        for ((s, expected) in data) {
            val result = parseVersionForTest(s)
            assertEquals(expected, result, "input=$s")
        }
    }

    @Test
    fun fromString() {
        val customVersion = "some version"
        val data: List<Pair<String, Version>> =
            listOf(
                "" to Version.Unknown,
                "1.2.3" to Version.Semantic(1uL, 2uL, 3uL),
                customVersion to Version.Custom(customVersion),
            )

        for ((s, expected) in data) {
            val version = Version.fromString(s)
            assertEquals(expected, version)
        }
    }

    @Test
    fun default() {
        assertEquals(Version.Unknown, Version.default())
    }

    @Test
    fun display() {
        val data: List<Pair<Version, String>> =
            listOf(
                Version.Unknown to "Unknown",
                Version.Semantic(1uL, 5uL, 0uL) to "1.5.0",
                Version.Rolling(null) to "Rolling Release",
                Version.Rolling("date") to "Rolling Release (date)",
            )

        for ((version, expected) in data) {
            assertEquals(expected, version.toString())
        }
    }
}

private fun parseVersionForTest(s: String): Triple<ULong, ULong, ULong>? =
    when (val v = Version.fromString(s)) {
        is Version.Semantic -> Triple(v.major, v.minor, v.patch)
        else -> null
    }
