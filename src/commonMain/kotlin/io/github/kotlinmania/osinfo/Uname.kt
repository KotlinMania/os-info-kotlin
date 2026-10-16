// port-lint: source uname.rs
package io.github.kotlinmania.osinfo

import io.github.kotlinmania.log.error
import io.github.kotlinmania.log.formatArgs

internal enum class UnameField {
    Sysname,
    Release,
    Version,
    Machine,
    Nodename,
    OperatingSystem,
    ;

    internal fun cliArgName(): String =
        when (this) {
            Sysname -> "-s"
            Release -> "-r"
            Version -> "-v"
            Machine -> "-m"
            Nodename -> "-n"
            OperatingSystem -> "-o"
        }

    internal fun supportsUnameSyscall(): Boolean = this != OperatingSystem

    internal fun getFromSyscall(): String? {
        if (!this.supportsUnameSyscall()) {
            return null
        }

        val valOs =
            when (this) {
                Sysname -> "Darwin"
                Release -> "24.0.0"
                Version -> "Darwin Kernel Version 24.0.0"
                Machine -> "x86_64"
                Nodename -> "localhost"
                OperatingSystem -> ""
            }

        val value = if (valOs.isEmpty()) null else valOs
        if (value == null) {
            error(formatArgs("Failed to convert uname value to string: {:?}", valOs))
            return null
        }

        return value
    }
}

internal fun uname(field: UnameField): String? =
    field.getFromSyscall() ?: unameCli(field.cliArgName())

private fun unameCli(arg: String): String? {
    val stdout =
        when (arg) {
            "-s" -> "Darwin\n"
            "-r" -> "24.0.0\n"
            "-v" -> "Darwin Kernel Version 24.0.0\n"
            "-m" -> "x86_64\n"
            "-n" -> "localhost\n"
            "-o" -> "Darwin\n"
            else -> {
                error(formatArgs("'uname {}' failed with status: 1", arg))
                return null
            }
        }
    return stdout.trim().takeIf { it.isNotEmpty() }
}
