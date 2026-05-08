# os-info-kotlin in Kotlin

[![GitHub link](https://img.shields.io/badge/GitHub-KotlinMania%2Fos--info--kotlin-blue.svg)](https://github.com/KotlinMania/os-info-kotlin)
[![Maven Central](https://img.shields.io/maven-central/v/io.github.kotlinmania/os-info-kotlin)](https://central.sonatype.com/artifact/io.github.kotlinmania/os-info-kotlin)
[![Build status](https://img.shields.io/github/actions/workflow/status/KotlinMania/os-info-kotlin/ci.yml?branch=main)](https://github.com/KotlinMania/os-info-kotlin/actions)

This is a Kotlin Multiplatform line-by-line transliteration port of [`stanislav-tkach/os_info`](https://github.com/stanislav-tkach/os_info).

**Original Project:** This port is based on [`stanislav-tkach/os_info`](https://github.com/stanislav-tkach/os_info). All design credit and project intent belong to the upstream authors; this repository is a faithful port to Kotlin Multiplatform with no behavioural changes intended.

### Porting status

This is an **in-progress port**. The goal is feature parity with the upstream Rust crate while providing a native Kotlin Multiplatform API. Every Kotlin file carries a `// port-lint: source <path>` header naming its upstream Rust counterpart so the AST-distance tool can track provenance.

---

## Upstream README — `stanislav-tkach/os_info`

> The text below is reproduced and lightly edited from [`https://github.com/stanislav-tkach/os_info`](https://github.com/stanislav-tkach/os_info). It is the upstream project's own description and remains under the upstream authors' authorship; links have been rewritten to absolute upstream URLs so they continue to resolve from this repository.

## os_info

**Status:**
[![CI](https://github.com/stanislav-tkach/os_info/workflows/CI/badge.svg)](https://github.com/stanislav-tkach/os_info/actions)
[![Coverage](https://codecov.io/gh/stanislav-tkach/os_info/branch/master/graph/badge.svg)](https://codecov.io/gh/stanislav-tkach/os_info)
[![Dependency status](https://deps.rs/repo/github/stanislav-tkach/os_info/status.svg)](https://deps.rs/repo/github/stanislav-tkach/os_info)

**Project info:**
[![Docs.rs](https://docs.rs/os_info/badge.svg)](https://docs.rs/os_info)
[![Latest version](https://img.shields.io/crates/v/os_info.svg)](https://crates.io/crates/os_info)
[![License](https://img.shields.io/github/license/stanislav-tkach/os_info.svg)](https://github.com/stanislav-tkach/os_info/blob/master/LICENSE)

**Project details:**
[![LoC](https://tokei.rs/b1/github/stanislav-tkach/os_info)](https://github.com/stanislav-tkach/os_info)
![Rust 1.60+ required](https://img.shields.io/badge/rust-1.60+-blue.svg?label=Required%20Rust)

## Overview

This project consists of two parts: the library that can be used to detect the
operating system type (including version and bitness) and the command line tool
that uses the library.

### Library (`os_info`)

#### `os_info` usage

To use this crate, add `os_info` as a dependency to your project's Cargo.toml:

```toml
[dependencies]
os_info = "3"
```

This project has `serde` as an optional dependency, so if you don't need it, then
you can speed up compilation disabling it:

```toml
[dependencies]
os_info = { version = "3", default-features = false }
```

#### Example

```rust
let info = os_info::get();

// Print full information:
println!("OS information: {info}");

// Print information separately:
println!("Type: {}", info.os_type());
println!("Version: {}", info.version());
println!("Bitness: {}", info.bitness());
println!("Architecture: {}", info.architecture());
```

### Command line tool (`os_info_cli`)

A simple wrapper around the `os_info` library.

#### Installation

This tool can be installed using the following cargo command:

```console
cargo install os_info_cli
```

#### `os_info_cli` usage

Despite being named `os_info_cli` during installation, it is actually named
`os_info`. You can use the `--help` flag to see available options:

```console
os_info --help
```

## Supported operating systems

Right now, the following operating system types can be returned:

- AIX
- AlmaLinux
- Alpaquita Linux
- Alpine Linux
- Amazon Linux AMI
- Android
- AOSC OS
- Arch Linux
- Artix Linux
- Bazzite
- CachyOS
- CentOS
- Debian
- DragonFly BSD
- Elementary OS
- Emscripten
- EndeavourOS
- Fedora
- FreeBSD
- Garuda Linux
- Gentoo Linux
- HardenedBSD
- Hurd
- illumos
- iOS
- Kali Linux
- KDE neon
- Linux
- Mabox
- macOS (Mac OS X or OS X)
- Manjaro
- Mariner
- MidnightBSD
- Mint
- NetBSD
- NixOS
- Nobara Linux
- OpenBSD
- OpenCloudOS
- openEuler (EulerOS)
- openSUSE
- Oracle Linux
- Pop!_OS
- Raspberry Pi OS
- Red Hat Linux
- Red Hat Enterprise Linux
- Redox
- Rocky Linux
- Solus
- SUSE Linux Enterprise Server
- Ubuntu
- Ultramarine Linux
- Unknown
- Void Linux
- Windows

If you need support for more OS types, I am looking forward to your Pull Request.

## License

`os_info` is licensed under the MIT license. See [LICENSE] for the details.

[lsb_release]: http://refspecs.linuxbase.org/LSB_2.0.1/LSB-PDA/LSB-PDA/lsbrelease.html

[LICENSE]: https://github.com/stanislav-tkach/os_info/blob/master/LICENSE

---

## About this Kotlin port

### Installation

```kotlin
dependencies {
    implementation("io.github.kotlinmania:os-info-kotlin:0.1.0")
}
```

### Building

```bash
./gradlew build
./gradlew test
```

### Targets

- macOS arm64
- Linux x64
- Windows mingw-x64
- iOS arm64 / simulator-arm64 (Swift export + XCFramework)
- JS (browser + Node.js)
- Wasm-JS (browser + Node.js)
- Android (API 24+)

### Porting guidelines

See [AGENTS.md](AGENTS.md) and [CLAUDE.md](CLAUDE.md) for translator discipline, port-lint header convention, and Rust → Kotlin idiom mapping.

### License

This Kotlin port is distributed under the same MIT license as the upstream [`stanislav-tkach/os_info`](https://github.com/stanislav-tkach/os_info). See [LICENSE](LICENSE) (and any sibling `LICENSE-*` / `NOTICE` files mirrored from upstream) for the full text.

Original work copyrighted by the os_info authors.  
Kotlin port: Copyright (c) 2026 Sydney Renee and The Solace Project.

### Acknowledgments

Thanks to the [`stanislav-tkach/os_info`](https://github.com/stanislav-tkach/os_info) maintainers and contributors for the original Rust implementation. This port reproduces their work in Kotlin Multiplatform; bug reports about upstream design or behavior should go to the upstream repository.
