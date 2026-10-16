// port-lint: source architecture.rs
package io.github.kotlinmania.osinfo

internal object Architecture {
    internal fun get(): String? = uname(UnameField.Machine)
}
