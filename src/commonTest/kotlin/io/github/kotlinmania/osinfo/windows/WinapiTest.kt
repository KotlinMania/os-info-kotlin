// port-lint: tests windows/winapi.rs
package io.github.kotlinmania.osinfo.windows

import io.github.kotlinmania.osinfo.Bitness
import io.github.kotlinmania.osinfo.OsType
import io.github.kotlinmania.prettyassertions.assertEq
import io.github.kotlinmania.prettyassertions.assertNe
import kotlin.test.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class WinapiTest {
    @Test
    fun version() {
        val info = get()
        assertEq(OsType.Windows, info.osType())
    }

    @Test
    fun getVersionInfo() {
        val version = versionInfo()
        assertNotNull(version)
    }

    @Test
    fun getEdition() {
        val testData: List<List<Any>> =
            listOf(
                listOf(10L, 0L, 0.toShort(), "Windows Server 2016"),
                listOf(6L, 3L, VER_NT_WORKSTATION, "Windows 8.1"),
                listOf(6L, 3L, 0.toShort(), "Windows Server 2012 R2"),
                listOf(6L, 2L, VER_NT_WORKSTATION, "Windows 8"),
                listOf(6L, 2L, 0.toShort(), "Windows Server 2012"),
                listOf(6L, 1L, VER_NT_WORKSTATION, "Windows 7"),
                listOf(6L, 1L, 0.toShort(), "Windows Server 2008 R2"),
                listOf(6L, 0L, VER_NT_WORKSTATION, "Windows Vista"),
                listOf(6L, 0L, 0.toShort(), "Windows Server 2008"),
                listOf(5L, 1L, 0.toShort(), "Windows XP"),
                listOf(5L, 1L, 1.toShort(), "Windows XP"),
                listOf(5L, 1L, 100.toShort(), "Windows XP"),
                listOf(5L, 0L, 0.toShort(), "Windows 2000"),
                listOf(5L, 0L, 1.toShort(), "Windows 2000"),
                listOf(5L, 0L, 100.toShort(), "Windows 2000"),
            )

        val info = checkNotNull(versionInfo())

        for (item in testData) {
            info.dwMajorVersion = item[0] as Long
            info.dwMinorVersion = item[1] as Long
            info.wProductType = item[2] as Short

            val edition = checkNotNull(edition(info))
            assertEq(edition, item[3] as String)
        }
    }

    @Test
    fun getBitness() {
        val b = bitness()
        assertNe(b, Bitness.Unknown)
    }

    @Test
    fun emptyModuleName() {
        assertFailsWith<IllegalArgumentException> {
            getProcAddress(byteArrayOf(), "RtlGetVersion\u0000".encodeToByteArray())
        }
    }

    @Test
    fun nonZeroTerminatedModuleName() {
        assertFailsWith<IllegalArgumentException> {
            getProcAddress("ntdll".encodeToByteArray(), "RtlGetVersion\u0000".encodeToByteArray())
        }
    }

    @Test
    fun emptyProcName() {
        assertFailsWith<IllegalArgumentException> {
            getProcAddress("ntdll\u0000".encodeToByteArray(), byteArrayOf())
        }
    }

    @Test
    fun nonZeroTerminatedProcName() {
        assertFailsWith<IllegalArgumentException> {
            getProcAddress("ntdll\u0000".encodeToByteArray(), "RtlGetVersion".encodeToByteArray())
        }
    }

    @Test
    fun procAddress() {
        val address = getProcAddress("ntdll\u0000".encodeToByteArray(), "RtlGetVersion\u0000".encodeToByteArray())
        assertNotNull(address)
    }

    @Test
    fun getArchitecture() {
        val cpuTypes: List<Pair<UShort, String?>> =
            listOf(
                PROCESSOR_ARCHITECTURE_AMD64 to "x86_64",
                PROCESSOR_ARCHITECTURE_ARM to "arm",
                PROCESSOR_ARCHITECTURE_ARM64 to "aarch64",
                PROCESSOR_ARCHITECTURE_IA64 to "ia64",
                PROCESSOR_ARCHITECTURE_INTEL to "i386",
                0xffffu.toUShort() to null,
            )

        val nativeInfo = nativeSystemInfo()

        for (cpuType in cpuTypes) {
            nativeInfo.wProcessorArchitecture = cpuType.first
            assertEq(architecture(nativeInfo), cpuType.second)
        }
    }

    @Test
    fun getProductName() {
        val version = checkNotNull(versionInfo()) { "version_info() failed" }
        val edition = checkNotNull(productName(version)) { "edition() failed" }
        assertTrue(edition.isNotEmpty())
    }

    @Test
    fun toWideStr() {
        val data =
            listOf(
                "" to listOf<UShort>(0x0000u),
                "U" to listOf<UShort>(0x0055u, 0x0000u),
                "你好" to listOf<UShort>(0x4F60u, 0x597Du, 0x0000u),
            )

        for ((s, expected) in data) {
            val wide = toWide(s)
            assertEq(wide, expected)
        }
    }
}
