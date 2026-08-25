# Immediate Actions - High-Value Files

Based on AST analysis, here are the concrete next steps.

## Summary

- **Files Present:** 26/26 (100.0%)
- **Function parity:** 184/237 matched (target 253) — 77.6%
- **Class/type parity:** 11/12 matched (target 46) — 91.7%
- **Combined symbol parity:** 195/249 matched (target 299) — 78.3%
- **Average inline-code cosine:** 0.47 (function body across 11 matched files)
- **Average documentation cosine:** 0.53 (doc text across 11 matched files)
- **Cheat-zeroed Files:** 15
- **Critical Issues:** 22 files with <0.60 function similarity

## Priority 1: Fix Incomplete High-Dependency Files

No incomplete high-dependency files detected.

## Priority 2: Port Missing High-Value Files

Critical missing files (>10 dependencies):

No missing high-value files detected.

## Detailed Work Items

Every matched file is listed below with function and type symbol parity.

### 1. info

- **Target:** `osinfo.Info`
- **Similarity:** 0.32
- **Dependents:** 1
- **Priority Score:** 1071206.8
- **Functions:** 4/11 matched (target 10)
- **Missing functions:** `os_type`, `version`, `edition`, `codename`, `bitness`, `architecture`, `fmt`
- **Types:** 1/1 matched (target 2)
- **Missing types:** _none_
- **Tests:** 1/1 matched

### 2. linux.lsb_release

- **Target:** `linux.LsbRelease`
- **Similarity:** 0.64
- **Dependents:** 0
- **Priority Score:** 347203.6
- **Functions:** 37/71 matched
- **Missing functions:** `file`, `alpaquita_file`, `alt_file`, `aosc_file`, `arch_file`, `artix_file`, `fedora_file`, `kali_2023_2_file`, `ubuntu_file`, `mint_file`, `nixos_file`, `nobara_file`, `uos_file`, `amazon1_file`, `amazon2_file`, `rhel8_file`, `rhel7_file`, `rhel6_file`, `suse_enterprise15_1_file`, `suse_enterprise12_5_file`, `raspberry_os_file`, `open_suse_15_1_file`, `oracle_server_linux_7_5_file`, `oracle_server_linux_8_1_file`, `pop_os_20_04_lts_file`, `solus_4_1_file`, `manjaro_19_0_2_file`, `manjaro_arm_24_04`, `mariner_file`, `endeavouros_file`, `ultramarine_file`, `void_file`, `cachyos_file`, `pikaos_file`
- **Types:** 1/1 matched (target 2)
- **Missing types:** _none_
- **Tests:** 34/68 matched

### 3. linux.file_release

- **Target:** `linux.FileRelease`
- **Similarity:** 0.62
- **Dependents:** 0
- **Priority Score:** 45403.8
- **Functions:** 49/53 matched (target 55)
- **Missing functions:** `alpine_3_12_os_release`, `alpine_release`, `alt_p11_os_release`, `archarm_os_release`
- **Types:** 1/1 matched (target 2)
- **Missing types:** _none_
- **Tests:** 46/50 matched

### 4. macos.mod

- **Target:** `macos.Mod [STUB]`
- **Similarity:** 0.00
- **Dependents:** 0
- **Priority Score:** 31410.0
- **Functions:** 11/14 matched
- **Missing functions:** `sw_vers_output`, `sw_vers_output_beta`, `sw_vers_output_double_digit_patch_version`
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_
- **Tests:** 6/9 matched

### 5. bitness

- **Target:** `osinfo.Bitness`
- **Similarity:** 0.15
- **Dependents:** 0
- **Priority Score:** 20508.5
- **Functions:** 2/4 matched (target 6)
- **Missing functions:** `fmt`, `get`
- **Types:** 1/1 matched (target 2)
- **Missing types:** _none_
- **Tests:** 2/2 matched

### 6. os_type

- **Target:** `osinfo.OsType`
- **Similarity:** 0.21
- **Dependents:** 0
- **Priority Score:** 20407.9
- **Functions:** 2/3 matched
- **Missing functions:** `fmt`
- **Types:** 0/1 matched (target 2)
- **Missing types:** `Type`
- **Tests:** 1/1 matched

### 7. version

- **Target:** `osinfo.Version`
- **Similarity:** 0.59
- **Dependents:** 0
- **Priority Score:** 10704.1
- **Functions:** 5/6 matched (target 12)
- **Missing functions:** `fmt`
- **Types:** 1/1 matched (target 6)
- **Missing types:** _none_
- **Tests:** 2/2 matched

### 8. architecture

- **Target:** `osinfo.Architecture`
- **Similarity:** 0.12
- **Dependents:** 0
- **Priority Score:** 10208.8
- **Functions:** 1/2 matched (target 3)
- **Missing functions:** `get`
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_
- **Tests:** 1/1 matched

### 9. windows.winapi

- **Target:** `windows.Winapi`
- **Similarity:** 0.57
- **Dependents:** 0
- **Priority Score:** 2604.3
- **Functions:** 22/22 matched (target 24)
- **Missing functions:** _none_
- **Types:** 4/4 matched (target 6)
- **Missing types:** _none_
- **Tests:** 11/11 matched

### 10. matcher

- **Target:** `osinfo.Matcher`
- **Similarity:** 0.67
- **Dependents:** 0
- **Priority Score:** 903.3
- **Functions:** 8/8 matched
- **Missing functions:** _none_
- **Types:** 1/1 matched (target 6)
- **Missing types:** _none_
- **Tests:** 4/4 matched

### 11. uname

- **Target:** `osinfo.Uname`
- **Similarity:** 0.55
- **Dependents:** 0
- **Priority Score:** 704.5
- **Functions:** 6/6 matched (target 7)
- **Missing functions:** _none_
- **Types:** 1/1 matched (target 2)
- **Missing types:** _none_
- **Tests:** 1/1 matched

### 12. aix.mod

- **Target:** `aix.Mod [STUB]`
- **Similarity:** 0.00
- **Dependents:** 0
- **Priority Score:** 410.0
- **Functions:** 4/4 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_
- **Tests:** 1/1 matched

### 13. ios.mod

- **Target:** `ios.Mod [STUB]`
- **Similarity:** 0.00
- **Dependents:** 0
- **Priority Score:** 410.0
- **Functions:** 4/4 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_
- **Tests:** 1/1 matched

### 14. illumos.mod

- **Target:** `illumos.Mod [STUB]`
- **Similarity:** 0.00
- **Dependents:** 0
- **Priority Score:** 310.0
- **Functions:** 3/3 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_
- **Tests:** 1/1 matched

### 15. redox.mod

- **Target:** `redox.Mod [STUB]`
- **Similarity:** 0.00
- **Dependents:** 0
- **Priority Score:** 310.0
- **Functions:** 3/3 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_
- **Tests:** 1/1 matched

### 16. android.mod

- **Target:** `android.Mod [STUB]`
- **Similarity:** 0.00
- **Dependents:** 0
- **Priority Score:** 310.0
- **Functions:** 3/3 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_
- **Tests:** 1/1 matched

### 17. freebsd.mod

- **Target:** `freebsd.Mod [STUB]`
- **Similarity:** 0.00
- **Dependents:** 0
- **Priority Score:** 310.0
- **Functions:** 3/3 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_
- **Tests:** 1/1 matched

### 18. cygwin.mod

- **Target:** `cygwin.Mod [STUB]`
- **Similarity:** 0.00
- **Dependents:** 0
- **Priority Score:** 210.0
- **Functions:** 2/2 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_
- **Tests:** 1/1 matched

### 19. windows.mod

- **Target:** `windows.Mod [STUB]`
- **Similarity:** 0.00
- **Dependents:** 0
- **Priority Score:** 210.0
- **Functions:** 2/2 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_
- **Tests:** 1/1 matched

### 20. openbsd.mod

- **Target:** `openbsd.Mod [STUB]`
- **Similarity:** 0.00
- **Dependents:** 0
- **Priority Score:** 210.0
- **Functions:** 2/2 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_
- **Tests:** 1/1 matched

### 21. unknown.mod

- **Target:** `unknown.Mod [STUB]`
- **Similarity:** 0.00
- **Dependents:** 0
- **Priority Score:** 210.0
- **Functions:** 2/2 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_
- **Tests:** 1/1 matched

### 22. dragonfly.mod

- **Target:** `dragonfly.Mod [STUB]`
- **Similarity:** 0.00
- **Dependents:** 0
- **Priority Score:** 210.0
- **Functions:** 2/2 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_
- **Tests:** 1/1 matched

### 23. netbsd.mod

- **Target:** `netbsd.Mod [STUB]`
- **Similarity:** 0.00
- **Dependents:** 0
- **Priority Score:** 210.0
- **Functions:** 2/2 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_
- **Tests:** 1/1 matched

### 24. emscripten.mod

- **Target:** `emscripten.Mod [STUB]`
- **Similarity:** 0.00
- **Dependents:** 0
- **Priority Score:** 210.0
- **Functions:** 2/2 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_
- **Tests:** 1/1 matched

### 25. linux.mod

- **Target:** `linux.Mod [STUB]`
- **Similarity:** 0.00
- **Dependents:** 0
- **Priority Score:** 210.0
- **Functions:** 2/2 matched (target 4)
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_
- **Tests:** 1/1 matched

### 26. lib

- **Target:** `osinfo.Lib`
- **Similarity:** 0.78
- **Dependents:** 0
- **Priority Score:** 102.2
- **Functions:** 1/1 matched (target 2)
- **Missing functions:** _none_
- **Types:** 0/0 matched
- **Missing types:** _none_

## Success Criteria

For each file to be considered "complete":
- **Similarity ≥ 0.85** (Excellent threshold)
- All public APIs ported
- All tests ported
- Documentation ported
- port-lint header present

