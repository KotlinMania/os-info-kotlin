// port-lint: tests matcher.rs
package io.github.kotlinmania.osinfo

import kotlin.test.Test
import kotlin.test.assertEquals

class MatcherTest {
    @Test
    fun trimmed() {
        val data = listOf(
            "" to "",
            "test" to "test",
            " \t\t test" to "test",
            "test  \t   " to "test",
            "  test \t" to "test",
        )

        val matcher = Matcher.AllTrimmed

        for ((input, expected) in data) {
            val result = matcher.find(input)
            assertEquals(expected, result, "input=<$input>")
        }
    }

    @Test
    fun prefixedWord() {
        val data: List<Pair<String, String?>> = listOf(
            "" to null,
            "test" to "",
            "test1" to "1",
            "test 1" to "1",
            " test 1" to "1",
            "test 1.2.3" to "1.2.3",
            " \t\ttest 1.2.3" to "1.2.3",
        )

        val matcher = Matcher.PrefixedWord("test")

        for ((input, expected) in data) {
            val result = matcher.find(input)
            assertEquals(expected, result, "input=<$input>")
        }
    }

    @Test
    fun prefixedVersion() {
        val data: List<Pair<String, String?>> = listOf(
            "" to null,
            "test" to "",
            "test 1" to "1",
            "test .1" to null,
            "test 1." to null,
            "test .1." to null,
            " test 1" to "1",
            "test 1.2.3" to "1.2.3",
            " \t\ttest 1.2.3" to "1.2.3",
        )

        val matcher = Matcher.PrefixedVersion("test")

        for ((input, expected) in data) {
            val result = matcher.find(input)
            assertEquals(expected, result, "input=<$input>")
        }
    }

    @Test
    fun keyValue() {
        val data: List<Pair<String, String?>> = listOf(
            "" to null,
            "key" to null,
            "key=value" to "value",
            "key=1" to "1",
            "key=\"1\"" to "1",
            "key=\"CentOS Linux\"" to "CentOS Linux",
        )

        val matcher = Matcher.KeyValue("key")

        for ((input, expected) in data) {
            val result = matcher.find(input)
            assertEquals(expected, result, "input=<$input>")
        }
    }
}
