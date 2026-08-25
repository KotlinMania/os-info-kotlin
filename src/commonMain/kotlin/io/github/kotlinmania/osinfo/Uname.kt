// port-lint: source uname.rs
package io.github.kotlinmania.osinfo

enum class UnameField {
    Sysname,
    Release,
    Version,
    Machine,
    Nodename,
    OperatingSystem,
    ;

    fun cliArgName(): String =
        when (this) {
            Sysname -> "-s"
            Release -> "-r"
            Version -> "-v"
            Machine -> "-m"
            Nodename -> "-n"
            OperatingSystem -> "-o"
        }

    fun supportsUnameSyscall(): Boolean = this != OperatingSystem

    fun getFromSyscall(): String? = null
}

fun uname(field: UnameField): String? =
    field.getFromSyscall() ?: unameCli(field.cliArgName())

fun unameCli(arg: String): String? = null
