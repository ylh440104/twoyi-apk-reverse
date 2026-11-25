#!/usr/bin/env python3
"""Check CI's debug APK packaging without claiming device/runtime compatibility."""
import hashlib
import json
from pathlib import Path
import zipfile

root = Path(__file__).resolve().parents[1]
apks = list((root / 'app/build/outputs/apk/debug').glob('*.apk'))
if len(apks) != 1:
    raise SystemExit(f'Expected one debug APK, found {len(apks)}')
apk = apks[0]
expected = {
    'lib/arm64-v8a/libtwoyi.so': root / 'app/src/main/jniLibs/arm64-v8a/libtwoyi.so',
    'lib/arm64-v8a/libloader.so': root / 'app/src/main/jniLibs/arm64-v8a/libloader.so',
    'lib/arm64-v8a/libOpenglRender.so': root / 'app/src/main/jniLibs/arm64-v8a/libOpenglRender.so',
    'assets/rootfs.7z': root / 'app/src/main/assets/rootfs.7z',
    'assets/rom.ini': root / 'app/src/main/assets/rom.ini',
}

def digest(stream):
    value = hashlib.sha256()
    for block in iter(lambda: stream.read(1024 * 1024), b''):
        value.update(block)
    return value.hexdigest()

report = {'apk': apk.name, 'checks': {}, 'scope': 'packaging only; no device execution'}
with zipfile.ZipFile(apk) as archive:
    for name in ['AndroidManifest.xml', 'classes.dex', 'lib/arm64-v8a/libp7zip.so', *expected]:
        if archive.getinfo(name).file_size == 0:
            raise SystemExit(f'Empty required APK entry: {name}')
    for name, source in expected.items():
        with archive.open(name) as packaged, source.open('rb') as original:
            actual, wanted = digest(packaged), digest(original)
        # AGP may strip native debug symbols; assets must remain byte-identical.
        if name.startswith('assets/') and actual != wanted:
            raise SystemExit(f'Packaged bytes do not match build input: {name}')
        report['checks'][name] = {'packaged_sha256': actual, 'input_sha256': wanted}
with apk.open('rb') as stream:
    report['sha256'] = digest(stream)
report['bytes'] = apk.stat().st_size
output = root / 'app/build/reports/apk-packaging.json'
output.parent.mkdir(parents=True, exist_ok=True)
output.write_text(json.dumps(report, indent=2) + '\n')
print(output.read_text())
