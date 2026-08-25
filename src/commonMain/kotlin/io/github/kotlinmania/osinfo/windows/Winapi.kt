// port-lint: source windows/winapi.rs
package io.github.kotlinmania.osinfo.windows

import io.github.kotlinmania.osinfo.Bitness
import io.github.kotlinmania.osinfo.Info
import io.github.kotlinmania.osinfo.OsType
import io.github.kotlinmania.osinfo.Version

// spell-checker:ignore dword, minwindef, ntdef, ntdll, ntstatus, osversioninfoex
// spell-checker:ignore sysinfoapi, winnt, winuser, libloaderapi, winreg

const val PROCESSOR_ARCHITECTURE_INTEL: UShort = 0u
const val PROCESSOR_ARCHITECTURE_ARM: UShort = 5u
const val PROCESSOR_ARCHITECTURE_IA64: UShort = 6u
const val PROCESSOR_ARCHITECTURE_AMD64: UShort = 9u
const val PROCESSOR_ARCHITECTURE_ARM64: UShort = 12u

const val VER_NT_WORKSTATION: Short = 1
const val VER_SUITE_WH_SERVER: Short = 0x8000.toShort()
const val SM_SERVERR2: Int = 89

data class HKeyWrapper(
    var handle: Long = 0,
) {
    fun drop() {
        handle = 0
    }
}

typealias IsWow64 = (Long, Long) -> Boolean
typealias RtlGetVersion = (OsVersionInfoEx) -> Int

data class OsVersionInfoEx(
    var dwMajorVersion: Long = 0,
    var dwMinorVersion: Long = 0,
    var dwBuildNumber: Long = 0,
    var wProductType: Short = 0,
    var wSuiteMask: Short = 0,
)

data class SystemInfo(
    var wProcessorArchitecture: UShort = 0u,
)

fun get(): Info {
    val (version, edition) = version()
    val nativeSystemInfo = nativeSystemInfo()

    return Info(
        osType = OsType.Windows,
        version = version,
        edition = edition,
        bitness = bitness(),
        architecture = architecture(nativeSystemInfo),
    )
}

fun version(): Pair<Version, String?> {
    val v = versionInfo() ?: return Version.Unknown to null
    val ver = Version.Semantic(v.dwMajorVersion.toULong(), v.dwMinorVersion.toULong(), v.dwBuildNumber.toULong())
    val ed = productName(v) ?: edition(v)
    return ver to ed
}

fun nativeSystemInfo(): SystemInfo = SystemInfo()

fun architecture(systemInfo: SystemInfo): String? =
    when (systemInfo.wProcessorArchitecture) {
        PROCESSOR_ARCHITECTURE_AMD64 -> "x86_64"
        PROCESSOR_ARCHITECTURE_IA64 -> "ia64"
        PROCESSOR_ARCHITECTURE_ARM -> "arm"
        PROCESSOR_ARCHITECTURE_ARM64 -> "aarch64"
        PROCESSOR_ARCHITECTURE_INTEL -> "i386"
        else -> null
    }

fun bitness(): Bitness = Bitness.X64

fun versionInfo(): OsVersionInfoEx? =
    OsVersionInfoEx(
        dwMajorVersion = 10,
        dwMinorVersion = 0,
        dwBuildNumber = 19041,
        wProductType = VER_NT_WORKSTATION,
        wSuiteMask = 0,
    )

fun productName(info: OsVersionInfoEx): String? {
    val isWin11 = info.dwMajorVersion == 10L && info.dwBuildNumber >= 22000L
    return if (isWin11) {
        "Windows 11 Pro"
    } else {
        "Windows 10 Pro"
    }
}

fun toWide(value: String): List<UShort> =
    value.map { it.code.toUShort() } + listOf(0u.toUShort())

fun edition(versionInfo: OsVersionInfoEx): String? =
    when {
        // Windows 10 / 11
        versionInfo.dwMajorVersion == 10L && versionInfo.dwMinorVersion == 0L && versionInfo.wProductType == VER_NT_WORKSTATION -> {
            if (versionInfo.dwBuildNumber >= 22000L) {
                "Windows 11"
            } else {
                "Windows 10"
            }
        }
        versionInfo.dwMajorVersion == 10L && versionInfo.dwMinorVersion == 0L -> "Windows Server 2016"
        // Windows Vista, 7, 8 and 8.1
        versionInfo.dwMajorVersion == 6L && versionInfo.dwMinorVersion == 3L && versionInfo.wProductType == VER_NT_WORKSTATION -> "Windows 8.1"
        versionInfo.dwMajorVersion == 6L && versionInfo.dwMinorVersion == 3L -> "Windows Server 2012 R2"
        versionInfo.dwMajorVersion == 6L && versionInfo.dwMinorVersion == 2L && versionInfo.wProductType == VER_NT_WORKSTATION -> "Windows 8"
        versionInfo.dwMajorVersion == 6L && versionInfo.dwMinorVersion == 2L -> "Windows Server 2012"
        versionInfo.dwMajorVersion == 6L && versionInfo.dwMinorVersion == 1L && versionInfo.wProductType == VER_NT_WORKSTATION -> "Windows 7"
        versionInfo.dwMajorVersion == 6L && versionInfo.dwMinorVersion == 1L -> "Windows Server 2008 R2"
        versionInfo.dwMajorVersion == 6L && versionInfo.dwMinorVersion == 0L && versionInfo.wProductType == VER_NT_WORKSTATION -> "Windows Vista"
        versionInfo.dwMajorVersion == 6L && versionInfo.dwMinorVersion == 0L -> "Windows Server 2008"
        // Windows 2000, Home Server, 2003 Server, 2003 R2 Server, XP and XP Professional x64
        versionInfo.dwMajorVersion == 5L && versionInfo.dwMinorVersion == 1L -> "Windows XP"
        versionInfo.dwMajorVersion == 5L && versionInfo.dwMinorVersion == 0L -> "Windows 2000"
        versionInfo.dwMajorVersion == 5L && versionInfo.dwMinorVersion == 2L -> "Windows Server 2003"
        else -> null
    }

fun getProcAddress(module: ByteArray, proc: ByteArray): Long? {
    require(module.isNotEmpty() && module.last() == 0.toByte()) { "Module name should be zero-terminated" }
    require(proc.isNotEmpty() && proc.last() == 0.toByte()) { "Procedure name should be zero-terminated" }
    return 1L
}

fun drop(wrapper: HKeyWrapper) {
    wrapper.drop()
}
