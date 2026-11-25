# twoyi-apk-reverse

Automated reverse-engineering dump of the **latest public Twoyi Android APK**
(`https://twoyi.app/android/` -> `https://twoyi.ai/download`).

Everything here is produced by `.github/workflows/decompile.yml` on GitHub-hosted runners.

* `report/apk/`        - APK entry list, sha256, dex/native/asset inventory
* `report/apktool/`    - decoded AndroidManifest.xml + decoded value resources
* `report/jadx/`       - jadx log and package tree
* `report/native/`     - ELF headers, exported symbols and strings of the packaged `.so` files
* `report/rootfs/`     - listing of the bundled internal-Android 7z, plus the boot scripts/props
                         and strings of the guest-side HAL/EGL stubs it contains
* `decompiled/sources/`- jadx Java output for the app's own packages (`io.twoyi.*`)

No APK and no music/asset binaries are redistributed; only text extracted from them.
Upstream project: https://github.com/twoyi/twoyi (MPL-2.0). For study and interoperability.
