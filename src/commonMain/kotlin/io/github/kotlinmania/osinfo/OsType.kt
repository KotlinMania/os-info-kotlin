// port-lint: source os_type.rs
package io.github.kotlinmania.osinfo

import kotlinx.serialization.Serializable

/**
 * A list of supported operating system types.
 */
@Serializable
enum class OsType {
    /** IBM AIX (https://en.wikipedia.org/wiki/IBM%5FAIX). */
    AIX,

    /** AlmaLinux (https://en.wikipedia.org/wiki/AlmaLinux). */
    AlmaLinux,

    /** Alpaquita Linux (https://bell-sw.com/alpaquita-linux/). */
    Alpaquita,

    /** Alpine Linux (https://en.wikipedia.org/wiki/Alpine%5FLinux). */
    Alpine,

    /** ALT Linux (https://en.wikipedia.org/wiki/ALT%5FLinux). */
    ALTLinux,

    /** Amazon Linux AMI (https://en.wikipedia.org/wiki/Amazon%5FMachine%5FImage#Amazon%5FLinux%5FAMI). */
    Amazon,

    /** Android (https://en.wikipedia.org/wiki/Android%5F(operating%5Fsystem)). */
    Android,

    /** AOSC OS (https://aosc.io/aosc-os/). */
    AOSC,

    /** Arch Linux (https://en.wikipedia.org/wiki/Arch%5FLinux). */
    Arch,

    /** Artix Linux (https://en.wikipedia.org/wiki/Artix%5FLinux). */
    Artix,

    /** Bluefin (https://projectbluefin.io). */
    Bluefin,

    /** CachyOS (https://en.wikipedia.org/wiki/Arch%5FLinux#Derivatives). */
    CachyOS,

    /** CentOS (https://en.wikipedia.org/wiki/CentOS). */
    CentOS,

    /** Cygwin (https://en.wikipedia.org/wiki/Cygwin). */
    Cygwin,

    /** Debian (https://en.wikipedia.org/wiki/Debian). */
    Debian,

    /** DragonFly BSD (https://en.wikipedia.org/wiki/DragonFly%5FBSD). */
    DragonFly,

    /** Elementary OS (https://en.wikipedia.org/wiki/Elementary%5FOS). */
    Elementary,

    /** Emscripten (https://en.wikipedia.org/wiki/Emscripten). */
    Emscripten,

    /** EndeavourOS (https://en.wikipedia.org/wiki/EndeavourOS). */
    EndeavourOS,

    /** Fedora (https://en.wikipedia.org/wiki/Fedora%5F(operating%5Fsystem)). */
    Fedora,

    /** FreeBSD (https://en.wikipedia.org/wiki/FreeBSD). */
    FreeBSD,

    /** Garuda Linux (https://en.wikipedia.org/wiki/Garuda%5FLinux) */
    Garuda,

    /** Gentoo Linux (https://en.wikipedia.org/wiki/Gentoo%5FLinux). */
    Gentoo,

    /** HardenedBSD (https://hardenedbsd.org/). */
    HardenedBSD,

    /** Illumos (https://en.wikipedia.org/wiki/Illumos). */
    Illumos,

    /** instantOS (https://instantos.io/). */
    InstantOS,

    /** iOS (https://en.wikipedia.org/wiki/iOS). */
    Ios,

    /** Kali Linux (https://en.wikipedia.org/wiki/Kali%5FLinux). */
    Kali,

    /** Linux based operating system (https://en.wikipedia.org/wiki/Linux). */
    Linux,

    /** Mabox (https://maboxlinux.org/). */
    Mabox,

    /** Mac OS X/OS X/macOS (https://en.wikipedia.org/wiki/MacOS). */
    Macos,

    /** Manjaro (https://en.wikipedia.org/wiki/Manjaro). */
    Manjaro,

    /** Mariner (https://en.wikipedia.org/wiki/CBL-Mariner). */
    Mariner,

    /** MidnightBSD (https://en.wikipedia.org/wiki/MidnightBSD). */
    MidnightBSD,

    /** Mint (https://en.wikipedia.org/wiki/Linux%5FMint). */
    Mint,

    /** NetBSD (https://en.wikipedia.org/wiki/NetBSD). */
    NetBSD,

    /** NixOS (https://en.wikipedia.org/wiki/NixOS). */
    NixOS,

    /** Nobara (https://nobaraproject.org/). */
    Nobara,

    /** OpenBSD (https://en.wikipedia.org/wiki/OpenBSD). */
    OpenBSD,

    /** OpenCloudOS (https://www.opencloudos.org). */
    OpenCloudOS,

    /** openEuler (https://en.wikipedia.org/wiki/EulerOS). */
    OpenEuler,

    /** openSUSE (https://en.wikipedia.org/wiki/OpenSUSE). */
    OpenSUSE,

    /** Oracle Linux (https://en.wikipedia.org/wiki/Oracle%5FLinux). */
    OracleLinux,

    /** PikaOS (https://wiki.pika-os.com/en/home) */
    PikaOS,

    /** Pop! OS (https://en.wikipedia.org/wiki/Pop!%5FOS) */
    Pop,

    /** Raspberry Pi OS (https://en.wikipedia.org/wiki/Raspberry%5FPi%5FOS). */
    Raspbian,

    /** Red Hat Linux (https://en.wikipedia.org/wiki/Red%5FHat%5FLinux). */
    Redhat,

    /** Red Hat Enterprise Linux (https://en.wikipedia.org/wiki/Red%5FHat%5FEnterprise%5FLinux). */
    RedHatEnterprise,

    /** Redox (https://en.wikipedia.org/wiki/Redox%5F(operating%5Fsystem)). */
    Redox,

    /** Rocky Linux (https://en.wikipedia.org/wiki/Rocky%5FLinux). */
    RockyLinux,

    /** Solus (https://en.wikipedia.org/wiki/Solus%5F(operating%5Fsystem)). */
    Solus,

    /** SUSE Linux Enterprise Server (https://en.wikipedia.org/wiki/SUSE%5FLinux%5FEnterprise). */
    SUSE,

    /** Ubuntu (https://en.wikipedia.org/wiki/Ubuntu%5F(operating%5Fsystem)). */
    Ubuntu,

    /** Ultramarine (https://ultramarine-linux.org/). */
    Ultramarine,

    /** Uos (https://uos.uniontech.com/). */
    Uos,

    /** Void Linux (https://en.wikipedia.org/wiki/Void%5FLinux). */
    Void,

    /** Zorin OS (https://en.wikipedia.org/wiki/Zorin%5FOS). */
    Zorin,

    /** Unknown operating system. */
    Unknown,

    /** Windows (https://en.wikipedia.org/wiki/Microsoft%5FWindows). */
    Windows,
    ;

    internal fun fmt(): String =
        when (this) {
            Alpaquita -> "Alpaquita Linux"
            Alpine -> "Alpine Linux"
            AlmaLinux -> "AlmaLinux"
            ALTLinux -> "ALT Linux"
            Amazon -> "Amazon Linux AMI"
            AOSC -> "AOSC OS"
            Arch -> "Arch Linux"
            Bluefin -> "Bluefin"
            CachyOS -> "CachyOS Linux"
            Artix -> "Artix Linux"
            DragonFly -> "DragonFly BSD"
            Elementary -> "Elementary OS"
            Garuda -> "Garuda Linux"
            Gentoo -> "Gentoo Linux"
            Illumos -> "illumos"
            InstantOS -> "instantOS"
            Ios -> "iOS"
            Kali -> "Kali Linux"
            Macos -> "Mac OS"
            MidnightBSD -> "Midnight BSD"
            Mint -> "Linux Mint"
            Nobara -> "Nobara Linux"
            OpenEuler -> "EulerOS"
            OpenSUSE -> "openSUSE"
            OracleLinux -> "Oracle Linux"
            PikaOS -> "PikaOS"
            Pop -> "Pop!_OS"
            Raspbian -> "Raspberry Pi OS"
            Redhat -> "Red Hat Linux"
            RedHatEnterprise -> "Red Hat Enterprise Linux"
            RockyLinux -> "Rocky Linux"
            SUSE -> "SUSE Linux Enterprise Server"
            Ultramarine -> "Ultramarine Linux"
            Uos -> "UOS"
            Void -> "Void Linux"
            Zorin -> "Zorin OS"
            else -> name
        }

    override fun toString(): String = fmt()

    companion object {
        fun default(): OsType = Unknown
    }
}
