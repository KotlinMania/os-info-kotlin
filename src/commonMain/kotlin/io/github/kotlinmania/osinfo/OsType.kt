// port-lint: source os_type.rs
package io.github.kotlinmania.osinfo

/**
 * A list of supported operating system types.
 */
enum class OsType {
    /** IBM AIX. */
    AIX,

    /** AlmaLinux. */
    AlmaLinux,

    /** Alpaquita Linux. */
    Alpaquita,

    /** Alpine Linux. */
    Alpine,

    /** ALT Linux. */
    ALTLinux,

    /** Amazon Linux AMI. */
    Amazon,

    /** Android. */
    Android,

    /** AOSC OS. */
    AOSC,

    /** Arch Linux. */
    Arch,

    /** Artix Linux. */
    Artix,

    /** Bluefin. */
    Bluefin,

    /** CachyOS. */
    CachyOS,

    /** CentOS. */
    CentOS,

    /** Cygwin. */
    Cygwin,

    /** Debian. */
    Debian,

    /** DragonFly BSD. */
    DragonFly,

    /** Elementary OS. */
    Elementary,

    /** Emscripten. */
    Emscripten,

    /** EndeavourOS. */
    EndeavourOS,

    /** Fedora. */
    Fedora,

    /** FreeBSD. */
    FreeBSD,

    /** Garuda Linux. */
    Garuda,

    /** Gentoo Linux. */
    Gentoo,

    /** HardenedBSD. */
    HardenedBSD,

    /** Illumos. */
    Illumos,

    /** instantOS. */
    InstantOS,

    /** iOS. */
    Ios,

    /** Kali Linux. */
    Kali,

    /** Linux based operating system. */
    Linux,

    /** Mabox. */
    Mabox,

    /** Mac OS X/OS X/macOS. */
    Macos,

    /** Manjaro. */
    Manjaro,

    /** Mariner. */
    Mariner,

    /** MidnightBSD. */
    MidnightBSD,

    /** Linux Mint. */
    Mint,

    /** NetBSD. */
    NetBSD,

    /** NixOS. */
    NixOS,

    /** Nobara. */
    Nobara,

    /** OpenBSD. */
    OpenBSD,

    /** OpenCloudOS. */
    OpenCloudOS,

    /** openEuler. */
    OpenEuler,

    /** openSUSE. */
    OpenSUSE,

    /** Oracle Linux. */
    OracleLinux,

    /** PikaOS. */
    PikaOS,

    /** Pop!_OS. */
    Pop,

    /** Raspberry Pi OS. */
    Raspbian,

    /** Red Hat Linux. */
    Redhat,

    /** Red Hat Enterprise Linux. */
    RedHatEnterprise,

    /** Redox. */
    Redox,

    /** Rocky Linux. */
    RockyLinux,

    /** Solus. */
    Solus,

    /** SUSE Linux Enterprise Server. */
    SUSE,

    /** Ubuntu. */
    Ubuntu,

    /** Ultramarine. */
    Ultramarine,

    /** Uos. */
    Uos,

    /** Void Linux. */
    Void,

    /** Zorin OS. */
    Zorin,

    /** Unknown operating system. */
    Unknown,

    /** Windows. */
    Windows,
    ;

    override fun toString(): String =
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
}
