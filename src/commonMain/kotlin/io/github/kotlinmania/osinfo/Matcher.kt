// port-lint: source matcher.rs
package io.github.kotlinmania.osinfo

/**
 * An implementation to match on simple strings.
 */
sealed class Matcher {
    /** Considers the entire string (trimmed) to be the match. */
    data object AllTrimmed : Matcher()

    /** After finding the `prefix` followed by one or more spaces, returns the following word. */
    data class PrefixedWord(
        val prefix: String,
    ) : Matcher()

    /** Similar to [PrefixedWord], but only if the word is a valid version. */
    data class PrefixedVersion(
        val prefix: String,
    ) : Matcher()

    /**
     * Takes a set of lines (separated by `\n`) and searches for the value in a key/value pair
     * separated by the `=` character. For example `VERSION_ID="8.1"`.
     */
    data class KeyValue(
        val key: String,
    ) : Matcher()

    /** Find the match on the input `string`. */
    fun find(string: String): String? =
        when (this) {
            is AllTrimmed -> string.trim()
            is PrefixedWord -> findPrefixedWord(string, prefix)
            is PrefixedVersion -> findPrefixedWord(string, prefix)?.takeIf { isValidVersion(it) }
            is KeyValue -> findByKey(string, key)
        }
}

private fun findByKey(string: String, key: String): String? {
    val keyEq = "$key="
    for (line in string.lineSequence()) {
        if (line.startsWith(keyEq)) {
            return line.substring(keyEq.length).trim { c -> c == '"' || c.isWhitespace() }
        }
    }
    return null
}

private fun findPrefixedWord(string: String, prefix: String): String? {
    val prefixStart = string.indexOf(prefix)
    if (prefixStart < 0) return null

    // Ignore prefix and leading whitespace
    val afterPrefix = string.substring(prefixStart + prefix.length).trimStart()

    // Find where the word boundary ends
    val wordEnd =
        afterPrefix
            .indexOfFirst { it.isWhitespace() }
            .let { if (it < 0) afterPrefix.length else it }

    return afterPrefix.substring(0, wordEnd)
}

private fun isValidVersion(word: String): Boolean =
    !word.startsWith('.') && !word.endsWith('.')
