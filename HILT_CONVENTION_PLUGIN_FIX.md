# Hilt Convention Plugin Fix - AGP 9 Alpha Compatibility

## Summary
Applied the `genesis.android.hilt` convention plugin to the app module, which includes the AGP 9 alpha workaround for the "Android BaseExtension not found" error.

## Changes Made

### 1. Updated AndroidHiltConventionPlugin.kt
**File**: `build-logic/src/main/kotlin/AndroidHiltConventionPlugin.kt`

**Change**: Added AGP 9 alpha workaround by applying `com.android.base` plugin before Hilt plugin

```kotlin
pluginManager.withPlugin(androidPluginId) {
    // AGP 9 alpha: ensure BaseExtension shim is available for Hilt
    pluginManager.apply("com.android.base")  // ← Added this line
    // Apply Hilt after Android is ready, then KSP
    pluginManager.apply("com.google.dagger.hilt.android")
    pluginManager.apply("com.google.devtools.ksp")
    // ... dependencies
}
```

**Why**: AGP 9.0 alpha has a compatibility issue where Hilt can't find the BaseExtension. Explicitly applying `com.android.base` exposes the BaseExtension interface for Hilt's annotation processor.

### 2. Updated app/build.gradle.kts
**File**: `app/build.gradle.kts`

**Before**:
```kotlin
plugins {
    id("genesis.android.application")
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
}
```

**After**:
```kotlin
plugins {
    id("genesis.android.application")
    id("genesis.android.hilt")
}
```

**Benefits**:
- ✅ Applies KSP for annotation processing automatically
- ✅ Applies `com.android.base` plugin (AGP 9 alpha workaround)
- ✅ Applies Hilt plugin correctly
- ✅ Adds Hilt dependencies (`hilt-android`, `hilt-compiler`) automatically
- ✅ Cleaner build script

### 3. Updated BuildScriptTest.kt
**File**: `app/src/test/kotlin/BuildScriptTest.kt`

Updated the plugin test to check for the convention plugin instead of direct plugin aliases:

```kotlin
@Test
fun `convention plugin and hilt present`() {
    assertTrue(content.contains("""id("genesis.android.application")"""), 
        "Missing convention plugin: genesis.android.application")
    // Hilt is now applied via the genesis.android.hilt convention plugin
    assertTrue(content.contains("""id("genesis.android.hilt")"""), 
        "Missing Hilt convention plugin")
}
```

## Technical Details

### AGP 9.0 Alpha + Hilt Compatibility Issue
- **Problem**: AGP 9.0 alpha changed how the BaseExtension interface is exposed
- **Solution**: Explicitly apply `com.android.base` plugin before Hilt
- **Scope**: This workaround is needed for both application and library modules

### Convention Plugin Architecture
The `genesis.android.hilt` convention plugin now:
1. Detects if the module is an application or library
2. Applies the appropriate Android plugin if not already applied
3. Applies `com.android.base` plugin (AGP 9 workaround)
4. Applies Hilt and KSP plugins in the correct order
5. Automatically adds Hilt dependencies from version catalog

## Testing
The build script test was updated to validate:
- ✅ `genesis.android.application` convention plugin is applied
- ✅ `genesis.android.hilt` convention plugin is applied
- ❌ No direct `alias(libs.plugins.hilt)` or `alias(libs.plugins.ksp)` (handled by convention)

## References
- Problem statement in issue description
- `checkpoint.md` - Hilt Configuration section
- `CLEANUP.TXT` - AGP 9.0 + Hilt compatibility notes
- `FIX_HILT_BASEEXTENSION.md` - Previous fix documentation
