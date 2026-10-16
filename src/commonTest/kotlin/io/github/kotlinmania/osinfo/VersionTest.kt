// port-lint: tests version.rs
package io.github.kotlinmania.osinfo

import io.github.kotlinmania.prettyassertions.assertEq
import kotlin.test.Test

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
            val result = parseVersion(s)
            assertEq(expected, result)
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
            assertEq(expected, version)
        }
    }

    @Test
    fun default() {
        assertEq(Version.Unknown, Version.default())
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
            assertEq(expected, version.toString())
        }
    }
}
