// port-lint: source src/os_type.rs
package io.github.kotlinmania.osinfo

/**
 * A list of supported operating system types.
 *
 * Annotated upstream as `#[non_exhaustive]`: new entries may be added without breaking source
 * compatibility. The upstream Rust enum also derives `Serialize`, `Deserialize`, and `JsonSchema`
 * behind the `serde` / `schemars` feature flags; those are translated as serializer plug-ins on
 * the consumer side, not as derives baked into the enum here.
 */
enum class Type {
    /** IBM AIX (https://en.wikipedia.org/wiki/IBM_AIX). */
    AIX,

    /** AlmaLinux (https://en.wikipedia.org/wiki/AlmaLinux). */
    AlmaLinux,

    /** Alpaquita Linux (https://bell-sw.com/alpaquita-linux/). */
    Alpaquita,

    /** Alpine Linux (https://en.wikipedia.org/wiki/Alpine_Linux). */
    Alpine,

    /** ALT Linux (https://en.wikipedia.org/wiki/ALT_Linux). */
    ALTLinux,

    /** Amazon Linux AMI (https://en.wikipedia.org/wiki/Amazon_Machine_Image#Amazon_Linux_AMI). */
    Amazon,

    /** Android (https://en.wikipedia.org/wiki/Android_(operating_system)). */
    Android,

    /** AOSC OS (https://aosc.io/aosc-os/). */
    AOSC,

    /** Arch Linux (https://en.wikipedia.org/wiki/Arch_Linux). */
    Arch,

    /** Artix Linux (https://en.wikipedia.org/wiki/Artix_Linux). */
    Artix,

    /** Bluefin (https://projectbluefin.io). */
    Bluefin,

    /** CachyOS (https://en.wikipedia.org/wiki/Arch_Linux#Derivatives). */
    CachyOS,

    /** CentOS (https://en.wikipedia.org/wiki/CentOS). */
    CentOS,

    /** Cygwin (https://en.wikipedia.org/wiki/Cygwin). */
    Cygwin,

    /** Debian (https://en.wikipedia.org/wiki/Debian). */
    Debian,

    /** DragonFly BSD (https://en.wikipedia.org/wiki/DragonFly_BSD). */
    DragonFly,

    /** Elementary OS (https://en.wikipedia.org/wiki/Elementary_OS). */
    Elementary,

    /** Emscripten (https://en.wikipedia.org/wiki/Emscripten). */
    Emscripten,

    /** EndeavourOS (https://en.wikipedia.org/wiki/EndeavourOS). */
    EndeavourOS,

    /** Fedora (https://en.wikipedia.org/wiki/Fedora_(operating_system)). */
    Fedora,

    /** FreeBSD (https://en.wikipedia.org/wiki/FreeBSD). */
    FreeBSD,

    /** Garuda Linux (https://en.wikipedia.org/wiki/Garuda_Linux) */
    Garuda,

    /** Gentoo Linux (https://en.wikipedia.org/wiki/Gentoo_Linux). */
    Gentoo,

    /** HardenedBSD (https://hardenedbsd.org/). */
    HardenedBSD,

    /** Illumos (https://en.wikipedia.org/wiki/Illumos). */
    Illumos,

    /** instantOS (https://instantos.io/). */
    InstantOS,

    /** iOS (https://en.wikipedia.org/wiki/iOS). */
    Ios,

    /** Kali Linux (https://en.wikipedia.org/wiki/Kali_Linux). */
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

    /** Mint (https://en.wikipedia.org/wiki/Linux_Mint). */
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

    /**
     * openEuler (https://en.wikipedia.org/wiki/EulerOS).
     *
     * Upstream Rust spells this `openEuler` (lower-case first letter, intentionally
     * non-`UpperCamelCase`). The Kotlin port follows Kotlin's enum-entry naming convention
     * (`UpperCamelCase`) for the identifier and preserves the upstream public-display string
     * `"EulerOS"` in `toString`.
     */
    OpenEuler,

    /**
     * openSUSE (https://en.wikipedia.org/wiki/OpenSUSE).
     *
     * Upstream Rust spells this `openSUSE` (lower-case first letter). The Kotlin port follows
     * Kotlin's enum-entry naming convention (`UpperCamelCase`) for the identifier and preserves
     * the upstream public-display string `"openSUSE"` in `toString`.
     */
    OpenSUSE,

    /** Oracle Linux (https://en.wikipedia.org/wiki/Oracle_Linux). */
    OracleLinux,

    /** PikaOS (https://wiki.pika-os.com/en/home) */
    PikaOS,

    /** Pop!_OS (https://en.wikipedia.org/wiki/Pop!_OS) */
    Pop,

    /**
     * Raspberry Pi OS (https://en.wikipedia.org/wiki/Raspberry_Pi_OS).
     *
     * Upstream `serde` deserialization additionally accepts the legacy alias `"RaspberryPiOS"`.
     */
    Raspbian,

    /** Red Hat Linux (https://en.wikipedia.org/wiki/Red_Hat_Linux). */
    Redhat,

    /** Red Hat Enterprise Linux (https://en.wikipedia.org/wiki/Red_Hat_Enterprise_Linux). */
    RedHatEnterprise,

    /** Redox (https://en.wikipedia.org/wiki/Redox_(operating_system)). */
    Redox,

    /** Rocky Linux (https://en.wikipedia.org/wiki/Rocky_Linux). */
    RockyLinux,

    /** Solus (https://en.wikipedia.org/wiki/Solus_(operating_system)). */
    Solus,

    /** SUSE Linux Enterprise Server (https://en.wikipedia.org/wiki/SUSE_Linux_Enterprise). */
    SUSE,

    /** Ubuntu (https://en.wikipedia.org/wiki/Ubuntu_(operating_system)). */
    Ubuntu,

    /** Ultramarine (https://ultramarine-linux.org/). */
    Ultramarine,

    /** Uos (https://uos.uniontech.com/). */
    Uos,

    /** Void Linux (https://en.wikipedia.org/wiki/Void_Linux). */
    Void,

    /** Zorin OS (https://en.wikipedia.org/wiki/Zorin_OS). */
    Zorin,

    /** Unknown operating system. */
    Unknown,

    /** Windows (https://en.wikipedia.org/wiki/Microsoft_Windows). */
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

    companion object {
        /** Default value, equivalent to upstream `Default::default()`: returns [Unknown]. */
        fun default(): Type = Unknown
    }
}
