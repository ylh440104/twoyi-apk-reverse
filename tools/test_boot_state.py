#!/usr/bin/env python3
"""Run the real boot state class against tiny Android API stubs on CI.

This is a host JVM concurrency test, not an Android instrumentation test.
No APK, Android SDK, ROM or prebuilt libraries are needed.
"""
from pathlib import Path
import subprocess
import tempfile

ROOT = Path(__file__).resolve().parents[1]
SOURCES = {
    'android/content/Context.java': '''package android.content;
public class Context { public void startActivity(Intent intent) {} }
''',
    'android/content/Intent.java': '''package android.content;
public class Intent {
 public static final String ACTION_MAIN="main", CATEGORY_HOME="home";
 public static final int FLAG_ACTIVITY_NEW_TASK=1, FLAG_ACTIVITY_CLEAR_TOP=2;
 public Intent(String action) {} public Intent(Context c, Class<?> target) {}
 public void addCategory(String c) {} public void setFlags(int flags) {}
}
''',
    'io/twoyi/Render2Activity.java': 'package io.twoyi; public class Render2Activity {}',
    'io/twoyi/BootStateTest.java': '''package io.twoyi;
import java.util.concurrent.*;
public class BootStateTest {
 static final TwoyiStatusManager state=TwoyiStatusManager.getInstance();
 static void check(boolean value,String message) {
  if (!value) throw new AssertionError(message);
 }
 public static void main(String[] args) throws Exception {
  ExecutorService pool=Executors.newCachedThreadPool();
  try {
   state.reset();
   check(!state.waitBoot(1,TimeUnit.MILLISECONDS),"unbooted state succeeds");
   pool.submit(() -> state.markStarted()).get(1,TimeUnit.SECONDS);
   check(state.waitBoot(1,TimeUnit.SECONDS),"timeout poisoned next wait");
   check(state.waitBoot(1,TimeUnit.SECONDS),"second observer lost completion");
   state.markStarted();
   check(state.isStarted(),"duplicate completion clears state");
   state.reset();
   check(!state.isStarted(),"reset retains completed state");
   Thread[] observer = new Thread[1];
   CountDownLatch entered = new CountDownLatch(1);
   Future<Boolean> oldWait=pool.submit(() -> {
    observer[0]=Thread.currentThread(); entered.countDown();
    try { return !state.waitBoot(10,TimeUnit.SECONDS); }
    catch (BrokenBarrierException expected) { return true; }
   });
   entered.await();
   long deadline=System.nanoTime()+TimeUnit.SECONDS.toNanos(2);
   while (observer[0].getState()!=Thread.State.TIMED_WAITING) {
    if (System.nanoTime()>deadline) throw new AssertionError("observer never waited");
    Thread.yield();
   }
   state.reset();
   check(oldWait.get(1,TimeUnit.SECONDS),"old observer reports success after reset");
   state.markStarted();
   check(state.waitBoot(1,TimeUnit.SECONDS),"new boot cannot complete");
   System.out.println("PASS: timeout recovery, nonblocking completion, repeated observers, reset");
  } finally { pool.shutdownNow(); }
 }
}
''',
}
with tempfile.TemporaryDirectory(prefix='twoyi-boot-test-') as temp:
    directory = Path(temp)
    for name, text in SOURCES.items():
        target = directory / name
        target.parent.mkdir(parents=True, exist_ok=True)
        target.write_text(text)
    source = ROOT / 'app/src/main/java/io/twoyi/TwoyiStatusManager.java'
    subprocess.run(['javac', '-d', str(directory), str(source),
                    *[str(directory / name) for name in SOURCES]], check=True)
    subprocess.run(['java', '-cp', str(directory), 'io.twoyi.BootStateTest'],
                   check=True, timeout=20)
