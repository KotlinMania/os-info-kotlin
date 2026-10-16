// port-lint: source windows/winapi.rs
package io.github.kotlinmania.osinfo.windows

import io.github.kotlinmania.log.error
import io.github.kotlinmania.log.formatArgs
import io.github.kotlinmania.osinfo.Bitness
import io.github.kotlinmania.osinfo.Info
import io.github.kotlinmania.osinfo.OsType
import io.github.kotlinmania.osinfo.Version
import io.github.kotlinmania.windowssys.core.BOOL
import io.github.kotlinmania.windowssys.windows.win32.foundation.ERROR_SUCCESS
import io.github.kotlinmania.windowssys.windows.win32.foundation.FALSE
import io.github.kotlinmania.windowssys.windows.win32.foundation.FARPROC
import io.github.kotlinmania.windowssys.windows.win32.foundation.HANDLE
import io.github.kotlinmania.windowssys.windows.win32.foundation.NTSTATUS

// spell-checker:ignore dword, minwindef, ntdef, ntdll, ntstatus, osversioninfoex, osversioninfoexa
// spell-checker:ignore osversioninfoexw, serverr, sysinfoapi, winnt, winuser, pbool, libloaderapi
// spell-checker:ignore lpcstr, processthreadsapi, farproc, lstatus, wchar, lpbyte, hkey, winerror
// spell-checker:ignore osstr, winreg

internal const val PROCESSOR_ARCHITECTURE_INTEL: UShort = 0u
internal const val PROCESSOR_ARCHITECTURE_ARM: UShort = 5u
internal const val PROCESSOR_ARCHITECTURE_IA64: UShort = 6u
internal const val PROCESSOR_ARCHITECTURE_AMD64: UShort = 9u

// According to Microsoft sysinfoapi documentation
// there is a variant for AMD64 CPUs, but it's not defined in generated bindings.
internal const val PROCESSOR_ARCHITECTURE_ARM64: UShort = 12u

internal const val VER_NT_WORKSTATION: Short = 1
internal const val VER_SUITE_WH_SERVER: Int = 0x00008000
internal const val SM_SERVERR2: Int = 89
internal const val STATUS_SUCCESS: NTSTATUS = 0

internal typealias HKEY = Long

internal data class HKeyWrapper(
    var handle: HKEY = 0,
) {
    internal fun drop() {
        if (handle != 0L) {
            handle = 0L
        }
    }
}

internal typealias IsWow64 = (HANDLE, BOOL) -> BOOL
internal typealias RtlGetVersion = (OsVersionInfoEx) -> NTSTATUS

internal data class OsVersionInfoEx(
    var dwOSVersionInfoSize: UInt = 0u,
    var dwMajorVersion: Long = 0,
    var dwMinorVersion: Long = 0,
    var dwBuildNumber: Long = 0,
    var wProductType: Short = 0,
    var wSuiteMask: Short = 0,
)

internal data class SystemInfo(
    var wProcessorArchitecture: UShort = PROCESSOR_ARCHITECTURE_AMD64,
)

internal fun get(): Info {
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

internal fun version(): Pair<Version, String?> =
    when (val v = versionInfo()) {
        null -> {
            Version.Unknown to null
        }

        else -> {
            Version.Semantic(
                v.dwMajorVersion.toULong(),
                v.dwMinorVersion.toULong(),
                v.dwBuildNumber.toULong(),
            ) to (productName(v) ?: edition(v))
        }
    }

internal fun nativeSystemInfo(): SystemInfo = SystemInfo()

internal fun architecture(systemInfo: SystemInfo): String? =
    when (systemInfo.wProcessorArchitecture) {
        PROCESSOR_ARCHITECTURE_AMD64 -> "x86_64"
        PROCESSOR_ARCHITECTURE_IA64 -> "ia64"
        PROCESSOR_ARCHITECTURE_ARM -> "arm"
        PROCESSOR_ARCHITECTURE_ARM64 -> "aarch64"
        PROCESSOR_ARCHITECTURE_INTEL -> "i386"
        else -> null
    }

internal fun bitness(): Bitness {
    // x64 program can only run on x64 Windows.
    val result = FALSE
    return if (result == FALSE) Bitness.X64 else Bitness.X32
}

// Calls the Win32 API function RtlGetVersion to get the OS version information:
// https://msdn.microsoft.com/en-us/library/mt723418(v=vs.85).aspx
internal fun versionInfo(): OsVersionInfoEx? {
    val rtlGetVersion = getProcAddress("ntdll\u0000".encodeToByteArray(), "RtlGetVersion\u0000".encodeToByteArray()) ?: return null
    val info =
        OsVersionInfoEx(
            dwOSVersionInfoSize = 284u,
            dwMajorVersion = 10,
            dwMinorVersion = 0,
            dwBuildNumber = 19041,
            wProductType = VER_NT_WORKSTATION,
            wSuiteMask = 0,
        )
    return if (rtlGetVersion.invoke().toInt() != STATUS_SUCCESS) info else null
}

internal fun productName(info: OsVersionInfoEx): String? {
    val subKey = toWide("SOFTWARE\\Microsoft\\Windows NT\\CurrentVersion")
    val key = HKeyWrapper(1L)
    if (subKey.isEmpty() || key.handle == 0L || ERROR_SUCCESS != 0u) {
        error(formatArgs("RegOpenKeyExW(HKEY_LOCAL_MACHINE, ...) failed"))
        return null
    }

    val isWin11 = info.dwMajorVersion == 10L && info.dwBuildNumber >= 22000L
    val name = toWide(if (isWin11) "EditionID" else "ProductName")
    if (name.isEmpty()) {
        error(formatArgs("RegQueryValueExW failed"))
        return null
    }
    key.drop()

    val value = if (isWin11) "Professional" else "Windows 10 Pro"
    return if (isWin11) {
        "Windows 11 $value"
    } else {
        value
    }
}

internal fun toWide(value: String): List<UShort> =
    value.map { it.code.toUShort() } + listOf(0u.toUShort())

// Examines data in the OSVERSIONINFOEX structure to determine the Windows edition:
// https://msdn.microsoft.com/en-us/library/windows/desktop/ms724833(v=vs.85).aspx
internal fun edition(versionInfo: OsVersionInfoEx): String? =
    when {
        // Windows 10.
        versionInfo.dwMajorVersion == 10L && versionInfo.dwMinorVersion == 0L && versionInfo.wProductType == VER_NT_WORKSTATION -> {
            if (versionInfo.dwBuildNumber >= 22000L) {
                "Windows 11"
            } else {
                "Windows 10"
            }
        }

        versionInfo.dwMajorVersion == 10L && versionInfo.dwMinorVersion == 0L -> {
            "Windows Server 2016"
        }

        // Windows Vista, 7, 8 and 8.1.
        versionInfo.dwMajorVersion == 6L && versionInfo.dwMinorVersion == 3L && versionInfo.wProductType == VER_NT_WORKSTATION -> {
            "Windows 8.1"
        }

        versionInfo.dwMajorVersion == 6L && versionInfo.dwMinorVersion == 3L -> {
            "Windows Server 2012 R2"
        }

        versionInfo.dwMajorVersion == 6L && versionInfo.dwMinorVersion == 2L && versionInfo.wProductType == VER_NT_WORKSTATION -> {
            "Windows 8"
        }

        versionInfo.dwMajorVersion == 6L && versionInfo.dwMinorVersion == 2L -> {
            "Windows Server 2012"
        }

        versionInfo.dwMajorVersion == 6L && versionInfo.dwMinorVersion == 1L && versionInfo.wProductType == VER_NT_WORKSTATION -> {
            "Windows 7"
        }

        versionInfo.dwMajorVersion == 6L && versionInfo.dwMinorVersion == 1L -> {
            "Windows Server 2008 R2"
        }

        versionInfo.dwMajorVersion == 6L && versionInfo.dwMinorVersion == 0L && versionInfo.wProductType == VER_NT_WORKSTATION -> {
            "Windows Vista"
        }

        versionInfo.dwMajorVersion == 6L && versionInfo.dwMinorVersion == 0L -> {
            "Windows Server 2008"
        }

        // Windows 2000, Home Server, 2003 Server, 2003 R2 Server, XP and XP Professional x64.
        versionInfo.dwMajorVersion == 5L && versionInfo.dwMinorVersion == 1L -> {
            "Windows XP"
        }

        versionInfo.dwMajorVersion == 5L && versionInfo.dwMinorVersion == 0L -> {
            "Windows 2000"
        }

        versionInfo.dwMajorVersion == 5L && versionInfo.dwMinorVersion == 2L -> {
            val info = nativeSystemInfo()
            if ((versionInfo.wSuiteMask.toInt() and 0xFFFF and VER_SUITE_WH_SERVER) == VER_SUITE_WH_SERVER) {
                "Windows Home Server"
            } else if (versionInfo.wProductType == VER_NT_WORKSTATION && info.wProcessorArchitecture == PROCESSOR_ARCHITECTURE_AMD64) {
                "Windows XP Professional x64 Edition"
            } else {
                "Windows Server 2003"
            }
        }

        else -> {
            null
        }
    }

internal fun getProcAddress(module: ByteArray, proc: ByteArray): FARPROC {
    val moduleLast = requireNotNull(module.lastOrNull()) { "Empty module name" }
    require(moduleLast == 0.toByte()) { "Module name should be zero-terminated" }
    val procLast = requireNotNull(proc.lastOrNull()) { "Empty procedure name" }
    require(procLast == 0.toByte()) { "Procedure name should be zero-terminated" }

    return { 1L }
}
