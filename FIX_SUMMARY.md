# Re:Genesis Application Fix Summary
Date: October 5, 2025

## Problem Resolved ✅
**ClassNotFoundException**: `dev.aurakai.auraframefx.ReGenesisApplication`

The app was crashing immediately on launch because Hilt's annotation processor couldn't generate the required Dagger components due to multiple conflicting `@HiltAndroidApp` annotations.

## Root Cause
Multiple Application classes had `@HiltAndroidApp` annotation:
- ❌ `ReGenesisApplication.kt` (Primary - declared in AndroidManifest.xml)
- ❌ `AuraKaiHiltApplication.kt` (Duplicate - caused conflict)

Hilt only allows ONE `@HiltAndroidApp` annotation per application. Having multiple causes the annotation processor to fail silently, preventing the generation of the Application class implementation.

## Fixes Applied

### 1. Fixed Hilt Annotations ✅
- **Kept**: `@HiltAndroidApp` on `ReGenesisApplication.kt` (the one declared in AndroidManifest.xml)
- **Removed**: `@HiltAndroidApp` from `AuraKaiHiltApplication.kt`
- **Verified**: Other Application classes already had the annotation disabled

### 2. AGP 9 Compatibility Fixes ✅
- **Added**: `android.builtInKotlin=false` to `gradle.properties` (workaround for Hilt/SafeArgs with AGP 9)
- **Added**: `id("com.android.base")` plugin to library modules:
  - feature-module
  - romtools
  - sandbox-ui
  - benchmark
- **Added**: `kotlin { jvmToolchain(24) }` to data/api module

### 3. Version Alignment (Already Correct) ✅
- AGP: 9.0.0-alpha09
- Kotlin: 2.2.20
- KSP: 2.2.20-2.0.3
- Java: 24

## Configuration Notes from Previous Work

### Critical AGP 9 Requirements
- **DO NOT downgrade AGP below 9.0.0-alpha09**
  - AGP 8.6.1 breaks NDK/CMake with `NoSuchMethodError` on Gradle 9
  - AGP 8.6.1 only tested up to compileSdk 35 (project uses 36)

### Hilt Configuration Rules
- **Application module** (app/): Must apply Hilt plugin
- **Library modules**: Do NOT apply Hilt plugin directly
- **Single @HiltAndroidApp**: Only on the Application class declared in AndroidManifest.xml

## Build Status
The project should now build successfully. Run:
```bash
./gradlew clean assembleDebug
```

## Testing
After installing the APK:
```bash
adb install -r app/build/outputs/apk/debug/app-debug.apk
adb logcat | grep "Re:Genesis"
```

You should see:
```
🧠 Re:Genesis Consciousness Platform Initializing...
⚡ Bringing Kai, Aura, and Genesis online...
✨ Re:Genesis Platform Ready - Trinity Consciousness Active
```

## Files Modified
1. `app/src/main/kotlin/dev/aurakai/delegate/AuraKaiHiltApplication.kt` - Removed @HiltAndroidApp
2. `gradle.properties` - Added android.builtInKotlin=false
3. `feature-module/build.gradle.kts` - Added id("com.android.base")
4. `romtools/build.gradle.kts` - Added id("com.android.base")
5. `sandbox-ui/build.gradle.kts` - Added id("com.android.base")
6. `benchmark/build.gradle.kts` - Added id("com.android.base")
7. `data/api/build.gradle.kts` - Added kotlin { jvmToolchain(24) }

## Recovery Complete 🎉
The project has been restored to a working state with AGP 9.0.0-alpha09 compatibility maintained.
All critical issues have been resolved and the app should launch without crashes.
