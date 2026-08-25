// port-lint: tests linux/file_release.rs
package io.github.kotlinmania.osinfo.linux

import io.github.kotlinmania.osinfo.OsType
import io.github.kotlinmania.osinfo.Version
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class FileReleaseTest {
    private fun osRelease(content: String): (String) -> String? =
        { path -> if (path.endsWith("etc/os-release")) content else null }

    private fun centosRelease(content: String): (String) -> String? =
        { path -> if (path.endsWith("etc/centos-release")) content else null }

    private fun fedoraRelease(content: String): (String) -> String? =
        { path -> if (path.endsWith("etc/fedora-release")) content else null }

    private fun redhatRelease(content: String): (String) -> String? =
        { path -> if (path.endsWith("etc/redhat-release")) content else null }

    @Test
    fun almalinux90Release() {
        val fileContent = "NAME=\"AlmaLinux\"\nVERSION=\"9.0 (Emerald Quark)\"\nID=\"almalinux\"\nVERSION_ID=\"9.0\"\n"
        val info = retrieve(DISTRIBUTIONS, "/", osRelease(fileContent))
        assertNotNull(info)
        assertEquals(OsType.AlmaLinux, info.osType)
        assertEquals(Version.Semantic(9u, 0u, 0u), info.version)
    }

    @Test
    fun alpaquitaOsRelease() {
        val fileContent =
            "NAME=\"Alpaquita Linux\"\n" +
                "VERSION=\"Stream 23 (musl)\"\n" +
                "ID=\"alpaquita\"\n" +
                "ID_LIKE=\"alpine\"\n" +
                "VERSION_ID=\"23\"\n" +
                "PRETTY_NAME=\"BellSoft Alpaquita Linux Stream 23 (musl)\"\n" +
                "HOME_URL=\"https://bell-sw.com/alpaquita-linux/\"\n"
        val info = retrieve(DISTRIBUTIONS, "/", osRelease(fileContent))
        assertNotNull(info)
        assertEquals(OsType.Alpaquita, info.osType)
        assertEquals(Version.Semantic(23u, 0u, 0u), info.version)
    }

    @Test
    fun alpineOsRelease() {
        val fileContent = "NAME=\"Alpine Linux\"\nID=alpine\nVERSION_ID=3.8.1\nPRETTY_NAME=\"Alpine Linux v3.8\"\n"
        val info = retrieve(DISTRIBUTIONS, "/", osRelease(fileContent))
        assertNotNull(info)
        assertEquals(OsType.Alpine, info.osType)
        assertEquals(Version.Semantic(3u, 8u, 1u), info.version)
    }

    @Test
    fun altlinuxOsRelease() {
        val fileContent = "NAME=\"ALT\"\nVERSION=\"11 (Salvia)\"\nID=altlinux\nVERSION_ID=11\n"
        val info = retrieve(DISTRIBUTIONS, "/", osRelease(fileContent))
        assertNotNull(info)
        assertEquals(OsType.ALTLinux, info.osType)
        assertEquals(Version.Semantic(11u, 0u, 0u), info.version)
    }

    @Test
    fun amazon1OsRelease() {
        val fileContent = "NAME=\"Amazon Linux AMI\"\nVERSION=\"2018.03\"\nID=\"amzn\"\nVERSION_ID=\"2018.03\"\n"
        val info = retrieve(DISTRIBUTIONS, "/", osRelease(fileContent))
        assertNotNull(info)
        assertEquals(OsType.Amazon, info.osType)
        assertEquals(Version.Semantic(2018u, 3u, 0u), info.version)
    }

    @Test
    fun amazon2OsRelease() {
        val fileContent = "NAME=\"Amazon Linux\"\nVERSION=\"2\"\nID=\"amzn\"\nVERSION_ID=\"2\"\n"
        val info = retrieve(DISTRIBUTIONS, "/", osRelease(fileContent))
        assertNotNull(info)
        assertEquals(OsType.Amazon, info.osType)
        assertEquals(Version.Semantic(2u, 0u, 0u), info.version)
    }

    @Test
    fun aoscOsRelease() {
        val fileContent = "NAME=\"AOSC OS\"\nVERSION=\"12.3.1\"\nID=\"aosc\"\nVERSION_ID=\"12.3.1\"\n"
        val info = retrieve(DISTRIBUTIONS, "/", osRelease(fileContent))
        assertNotNull(info)
        assertEquals(OsType.AOSC, info.osType)
        assertEquals(Version.Semantic(12u, 3u, 1u), info.version)
    }

    @Test
    fun archOsRelease() {
        val fileContent = "NAME=\"Arch Linux\"\nID=arch\nPRETTY_NAME=\"Arch Linux\"\n"
        val info = retrieve(DISTRIBUTIONS, "/", osRelease(fileContent))
        assertNotNull(info)
        assertEquals(OsType.Arch, info.osType)
        assertEquals(Version.Unknown, info.version)
    }

    @Test
    fun artixOsRelease() {
        val fileContent = "NAME=\"Artix Linux\"\nID=artix\nPRETTY_NAME=\"Artix Linux\"\n"
        val info = retrieve(DISTRIBUTIONS, "/", osRelease(fileContent))
        assertNotNull(info)
        assertEquals(OsType.Artix, info.osType)
        assertEquals(Version.Unknown, info.version)
    }

    @Test
    fun bluefinOsRelease() {
        val fileContent = "NAME=\"Bluefin\"\nVERSION=\"41\"\nID=bluefin\nVERSION_ID=\"41\"\n"
        val info = retrieve(DISTRIBUTIONS, "/", osRelease(fileContent))
        assertNotNull(info)
        assertEquals(OsType.Bluefin, info.osType)
        assertEquals(Version.Semantic(41u, 0u, 0u), info.version)
    }

    @Test
    fun instantOsRelease() {
        val fileContent = "NAME=\"instantOS\"\nID=instantos\n"
        val info = retrieve(DISTRIBUTIONS, "/", osRelease(fileContent))
        assertNotNull(info)
        assertEquals(OsType.InstantOS, info.osType)
        assertEquals(Version.Unknown, info.version)
    }

    @Test
    fun centos7OsRelease() {
        val fileContent = "NAME=\"CentOS Linux\"\nVERSION=\"7 (Core)\"\nID=\"centos\"\nVERSION_ID=\"7\"\n"
        val info = retrieve(DISTRIBUTIONS, "/", osRelease(fileContent))
        assertNotNull(info)
        assertEquals(OsType.CentOS, info.osType)
        assertEquals(Version.Semantic(7u, 0u, 0u), info.version)
    }

    @Test
    fun centosStreamOsRelease() {
        val fileContent = "NAME=\"CentOS Stream\"\nVERSION=\"8\"\nID=\"centos\"\nVERSION_ID=\"8\"\n"
        val info = retrieve(DISTRIBUTIONS, "/", osRelease(fileContent))
        assertNotNull(info)
        assertEquals(OsType.CentOS, info.osType)
        assertEquals(Version.Semantic(8u, 0u, 0u), info.version)
    }

    @Test
    fun centosRelease() {
        val fileContent = "CentOS release XX\n"
        val info = retrieve(DISTRIBUTIONS, "/", centosRelease(fileContent))
        assertNotNull(info)
        assertEquals(OsType.CentOS, info.osType)
        assertEquals(Version.Custom("XX"), info.version)
    }

    @Test
    fun centosReleaseUnknown() {
        val fileContent = "CentOS release\n"
        val info = retrieve(DISTRIBUTIONS, "/", centosRelease(fileContent))
        assertNotNull(info)
        assertEquals(OsType.CentOS, info.osType)
        assertEquals(Version.Unknown, info.version)
    }

    @Test
    fun debian11OsRelease() {
        val fileContent = "NAME=\"Debian GNU/Linux\"\nVERSION_ID=\"11\"\nVERSION=\"11 (bullseye)\"\nID=debian\n"
        val info = retrieve(DISTRIBUTIONS, "/", osRelease(fileContent))
        assertNotNull(info)
        assertEquals(OsType.Debian, info.osType)
        assertEquals(Version.Semantic(11u, 0u, 0u), info.version)
    }

    @Test
    fun fedora32OsRelease() {
        val fileContent = "NAME=Fedora\nVERSION=\"32 (Workstation Edition)\"\nID=fedora\nVERSION_ID=32\n"
        val info = retrieve(DISTRIBUTIONS, "/", osRelease(fileContent))
        assertNotNull(info)
        assertEquals(OsType.Fedora, info.osType)
        assertEquals(Version.Semantic(32u, 0u, 0u), info.version)
    }

    @Test
    fun fedora35OsRelease() {
        val fileContent = "NAME=\"Fedora Linux\"\nVERSION=\"35 (Workstation Edition)\"\nID=fedora\nVERSION_ID=35\n"
        val info = retrieve(DISTRIBUTIONS, "/", osRelease(fileContent))
        assertNotNull(info)
        assertEquals(OsType.Fedora, info.osType)
        assertEquals(Version.Semantic(35u, 0u, 0u), info.version)
    }

    @Test
    fun fedoraRelease() {
        val fileContent = "Fedora release 26 (Twenty Six)\n"
        val info = retrieve(DISTRIBUTIONS, "/", fedoraRelease(fileContent))
        assertNotNull(info)
        assertEquals(OsType.Fedora, info.osType)
        assertEquals(Version.Semantic(26u, 0u, 0u), info.version)
    }

    @Test
    fun fedoraReleaseUnknown() {
        val fileContent = "Fedora release\n"
        val info = retrieve(DISTRIBUTIONS, "/", fedoraRelease(fileContent))
        assertNotNull(info)
        assertEquals(OsType.Fedora, info.osType)
        assertEquals(Version.Unknown, info.version)
    }

    @Test
    fun kali20232OsRelease() {
        val fileContent = "NAME=\"Kali GNU/Linux\"\nVERSION=\"2023.2\"\nID=kali\nVERSION_ID=\"2023.2\"\n"
        val info = retrieve(DISTRIBUTIONS, "/", osRelease(fileContent))
        assertNotNull(info)
        assertEquals(OsType.Kali, info.osType)
        assertEquals(Version.Semantic(2023u, 2u, 0u), info.version)
    }

    @Test
    fun manjaroArmRelease() {
        val fileContent = "NAME=\"Manjaro-ARM\"\nID=manjaro-arm\n"
        val info = retrieve(DISTRIBUTIONS, "/", osRelease(fileContent))
        assertNotNull(info)
        assertEquals(OsType.Manjaro, info.osType)
        assertEquals(Version.Unknown, info.version)
    }

    @Test
    fun marinerRelease() {
        val fileContent = "NAME=\"CBL-Mariner\"\nVERSION=\"2.0.20220210\"\nID=\"mariner\"\nVERSION_ID=\"2.0.20220210\"\n"
        val info = retrieve(DISTRIBUTIONS, "/", osRelease(fileContent))
        assertNotNull(info)
        assertEquals(OsType.Mariner, info.osType)
        assertEquals(Version.Semantic(2u, 0u, 20220210u), info.version)
    }

    @Test
    fun marinerReleaseUnknown() {
        val fileContent = "NAME=\"CBL-Mariner\"\nID=\"mariner\"\n"
        val info = retrieve(DISTRIBUTIONS, "/", osRelease(fileContent))
        assertNotNull(info)
        assertEquals(OsType.Mariner, info.osType)
        assertEquals(Version.Unknown, info.version)
    }

    @Test
    fun mintOsRelease() {
        val fileContent = "NAME=\"Linux Mint\"\nVERSION=\"20 (Ulyana)\"\nID=linuxmint\nVERSION_ID=\"20\"\n"
        val info = retrieve(DISTRIBUTIONS, "/", osRelease(fileContent))
        assertNotNull(info)
        assertEquals(OsType.Mint, info.osType)
        assertEquals(Version.Semantic(20u, 0u, 0u), info.version)
    }

    @Test
    fun nixosOsRelease() {
        val fileContent = "NAME=NixOS\nVERSION=\"21.05pre275822.916ee862e87 (Okapi)\"\nID=nixos\nVERSION_ID=\"21.05pre275822.916ee862e87\"\n"
        val info = retrieve(DISTRIBUTIONS, "/", osRelease(fileContent))
        assertNotNull(info)
        assertEquals(OsType.NixOS, info.osType)
        assertEquals(Version.Custom("21.05pre275822.916ee862e87"), info.version)
    }

    @Test
    fun nobaraOsRelease() {
        val fileContent = "NAME=\"Nobara Linux\"\nVERSION=\"39 (KDE Plasma)\"\nID=nobara\nVERSION_ID=\"39\"\n"
        val info = retrieve(DISTRIBUTIONS, "/", osRelease(fileContent))
        assertNotNull(info)
        assertEquals(OsType.Nobara, info.osType)
        assertEquals(Version.Semantic(39u, 0u, 0u), info.version)
    }

    @Test
    fun uosOsRelease() {
        val fileContent = "NAME=\"UnionTech OS\"\nVERSION=\"20\"\nID=uos\nVERSION_ID=\"20\"\n"
        val info = retrieve(DISTRIBUTIONS, "/", osRelease(fileContent))
        assertNotNull(info)
        assertEquals(OsType.Uos, info.osType)
        assertEquals(Version.Semantic(20u, 0u, 0u), info.version)
    }

    @Test
    fun noneInvalidOsRelease() {
        val fileContent = "INVALID_KEY=INVALID_VALUE\n"
        val info = retrieve(DISTRIBUTIONS, "/", osRelease(fileContent))
        assertNull(info)
    }

    @Test
    fun noneNoRelease() {
        val info = retrieve(DISTRIBUTIONS, "/") { null }
        assertNull(info)
    }

    @Test
    fun noneNoPath() {
        val info = retrieve(DISTRIBUTIONS, "/non_existent_path") { null }
        assertNull(info)
    }

    @Test
    fun opencloudosOsRelease() {
        val fileContent = "NAME=\"OpenCloudOS\"\nVERSION=\"8.6\"\nID=\"opencloudos\"\nVERSION_ID=\"8.6\"\n"
        val info = retrieve(DISTRIBUTIONS, "/", osRelease(fileContent))
        assertNotNull(info)
        assertEquals(OsType.OpenCloudOS, info.osType)
        assertEquals(Version.Semantic(8u, 6u, 0u), info.version)
    }

    @Test
    fun openeulerOsRelease() {
        val fileContent = "NAME=\"openEuler\"\nVERSION=\"22.03\"\nID=\"openeuler\"\nVERSION_ID=\"22.3\"\n"
        val info = retrieve(DISTRIBUTIONS, "/", osRelease(fileContent))
        assertNotNull(info)
        assertEquals(OsType.OpenEuler, info.osType)
        assertEquals(Version.Semantic(22u, 3u, 0u), info.version)
    }

    @Test
    fun opensuseTumbleweedOsRelease() {
        val fileContent = "NAME=\"openSUSE Tumbleweed\"\nVERSION=\"20230816\"\nID=\"opensuse-tumbleweed\"\nVERSION_ID=\"20230816\"\n"
        val info = retrieve(DISTRIBUTIONS, "/", osRelease(fileContent))
        assertNotNull(info)
        assertEquals(OsType.OpenSUSE, info.osType)
        assertEquals(Version.Semantic(20230816u, 0u, 0u), info.version)
    }

    @Test
    fun oracleLinuxOsRelease() {
        val fileContent = "NAME=\"Oracle Linux Server\"\nVERSION=\"8.1\"\nID=\"ol\"\nVERSION_ID=\"8.1\"\n"
        val info = retrieve(DISTRIBUTIONS, "/", osRelease(fileContent))
        assertNotNull(info)
        assertEquals(OsType.OracleLinux, info.osType)
        assertEquals(Version.Semantic(8u, 1u, 0u), info.version)
    }

    @Test
    fun rhel8OsRelease() {
        val fileContent = "NAME=\"Red Hat Enterprise Linux\"\nVERSION=\"8.2 (Ootpa)\"\nID=\"rhel\"\nVERSION_ID=\"8.2\"\n"
        val info = retrieve(DISTRIBUTIONS, "/", osRelease(fileContent))
        assertNotNull(info)
        assertEquals(OsType.RedHatEnterprise, info.osType)
        assertEquals(Version.Semantic(8u, 2u, 0u), info.version)
    }

    @Test
    fun rhel7OsRelease() {
        val fileContent = "NAME=\"Red Hat Enterprise Linux Server\"\nVERSION=\"7.9 (Maipo)\"\nID=\"rhel\"\nVERSION_ID=\"7.9\"\n"
        val info = retrieve(DISTRIBUTIONS, "/", osRelease(fileContent))
        assertNotNull(info)
        assertEquals(OsType.RedHatEnterprise, info.osType)
        assertEquals(Version.Semantic(7u, 9u, 0u), info.version)
    }

    @Test
    fun redhatRelease() {
        val fileContent = "Red Hat Enterprise Linux release XX (Santiago)\n"
        val info = retrieve(DISTRIBUTIONS, "/", redhatRelease(fileContent))
        assertNotNull(info)
        assertEquals(OsType.RedHatEnterprise, info.osType)
        assertEquals(Version.Custom("XX"), info.version)
    }

    @Test
    fun redhatReleaseUnknown() {
        val fileContent = "Red Hat Enterprise Linux release\n"
        val info = retrieve(DISTRIBUTIONS, "/", redhatRelease(fileContent))
        assertNotNull(info)
        assertEquals(OsType.RedHatEnterprise, info.osType)
        assertEquals(Version.Unknown, info.version)
    }

    @Test
    fun rocky92Release() {
        val fileContent = "NAME=\"Rocky Linux\"\nVERSION=\"9.2 (Blue Onyx)\"\nID=\"rocky\"\nVERSION_ID=\"9.2\"\n"
        val info = retrieve(DISTRIBUTIONS, "/", osRelease(fileContent))
        assertNotNull(info)
        assertEquals(OsType.RockyLinux, info.osType)
        assertEquals(Version.Semantic(9u, 2u, 0u), info.version)
    }

    @Test
    fun suse12OsRelease() {
        val fileContent = "NAME=\"SLES\"\nVERSION=\"12-SP5\"\nID=\"sles\"\nVERSION_ID=\"12.5\"\n"
        val info = retrieve(DISTRIBUTIONS, "/", osRelease(fileContent))
        assertNotNull(info)
        assertEquals(OsType.SUSE, info.osType)
        assertEquals(Version.Semantic(12u, 5u, 0u), info.version)
    }

    @Test
    fun suse15OsRelease() {
        val fileContent = "NAME=\"SLES\"\nVERSION=\"15-SP2\"\nID=\"sles\"\nVERSION_ID=\"15.2\"\n"
        val info = retrieve(DISTRIBUTIONS, "/", osRelease(fileContent))
        assertNotNull(info)
        assertEquals(OsType.SUSE, info.osType)
        assertEquals(Version.Semantic(15u, 2u, 0u), info.version)
    }

    @Test
    fun ubuntuOsRelease() {
        val fileContent = "NAME=\"Ubuntu\"\nVERSION=\"18.10 (Cosmic Cuttlefish)\"\nID=ubuntu\nVERSION_ID=\"18.10\"\n"
        val info = retrieve(DISTRIBUTIONS, "/", osRelease(fileContent))
        assertNotNull(info)
        assertEquals(OsType.Ubuntu, info.osType)
        assertEquals(Version.Semantic(18u, 10u, 0u), info.version)
    }

    @Test
    fun ultramarineOsRelease() {
        val fileContent = "NAME=\"Ultramarine Linux\"\nVERSION=\"39 (Kuma)\"\nID=ultramarine\nVERSION_ID=\"39\"\n"
        val info = retrieve(DISTRIBUTIONS, "/", osRelease(fileContent))
        assertNotNull(info)
        assertEquals(OsType.Ultramarine, info.osType)
        assertEquals(Version.Semantic(39u, 0u, 0u), info.version)
    }

    @Test
    fun voidOsRelease() {
        val fileContent = "NAME=\"Void Linux\"\nID=\"void\"\n"
        val info = retrieve(DISTRIBUTIONS, "/", osRelease(fileContent))
        assertNotNull(info)
        assertEquals(OsType.Void, info.osType)
        assertEquals(Version.Unknown, info.version)
    }

    @Test
    fun cachyOsRelease() {
        val fileContent = "NAME=\"CachyOS\"\nID=cachyos\n"
        val info = retrieve(DISTRIBUTIONS, "/", osRelease(fileContent))
        assertNotNull(info)
        assertEquals(OsType.CachyOS, info.osType)
        assertEquals(Version.Unknown, info.version)
    }

    @Test
    fun pikaOsRelease() {
        val fileContent = "NAME=\"PikaOS\"\nVERSION=\"4 (Nest)\"\nID=pika\nVERSION_ID=\"4\"\n"
        val info = retrieve(DISTRIBUTIONS, "/", osRelease(fileContent))
        assertNotNull(info)
        assertEquals(OsType.PikaOS, info.osType)
        assertEquals(Version.Semantic(4u, 0u, 0u), info.version)
    }

    @Test
    fun releaseInfoDebug() {
        assertEquals("etc/os-release", DISTRIBUTIONS[0].path)
    }
}
