// port-lint: tests linux/lsb_release.rs
package io.github.kotlinmania.osinfo.linux

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class LsbReleaseTest {
    @Test
    fun debian() {
        val parseResults = parse(file())
        assertEquals("Debian", parseResults.distribution)
        assertEquals("7.8", parseResults.version)
        assertEquals("wheezy", parseResults.codename)
    }

    @Test
    fun alpaquita() {
        val parseResults = parse(alpaquitaFile())
        assertEquals("Alpaquita", parseResults.distribution)
        assertEquals("23", parseResults.version)
        assertNull(parseResults.codename)
    }

    @Test
    fun altlinux() {
        val parseResults = parse(altFile())
        assertEquals("ALT", parseResults.distribution)
        assertEquals("n/a", parseResults.version)
        assertEquals("Salvia", parseResults.codename)
    }

    @Test
    fun aosc() {
        val parseResults = parse(aoscFile())
        assertEquals("AOSC", parseResults.distribution)
        assertEquals("12.3.1", parseResults.version)
        assertEquals("localhost", parseResults.codename)
    }

    @Test
    fun arch() {
        val parseResults = parse(archFile())
        assertEquals("Arch", parseResults.distribution)
        assertEquals("rolling", parseResults.version)
        assertNull(parseResults.codename)
    }

    @Test
    fun artix() {
        val parseResults = parse(artixFile())
        assertEquals("Artix", parseResults.distribution)
        assertEquals("rolling", parseResults.version)
        assertNull(parseResults.codename)
    }

    @Test
    fun fedora() {
        val parseResults = parse(fedoraFile())
        assertEquals("Fedora", parseResults.distribution)
        assertEquals("26", parseResults.version)
        assertEquals("TwentySix", parseResults.codename)
    }

    @Test
    fun kali20232() {
        val parseResults = parse(kali20232File())
        assertEquals("Kali", parseResults.distribution)
        assertEquals("2023.2", parseResults.version)
        assertEquals("kali-rolling", parseResults.codename)
    }

    @Test
    fun ubuntu() {
        val parseResults = parse(ubuntuFile())
        assertEquals("Ubuntu", parseResults.distribution)
        assertEquals("16.04", parseResults.version)
        assertEquals("xenial", parseResults.codename)
    }

    @Test
    fun mint() {
        val parseResults = parse(mintFile())
        assertEquals("Linuxmint", parseResults.distribution)
        assertEquals("20", parseResults.version)
        assertEquals("ulyana", parseResults.codename)
    }

    @Test
    fun nixos() {
        val parseResults = parse(nixosFile())
        assertEquals("NixOS", parseResults.distribution)
        assertEquals("21.05pre275822.916ee862e87", parseResults.version)
        assertEquals("okapi", parseResults.codename)
    }

    @Test
    fun nobara() {
        val parseResults = parse(nobaraFile())
        assertEquals("NobaraLinux", parseResults.distribution)
        assertEquals("39", parseResults.version)
        assertNull(parseResults.codename)
    }

    @Test
    fun uos() {
        val parseResults = parse(uosFile())
        assertEquals("uos", parseResults.distribution)
        assertEquals("20", parseResults.version)
        assertEquals("eagle", parseResults.codename)
    }

    @Test
    fun amazon1() {
        val parseResults = parse(amazon1File())
        assertEquals("AmazonAMI", parseResults.distribution)
        assertEquals("2018.03", parseResults.version)
        assertNull(parseResults.codename)
    }

    @Test
    fun amazon2() {
        val parseResults = parse(amazon2File())
        assertEquals("Amazon", parseResults.distribution)
        assertEquals("2", parseResults.version)
        assertEquals("Karoo", parseResults.codename)
    }

    @Test
    fun redhatEnterprise8() {
        val parseResults = parse(rhel8File())
        assertEquals("RedHatEnterprise", parseResults.distribution)
        assertEquals("8.1", parseResults.version)
        assertEquals("Ootpa", parseResults.codename)
    }

    @Test
    fun redhatEnterprise7() {
        val parseResults = parse(rhel7File())
        assertEquals("RedHatEnterpriseServer", parseResults.distribution)
        assertEquals("7.7", parseResults.version)
        assertEquals("Maipo", parseResults.codename)
    }

    @Test
    fun redhatEnterprise6() {
        val parseResults = parse(rhel6File())
        assertEquals("RedHatEnterpriseServer", parseResults.distribution)
        assertEquals("6.10", parseResults.version)
        assertEquals("Santiago", parseResults.codename)
    }

    @Test
    fun suseEnterprise151() {
        val parseResults = parse(suseEnterprise151File())
        assertEquals("SUSE", parseResults.distribution)
        assertEquals("15.1", parseResults.version)
        assertNull(parseResults.codename)
    }

    @Test
    fun suseEnterprise125() {
        val parseResults = parse(suseEnterprise125File())
        assertEquals("SUSE", parseResults.distribution)
        assertEquals("12.5", parseResults.version)
        assertNull(parseResults.codename)
    }

    @Test
    fun openSuse151() {
        val parseResults = parse(openSuse151File())
        assertEquals("openSUSE", parseResults.distribution)
        assertEquals("15.1", parseResults.version)
        assertNull(parseResults.codename)
    }

    @Test
    fun oracleLinux75() {
        val parseResults = parse(oracleServerLinux75File())
        assertEquals("OracleServer", parseResults.distribution)
        assertEquals("7.5", parseResults.version)
        assertNull(parseResults.codename)
    }

    @Test
    fun oracleLinux81() {
        val parseResults = parse(oracleServerLinux81File())
        assertEquals("OracleServer", parseResults.distribution)
        assertEquals("8.1", parseResults.version)
        assertNull(parseResults.codename)
    }

    @Test
    fun popOs2004Lts() {
        val parseResults = parse(popOs2004LtsFile())
        assertEquals("Pop", parseResults.distribution)
        assertEquals("20.04", parseResults.version)
        assertEquals("focal", parseResults.codename)
    }

    @Test
    fun solus41() {
        val parseResults = parse(solus41File())
        assertEquals("Solus", parseResults.distribution)
        assertEquals("4.1", parseResults.version)
        assertEquals("fortitude", parseResults.codename)
    }

    @Test
    fun manjaro() {
        val parseResults = parse(manjaro1902File())
        assertEquals("ManjaroLinux", parseResults.distribution)
        assertEquals("19.0.2", parseResults.version)
        assertNull(parseResults.codename)
    }

    @Test
    fun manjaroArm() {
        val parseResults = parse(manjaroArm2404())
        assertEquals("Manjaro-ARM", parseResults.distribution)
        assertEquals("24.04", parseResults.version)
        assertNull(parseResults.codename)
    }

    @Test
    fun mariner() {
        val parseResults = parse(marinerFile())
        assertEquals("Mariner", parseResults.distribution)
        assertEquals("2.0.20220210", parseResults.version)
        assertEquals("Mariner", parseResults.codename)
    }

    @Test
    fun endeavouros() {
        val parseResults = parse(endeavourosFile())
        assertEquals("EndeavourOS", parseResults.distribution)
        assertEquals("rolling", parseResults.version)
        assertNull(parseResults.codename)
    }

    @Test
    fun ultramarine() {
        val parseResults = parse(ultramarineFile())
        assertEquals("UltramarineLinux", parseResults.distribution)
        assertEquals("39", parseResults.version)
        assertEquals("kuma", parseResults.codename)
    }

    @Test
    fun voidLinux() {
        val parseResults = parse(voidFile())
        assertEquals("Void", parseResults.distribution)
        assertEquals("rolling", parseResults.version)
    }

    @Test
    fun raspbian() {
        val parseResults = parse(raspberryOsFile())
        assertEquals("Raspbian", parseResults.distribution)
        assertEquals("10", parseResults.version)
        assertNull(parseResults.codename)
    }

    @Test
    fun cachyos() {
        val parseResults = parse(cachyosFile())
        assertEquals("cachyos", parseResults.distribution)
        assertEquals("rolling", parseResults.version)
    }

    @Test
    fun pikaos() {
        val parseResults = parse(pikaosFile())
        assertEquals("Pika", parseResults.distribution)
        assertEquals("4", parseResults.version)
    }

    private fun file(): String =
        "\nDistributor ID:\tDebian\n" +
            "Description:\tDebian GNU/Linux 7.8 (wheezy)\n" +
            "Release:\t7.8\n" +
            "Codename:\twheezy\n"

    private fun alpaquitaFile(): String =
        "\nDistributor ID: Alpaquita\n" +
            "Description:    BellSoft Alpaquita Linux Stream 23 (musl)\n" +
            "Release:        23\n" +
            "Codename:       n/a"

    private fun altFile(): String =
        "\nDistributor ID: ALT\n" +
            "Description:    ALT p11 Starterkit (Salvia)\n" +
            "Release:        n/a\n" +
            "Codename:       Salvia"

    private fun aoscFile(): String =
        "\nDistributor ID: AOSC OS\n" +
            "Description:    AOSC OS\n" +
            "Release:        12.3.1\n" +
            "Codename:       localhost"

    private fun archFile(): String =
        "\nLSB Version:\t1.4\n" +
            "Distributor ID:\tArch\n" +
            "Description:\tArch Linux\n" +
            "Release:\trolling\n" +
            "Codename:\tn/a"

    private fun artixFile(): String =
        "\nLSB Version:\tn/a\n" +
            "Distributor ID:\tArtix\n" +
            "Description:\tArtix Linux\n" +
            "Release:\trolling\n" +
            "Codename:\tn/a"

    private fun fedoraFile(): String =
        "\nLSB Version:    :core-4.1-amd64:core-4.1-noarch:cxx-4.1-amd64:cxx-4.1-noarch\n" +
            "Distributor ID: Fedora\n" +
            "Description:    Fedora release 26 (Twenty Six)\n" +
            "Release:    26\n" +
            "Codename:   TwentySix\n"

    private fun kali20232File(): String =
        "\nDistributor ID: Kali\n" +
            "Description:    Kali GNU/Linux Rolling\n" +
            "Release:        2023.2\n" +
            "Codename:       kali-rolling\n"

    private fun ubuntuFile(): String =
        "Distributor ID: Ubuntu\n" +
            "Description:    Ubuntu 16.04.5 LTS\n" +
            "Release:        16.04\n" +
            "Codename:       xenial"

    private fun mintFile(): String =
        "Distributor ID:\tLinuxmint\n" +
            "Description:\t    Linux Mint 20\n" +
            "Release:\t        20\n" +
            "Codename:\t        ulyana"

    private fun nixosFile(): String =
        "Distributor ID: NixOS\n" +
            "Description:    NixOS 21.05 (Okapi)\n" +
            "Release:        21.05pre275822.916ee862e87\n" +
            "Codename:       okapi"

    private fun nobaraFile(): String =
        "LSB Version:\tn/a\n" +
            "Distributor ID:\tNobaraLinux\n" +
            "Description:\tNobara Linux 39 (KDE Plasma)\n" +
            "Release:\t39\n" +
            "Codename:\tn/a\n"

    private fun uosFile(): String =
        "Distributor ID: uos\n" +
            "Description:\t UnionTech OS 20\n" +
            "Release:\t     20\n" +
            "Codename:\t     eagle\n"

    private fun amazon1File(): String =
        "LSB Version:\t:base-4.0-amd64:base-4.0-noarch:core-4.0-amd64:core-4.0-noarch\n" +
            "Distributor ID:\tAmazonAMI\n" +
            "Description:\tAmazon Linux AMI release 2018.03\n" +
            "Release:\t2018.03\n" +
            "Codename:\tn/a\n"

    private fun amazon2File(): String =
        "LSB Version:\t:core-4.1-amd64:core-4.1-noarch\n" +
            "Distributor ID:\tAmazon\n" +
            "Description:\tAmazon Linux release 2 (Karoo)\n" +
            "Release:\t2\n" +
            "Codename:\tKaroo\n"

    private fun rhel8File(): String =
        "LSB Version:\t:core-4.1-amd64:core-4.1-noarch\n" +
            "Distributor ID:\tRedHatEnterprise\n" +
            "Description:\tRed Hat Enterprise Linux release 8.1 (Ootpa)\n" +
            "Release:\t8.1\n" +
            "Codename:\tOotpa\n"

    private fun rhel7File(): String =
        "LSB Version:\t:core-4.1-amd64:core-4.1-noarch\n" +
            "Distributor ID:\tRedHatEnterpriseServer\n" +
            "Description:\tRed Hat Enterprise Linux Server release 7.7 (Maipo)\n" +
            "Release:\t7.7\n" +
            "Codename:\tMaipo\n"

    private fun rhel6File(): String =
        "LSB Version:\t:base-4.0-amd64:base-4.0-noarch:core-4.0-amd64:core-4.0-noarch:graphics-4.0-amd64:graphics-4.0-noarch:printing-4.0-amd64:printing-4.0-noarch\n" +
            "Distributor ID:\tRedHatEnterpriseServer\n" +
            "Description:\tRed Hat Enterprise Linux Server release 6.10 (Santiago)\n" +
            "Release:\t6.10\n" +
            "Codename:\tSantiago\n"

    private fun suseEnterprise151File(): String =
        "LSB Version:\tn/a\n" +
            "Distributor ID:\tSUSE\n" +
            "Description:\tSUSE Linux Enterprise Server 15 SP1\n" +
            "Release:\t15.1\n" +
            "Codename:\tn/a\n"

    private fun suseEnterprise125File(): String =
        "LSB Version:\tn/a\n" +
            "Distributor ID:\tSUSE\n" +
            "Description:\tSUSE Linux Enterprise Server 12 SP5\n" +
            "Release:\t12.5\n" +
            "Codename:\tn/a\n"

    private fun raspberryOsFile(): String =
        "LSB Version:   n/a\n" +
            "Distributor ID: Raspbian\n" +
            "Description:    Raspbian GNU/Linux 10 (buster)\n" +
            "Release:        10\n" +
            "Codename:       n/a\n"

    private fun openSuse151File(): String =
        "LSB Version:\tn/a\n" +
            "Distributor ID:\topenSUSE\n" +
            "Description:\topenSUSE Leap 15.1\n" +
            "Release:\t15.1\n" +
            "Codename:\tn/a\n"

    private fun oracleServerLinux75File(): String =
        "LSB Version:\t:core-4.1-amd64:core-4.1-noarch\n" +
            "Distributor ID:\tOracleServer\n" +
            "Description:\tOracle Linux Server release 7.5\n" +
            "Release:\t7.5\n" +
            "Codename:\tn/a\n"

    private fun oracleServerLinux81File(): String =
        "LSB Version:\t:core-4.1-amd64:core-4.1-noarch\n" +
            "Distributor ID:\tOracleServer\n" +
            "Description:\tOracle Linux Server release 8.1\n" +
            "Release:\t8.1\n" +
            "Codename:\tn/a\n"

    private fun popOs2004LtsFile(): String =
        "No LSB modules are available.\n" +
            "Distributor ID: Pop\n" +
            "Description: Pop!_OS 20.04 LTS\n" +
            "Release: 20.04\n" +
            "Codename: focal\n"

    private fun solus41File(): String =
        "LSB Version:\t1.4\n" +
            "Distributor ID:\tSolus\n" +
            "Description:\tSolus\n" +
            "Release:\t4.1\n" +
            "Codename:\tfortitude\n"

    private fun manjaro1902File(): String =
        "LSB Version:    n/a\n" +
            "Distributor ID: ManjaroLinux\n" +
            "Description:    Manjaro Linux\n" +
            "Release:        19.0.2\n" +
            "Codename:       n/a\n"

    private fun manjaroArm2404(): String =
        "LSB Version:    n/a\n" +
            "Distributor ID: Manjaro-ARM\n" +
            "Description:    Manjaro ARM Linux\n" +
            "Release:        24.04\n" +
            "Codename:       n/a\n"

    private fun marinerFile(): String =
        "LSB Version:    n/a\n" +
            "Distributor ID: Mariner\n" +
            "Description:    CBL-Mariner 2.0.20220210\n" +
            "Release:        2.0.20220210\n" +
            "Codename:       Mariner\n"

    private fun endeavourosFile(): String =
        "LSB Version:\t1.4\n" +
            "Distributor ID:\tEndeavourOS\n" +
            "Description:\tEndeavourOS Linux\n" +
            "Release:\trolling\n" +
            "Codename:\tn/a\n"

    private fun ultramarineFile(): String =
        "LSB Version:    n/a\n" +
            "Distributor ID: UltramarineLinux\n" +
            "Description:    Ultramarine Linux 39 (Kuma)\n" +
            "Release:        39\n" +
            "Codename:       kuma\n"

    private fun voidFile(): String =
        "LSB Version:        n/a\n" +
            "  Distributor ID:        Void\n" +
            "  Description:        Void Linux\n" +
            "  Release:        rolling\n" +
            "  Codename:        n/a\n"

    private fun cachyosFile(): String =
        "Distributor ID: cachyos\n" +
            "Description:\t CachyOS\n" +
            "Release:\t     rolling\n" +
            "Codename:\t     n/a\n"

    private fun pikaosFile(): String =
        "Distributor ID: Pika\n" +
            "Description:    PikaOS 4\n" +
            "Release:        4\n" +
            "Codename:       nest\n"
}
