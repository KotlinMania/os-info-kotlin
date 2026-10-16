// port-lint: source uname.rs
package io.github.kotlinmania.osinfo

import io.github.kotlinmania.libc.Utsname
import io.github.kotlinmania.libc.unix.bsd.popen
import io.github.kotlinmania.libc.unix.fgets
import io.github.kotlinmania.libc.unix.pclose
import io.github.kotlinmania.log.error
import io.github.kotlinmania.log.formatArgs
import io.github.kotlinmania.libc.unix.bsd.apple.uname as nixUname

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

        val utsname = Utsname()
        val res = nixUname(utsname)
        if (res != 0) {
            error(formatArgs("Failed to invoke native uname: {:?}", res))
            return null
        }

        val valOs =
            when (this) {
                Sysname -> utsname.sysname
                Release -> utsname.release
                Version -> utsname.version
                Machine -> utsname.machine
                Nodename -> utsname.nodename
                OperatingSystem -> return null
            }

        val nulIndex = valOs.indexOf(0.toByte())
        val bytes = if (nulIndex >= 0) valOs.copyOfRange(0, nulIndex) else valOs
        val value = bytes.decodeToString().takeIf { it.isNotEmpty() }
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
    val stream = popen("uname $arg", "r")
    if (stream == null) {
        error(formatArgs("Failed to invoke 'uname {}'", arg))
        return null
    }
    val stdout = StringBuilder()
    while (true) {
        val line = fgets(null, 1024, stream) ?: break
        stdout.append(line)
    }
    val status = pclose(stream)
    return if (status == 0) {
        stdout.toString().trimEnd().takeIf { it.isNotEmpty() }
    } else {
        error(formatArgs("'uname {}' failed with status: {:?}", arg, status))
        null
    }
}
