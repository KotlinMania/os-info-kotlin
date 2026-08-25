// port-lint: tests windows/winapi.rs
package io.github.kotlinmania.osinfo.windows

import io.github.kotlinmania.osinfo.Bitness
import io.github.kotlinmania.osinfo.OsType
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class WinapiTest {
    @Test
    fun version() {
        val info = get()
        assertEquals(OsType.Windows, info.osType)
    }

    @Test
    fun getVersionInfo() {
        val v = versionInfo()
        assertNotNull(v)
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
                listOf(5L, 0L, 0.toShort(), "Windows 2000"),
            )

        val info = versionInfo()!!

        for (item in testData) {
            info.dwMajorVersion = item[0] as Long
            info.dwMinorVersion = item[1] as Long
            info.wProductType = item[2] as Short

            val ed = edition(info)
            assertEquals(item[3] as String, ed)
        }
    }

    @Test
    fun getBitness() {
        val b = bitness()
        assertNotEquals(Bitness.Unknown, b)
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

        for ((arch, expected) in cpuTypes) {
            nativeInfo.wProcessorArchitecture = arch
            assertEquals(expected, architecture(nativeInfo))
        }
    }

    @Test
    fun getProductName() {
        val v = versionInfo()!!
        val prod = productName(v)!!
        assertTrue(prod.isNotEmpty())
    }

    @Test
    fun toWideStr() {
        val data =
            listOf(
                "" to listOf(0u.toUShort()),
                "U" to listOf('U'.code.toUShort(), 0u.toUShort()),
            )

        for ((s, expected) in data) {
            val wide = toWide(s)
            assertEquals(expected, wide)
        }
    }
}
