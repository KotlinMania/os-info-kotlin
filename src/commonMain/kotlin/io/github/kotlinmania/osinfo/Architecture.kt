// port-lint: source architecture.rs
package io.github.kotlinmania.osinfo

/**
 * Returns the architecture of the current system if available.
 */
fun getArchitecture(): String? = uname(UnameField.Machine)

/**
 * Alias for getArchitecture.
 */
fun architecture(): String? = getArchitecture()
