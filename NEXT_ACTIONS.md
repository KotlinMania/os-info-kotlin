# Immediate Actions - High-Value Files

Based on AST analysis, here are the concrete next steps.

## Summary

- **Files Present:** 26/26 (100.0%)
- **Function parity:** 207/249 matched (target 259) — 83.1%
- **Class/type parity:** 11/12 matched (target 48) — 91.7%
- **Combined symbol parity:** 218/261 matched (target 307) — 83.5%
- **Average inline-code cosine:** 0.35 (function body across 26 matched files)
- **Average documentation cosine:** 0.25 (doc text across 26 matched files)
- **Cheat-zeroed Files:** 0
- **Critical Issues:** 26 files with <0.60 function similarity
- **Needs Review:** 0 files with 0.60-0.84 function similarity
- **Excellent:** 0 files with >=0.85 function similarity

## Priority 1: Fix Incomplete High-Dependency Files

No incomplete high-dependency files detected.

## Priority 2: Port Missing High-Value Files

Critical missing files (>10 dependencies):

No missing high-value files detected.

## Detailed Work Items

Every matched file is listed below with function and type symbol parity.

### 1. info

- **Target:** `osinfo.Info`
- **Similarity:** 0.18
- **Dependents:** 1
- **Priority Score:** 1001508.1
- **Functions:** 14/14 matched (target 17)
- **Missing functions:** _none_
- **Types:** 1/1 matched (target 2)
- **Missing types:** _none_
- **Tests:** 4/4 matched

### 2. linux.lsb_release

- **Target:** `linux.LsbRelease`
- **Similarity:** 0.23
- **Dependents:** 0
- **Priority Score:** 347207.7
- **Functions:** 37/71 matched
- **Missing functions:** `file`, `alpaquita_file`, `alt_file`, `aosc_file`, `arch_file`, `artix_file`, `fedora_file`, `kali_2023_2_file`, `ubuntu_file`, `mint_file`, `nixos_file`, `nobara_file`, `uos_file`, `amazon1_file`, `amazon2_file`, `rhel8_file`, `rhel7_file`, `rhel6_file`, `suse_enterprise15_1_file`, `suse_enterprise12_5_file`, `raspberry_os_file`, `open_suse_15_1_file`, `oracle_server_linux_7_5_file`, `oracle_server_linux_8_1_file`, `pop_os_20_04_lts_file`, `solus_4_1_file`, `manjaro_19_0_2_file`, `manjaro_arm_24_04`, `mariner_file`, `endeavouros_file`, `ultramarine_file`, `void_file`, `cachyos_file`, `pikaos_file`
- **Types:** 1/1 matched (target 2)
- **Missing types:** _none_
- **Tests:** 34/68 matched

### 3. bitness

- **Target:** `osinfo.Bitness`
- **Similarity:** 0.17
- **Dependents:** 0
- **Priority Score:** 40908.3
- **Functions:** 4/8 matched (target 5)
- **Missing functions:** `get`, `get`, `get`, `get`
- **Types:** 1/1 matched (target 2)
- **Missing types:** _none_
- **Tests:** 2/2 matched

### 4. macos.mod

- **Target:** `macos.Macos`
- **Similarity:** 0.36
- **Dependents:** 0
- **Priority Score:** 31406.4
- **Functions:** 11/14 matched
- **Missing functions:** `sw_vers_output`, `sw_vers_output_beta`, `sw_vers_output_double_digit_patch_version`
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_
- **Tests:** 6/9 matched

### 5. windows.winapi

- **Target:** `windows.Winapi`
- **Similarity:** 0.22
- **Dependents:** 0
- **Priority Score:** 12807.8
- **Functions:** 23/24 matched (target 23)
- **Missing functions:** `bitness`
- **Types:** 4/4 matched (target 7)
- **Missing types:** _none_
- **Tests:** 12/12 matched

### 6. os_type

- **Target:** `osinfo.OsType`
- **Similarity:** 0.18
- **Dependents:** 0
- **Priority Score:** 10508.2
- **Functions:** 4/4 matched (target 5)
- **Missing functions:** _none_
- **Types:** 0/1 matched (target 2)
- **Missing types:** `Type`
- **Tests:** 2/2 matched

### 7. linux.file_release

- **Target:** `linux.FileRelease`
- **Similarity:** 0.19
- **Dependents:** 0
- **Priority Score:** 5408.1
- **Functions:** 53/53 matched (target 58)
- **Missing functions:** _none_
- **Types:** 1/1 matched (target 2)
- **Missing types:** _none_
- **Tests:** 50/50 matched

### 8. version

- **Target:** `osinfo.Version`
- **Similarity:** 0.29
- **Dependents:** 0
- **Priority Score:** 907.1
- **Functions:** 8/8 matched (target 12)
- **Missing functions:** _none_
- **Types:** 1/1 matched (target 6)
- **Missing types:** _none_
- **Tests:** 4/4 matched

### 9. matcher

- **Target:** `osinfo.Matcher`
- **Similarity:** 0.33
- **Dependents:** 0
- **Priority Score:** 906.7
- **Functions:** 8/8 matched
- **Missing functions:** _none_
- **Types:** 1/1 matched (target 6)
- **Missing types:** _none_
- **Tests:** 4/4 matched

### 10. uname

- **Target:** `osinfo.Uname`
- **Similarity:** 0.23
- **Dependents:** 0
- **Priority Score:** 707.7
- **Functions:** 6/6 matched
- **Missing functions:** _none_
- **Types:** 1/1 matched (target 2)
- **Missing types:** _none_
- **Tests:** 1/1 matched

### 11. ios.mod

- **Target:** `ios.Ios`
- **Similarity:** 0.29
- **Dependents:** 0
- **Priority Score:** 407.1
- **Functions:** 4/4 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_
- **Tests:** 1/1 matched

### 12. aix.mod

- **Target:** `aix.Aix`
- **Similarity:** 0.38
- **Dependents:** 0
- **Priority Score:** 406.2
- **Functions:** 4/4 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_
- **Tests:** 1/1 matched

### 13. redox.mod

- **Target:** `redox.Redox`
- **Similarity:** 0.31
- **Dependents:** 0
- **Priority Score:** 306.9
- **Functions:** 3/3 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_
- **Tests:** 1/1 matched

### 14. freebsd.mod

- **Target:** `freebsd.Freebsd`
- **Similarity:** 0.36
- **Dependents:** 0
- **Priority Score:** 306.4
- **Functions:** 3/3 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_
- **Tests:** 1/1 matched

### 15. illumos.mod

- **Target:** `illumos.Illumos`
- **Similarity:** 0.41
- **Dependents:** 0
- **Priority Score:** 305.9
- **Functions:** 3/3 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_
- **Tests:** 1/1 matched

### 16. android.mod

- **Target:** `android.Android`
- **Similarity:** 0.50
- **Dependents:** 0
- **Priority Score:** 305.0
- **Functions:** 3/3 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_
- **Tests:** 1/1 matched

### 17. linux.mod

- **Target:** `linux.Linux`
- **Similarity:** 0.21
- **Dependents:** 0
- **Priority Score:** 207.9
- **Functions:** 2/2 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_
- **Tests:** 1/1 matched

### 18. architecture

- **Target:** `osinfo.Architecture`
- **Similarity:** 0.41
- **Dependents:** 0
- **Priority Score:** 205.9
- **Functions:** 2/2 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 2)
- **Missing types:** _none_
- **Tests:** 1/1 matched

### 19. dragonfly.mod

- **Target:** `dragonfly.Dragonfly`
- **Similarity:** 0.47
- **Dependents:** 0
- **Priority Score:** 205.3
- **Functions:** 2/2 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_
- **Tests:** 1/1 matched

### 20. cygwin.mod

- **Target:** `cygwin.Cygwin`
- **Similarity:** 0.48
- **Dependents:** 0
- **Priority Score:** 205.2
- **Functions:** 2/2 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_
- **Tests:** 1/1 matched

### 21. openbsd.mod

- **Target:** `openbsd.Openbsd`
- **Similarity:** 0.48
- **Dependents:** 0
- **Priority Score:** 205.2
- **Functions:** 2/2 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_
- **Tests:** 1/1 matched

### 22. netbsd.mod

- **Target:** `netbsd.Netbsd`
- **Similarity:** 0.48
- **Dependents:** 0
- **Priority Score:** 205.2
- **Functions:** 2/2 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_
- **Tests:** 1/1 matched

### 23. windows.mod

- **Target:** `windows.Windows`
- **Similarity:** 0.50
- **Dependents:** 0
- **Priority Score:** 205.0
- **Functions:** 2/2 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_
- **Tests:** 1/1 matched

### 24. unknown.mod

- **Target:** `unknown.Unknown`
- **Similarity:** 0.57
- **Dependents:** 0
- **Priority Score:** 204.3
- **Functions:** 2/2 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_
- **Tests:** 1/1 matched

### 25. emscripten.mod

- **Target:** `emscripten.Emscripten`
- **Similarity:** 0.58
- **Dependents:** 0
- **Priority Score:** 204.2
- **Functions:** 2/2 matched
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_
- **Tests:** 1/1 matched

### 26. lib

- **Target:** `osinfo.OsInfo`
- **Similarity:** 0.20
- **Dependents:** 0
- **Priority Score:** 108.0
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

