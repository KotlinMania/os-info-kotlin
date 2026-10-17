// port-lint: source bitness.rs
package io.github.kotlinmania.osinfo

import io.github.kotlinmania.libc.unix.bsd.popen
import io.github.kotlinmania.libc.unix.fgets
import io.github.kotlinmania.libc.unix.pclose
import kotlinx.serialization.Serializable

// spell-checker:ignore getconf

/**
 * Operating system architecture in terms of how many bits compose the basic values it can deal with.
 */
@Serializable
enum class Bitness {
    /** Unknown bitness (unable to determine). */
    Unknown,

    /** 32-bit. */
    X32,

    /** 64-bit. */
    X64,
    ;

    internal fun fmt(): String =
        when (this) {
            Unknown -> "unknown bitness"
            X32 -> "32-bit"
            X64 -> "64-bit"
        }

    override fun toString(): String = fmt()

    companion object {
        private fun runCommandOutput(command: String): String? {
            val stream = popen(command, "r") ?: return null
            val out = StringBuilder()
            while (true) {
                val line = fgets(null, 256, stream) ?: break
                out.append(line)
            }
            val status = pclose(stream)
            return if (status == 0) out.toString() else null
        }

        internal fun get(): Bitness =
            when (uname(UnameField.Sysname)) {
                "NetBSD" -> {
                    when (runCommandOutput("sysctl -n hw.machine_arch")) {
                        "amd64\n" -> X64
                        "x86_64\n" -> X64
                        "i386\n" -> X32
                        "aarch64\n" -> X64
                        "earmv7hf\n" -> X32
                        "sparc64\n" -> X64
                        else -> Unknown
                    }
                }

                "OpenBSD" -> {
                    when (runCommandOutput("sysctl -n hw.machine")) {
                        "amd64\n" -> X64
                        "x86_64\n" -> X64
                        "i386\n" -> X32
                        "aarch64\n" -> X64
                        "earmv7hf\n" -> X32
                        "sparc64\n" -> X64
                        else -> Unknown
                    }
                }

                "SunOS", "illumos" -> {
                    when (runCommandOutput("isainfo -b")) {
                        "64\n" -> X64
                        "32\n" -> X32
                        else -> Unknown
                    }
                }

                "AIX" -> {
                    when (runCommandOutput("prtconf -c")) {
                        "CPU Type: 64-bit\n" -> X64
                        "CPU Type: 32-bit\n" -> X32
                        else -> Unknown
                    }
                }

                else -> {
                    when (runCommandOutput("getconf LONG_BIT")) {
                        "32\n" -> {
                            X32
                        }

                        "64\n" -> {
                            X64
                        }

                        else -> {
                            when (uname(UnameField.Machine)) {
                                "wasm32", "i386", "earmv7hf", "x86", "arm" -> X32
                                "amd64", "x86_64", "aarch64", "arm64", "sparc64" -> X64
                                else -> Unknown
                            }
                        }
                    }
                }
            }

        @OptIn(kotlin.experimental.ExperimentalObjCRefinement::class)
        @kotlin.native.HiddenFromObjC
        fun jsonSchema(
            generator: io.github.kotlinmania.schemars.generate.SchemaGenerator =
                io.github.kotlinmania.schemars.generate.SchemaGenerator
                    .default(),
        ): io.github.kotlinmania.schemars.Schema = jsonSchemaDescriptor.jsonSchema(generator)
    }
}

@OptIn(kotlin.experimental.ExperimentalObjCRefinement::class)
@kotlin.native.HiddenFromObjC
val Bitness.Companion.jsonSchemaDescriptor: io.github.kotlinmania.schemars.JsonSchema
    get() =
        object : io.github.kotlinmania.schemars.JsonSchema {
            override fun schemaName(): String = "Bitness"

            override fun schemaId(): String = "os_info::bitness::Bitness"

            override fun jsonSchema(
                generator: io.github.kotlinmania.schemars.generate.SchemaGenerator,
            ): io.github.kotlinmania.schemars.Schema =
                io.github.kotlinmania.schemars.jsonSchema {
                    this["description"] =
                        "Operating system architecture in terms of how many bits compose the basic values it can deal with."
                    this["type"] = "string"
                    this["enum"] =
                        io.github.kotlinmania.schemars.Value.Array(
                            Bitness.entries
                                .map {
                                    io.github.kotlinmania.schemars.Value
                                        .Str(it.name)
                                }.toMutableList(),
                        )
                }
        }
