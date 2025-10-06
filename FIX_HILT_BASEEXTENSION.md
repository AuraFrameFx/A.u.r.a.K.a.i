# Fix: Hilt "Android BaseExtension not found" Error

## Problem
```
Build file 'C:\Aurakai\app\build.gradle.kts' line: 1

An exception occurred applying plugin request [id: 'com.google.dagger.hilt.android', version: '2.57.2']
> Failed to apply plugin 'com.google.dagger.hilt.android'.
   > Android BaseExtension not found.
```

## Root Cause

The app module's `build.gradle.kts` incorrectly included `id("com.android.base")` on line 4. This plugin is an AGP 9.0 alpha workaround that is **ONLY needed for library modules**, not application modules.

### Why This Matters

- **Application modules** (using `com.android.application`):
  - Can apply Hilt plugin directly
  - The standard Android application plugin already provides the necessary BaseExtension
  - Adding `id("com.android.base")` causes conflicts with Hilt initialization

- **Library modules** (using `com.android.library`):  
  - Should NOT apply the Hilt plugin directly
  - Need `id("com.android.base")` as a workaround for AGP 9.0 alpha
  - This is because the library plugin doesn't expose BaseExtension properly in alpha builds

## Solution

Removed the incorrect line from `app/build.gradle.kts`:

**Before:**
```kotlin
plugins {
    id("genesis.android.application")
    id("com.android.base")  // ❌ WRONG - causes BaseExtension error
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
}
```

**After:**
```kotlin
plugins {
    id("genesis.android.application")
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
}
```

## Correct Configuration Reference

### Application Module (app/build.gradle.kts)
```kotlin
plugins {
    id("genesis.android.application")  // Applies com.android.application internally
    alias(libs.plugins.hilt)             // ✅ Hilt works fine with application plugin
    alias(libs.plugins.ksp)
}
```

### Library Modules (e.g., romtools/build.gradle.kts, feature-module/build.gradle.kts)
```kotlin
plugins {
    id("com.android.library")
    id("com.android.base")  // ✅ AGP 9.0 alpha workaround for library modules
    // Do NOT apply Hilt plugin here
}
```

## Additional Changes

1. **Updated test**: `app/src/test/kotlin/dev/aurakai/auraframefx/BuildGradleKtsTest.kt`
   - Now checks for the actual plugins used in the build file (convention plugins + aliases)
   - Validates that the correct pattern is maintained

2. **Updated documentation**: `checkpoint.md`
   - Clarified that application modules should NOT use `id("com.android.base")`
   - Added explicit warning about the error this causes
   - Corrected historical note that previously described applying this workaround to the app module

## Files Changed

- `app/build.gradle.kts` - Removed incorrect `id("com.android.base")` plugin (1 line)
- `app/src/test/kotlin/dev/aurakai/auraframefx/BuildGradleKtsTest.kt` - Updated to test actual plugin usage
- `checkpoint.md` - Updated documentation to prevent future mistakes

## Testing

To verify the fix works:

```bash
./gradlew :app:assembleDebug
```

This should now succeed without the "Android BaseExtension not found" error.

## References

- Project documentation: `checkpoint.md`, `CLEANUP.TXT`
- Hilt issue is specific to AGP 9.0 alpha builds
- Convention plugin implementation: `build-logic/src/main/kotlin/AndroidApplicationConventionPlugin.kt`
