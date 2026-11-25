# 验证记录

## 开源基线回归测试（首批）

- GitHub Actions: https://github.com/ylh440104/twoyi-apk-reverse/actions/runs/45587662013
- 验证分支: verify-opensource-boot-input
- 远端验证提交: 8726c8f0fe4d98267e66cb637dbb565e552ced65
- boot-state: success。编译实际 TwoyiStatusManager 类，使用小型 Android API stub 验证超时后恢复、通知不阻塞、重复观察、重置旧会话。
- input-stream: success。直接编译生产代码使用的 event_stream.rs，验证发送端关闭后排空退出，以及写错误时不等待发送端关闭而立即退出。
- 本机未运行 javac、rustc、Android 构建或反编译。

## 尚未验证

- 完整 Android APK 与 ARM64 JNI 库构建。
- Android LocalSocket 的设备端断线/重连行为。
- 旧 ROM 实际启动、按键释放、多指触控和窗口重连。
- 新版功能等价性。

这些 host 回归测试不代表完整 Android/ROM 集成测试通过。验证分支只发布测试必需源文件，尚非完整开发分支。
