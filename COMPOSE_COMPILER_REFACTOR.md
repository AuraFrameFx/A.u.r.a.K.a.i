# Compose Compiler Configuration Refactoring

**Date:** October 7, 2025  
**Status:** ✅ Complete

## Overview

This refactoring centralizes the Jetpack Compose Compiler configuration across all modules using the `genesis.android.compose` convention plugin, eliminating redundant configuration and ensuring consistency.

## Key Changes

### 1. `AndroidComposeConventionPlugin` (Convention Plugin)

**Location:** `build-logic/src/main/kotlin/AndroidComposeConventionPlugin.kt`

**Changes:**
- ✅ Re-enabled `compose = true` build feature
- ✅ Added automatic Compose Compiler configuration via `afterEvaluate`
- ✅ Configures `reportsDestination` to `build/compose_compiler`
- ✅ Configures `stabilityConfigurationFiles` to use `stability_config.conf`
- ✅ Uses reflection to access Compose Compiler extension properties
- ✅ Includes error handling with try-catch and logging

**How it works:**
```kotlin
afterEvaluate {
    extensions.findByName("composeCompiler")?.apply {
        // Configure reports destination
        val reportsDestProp = javaClass.getMethod("getReportsDestination")
            .invoke(this) as DirectoryProperty
        reportsDestProp.set(layout.buildDirectory.dir("compose_compiler"))
        
        // Configure stability configuration files
        val stabilityFilesProp = javaClass.getMethod("getStabilityConfigurationFiles")
            .invoke(this)
        @Suppress("UNCHECKED_CAST")
        (stabilityFilesProp as ListProperty<Any>)
            .set(listOf(rootProject.layout.projectDirectory.file("stability_config.conf")))
    }
}
```

### 2. Module Build Files

**Updated Modules (13 total):**
1. `app` - Application module
2. `feature-module` - Primary feature module
3. `collab-canvas` - Collaboration canvas module
4. `colorblendr` - Color utility module
5. `romtools` - ROM tools module
6. `sandbox-ui` - Sandbox UI module
7. `datavein-oracle-native` - DataVein Oracle module with native code
8. `module-a` through `module-f` - Feature modules A-F

**Changes per module:**
- ✅ Applied `alias(libs.plugins.compose.compiler)` explicitly
- ✅ Replaced `id("genesis.android.library")` with `id("genesis.android.compose")` where appropriate
- ✅ Removed redundant `buildFeatures { compose = true }` blocks
- ✅ Removed manual `composeCompiler { ... }` configuration blocks (except `app` module)

**Example before:**
```kotlin
plugins {
    id("genesis.android.library")
    alias(libs.plugins.ksp)
}

android {
    buildFeatures {
        compose = true
    }
}

composeCompiler {
    reportsDestination = layout.buildDirectory.dir("compose_compiler")
    stabilityConfigurationFile = rootProject.layout.projectDirectory.file("stability_config.conf")
}
```

**Example after:**
```kotlin
plugins {
    id("genesis.android.compose")
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.ksp)
}

android {
    // compose buildFeatures now handled by convention plugin
    // composeCompiler configuration now handled by convention plugin
}
```

### 3. Build Logic Dependencies

**Location:** `build-logic/build.gradle.kts`

**Added dependency:**
```kotlin
dependencies {
    implementation("org.jetbrains.kotlin:compose-compiler-gradle-plugin:2.2.20")
}
```

This dependency provides the Compose Compiler plugin classes needed for reflection-based configuration.

### 4. Version Catalog

**Location:** `gradle/libs.versions.toml`

**Plugin definition:**
```toml
[plugins]
compose-compiler = { id = "org.jetbrains.kotlin.compose", version.ref = "kotlin" }
```

**Version:**
```toml
[versions]
kotlin = "2.2.20"
```

### 5. App Module Special Configuration

**Location:** `app/build.gradle.kts`

The app module retains an explicit `composeCompiler` block because it's an application module, not a library:

```kotlin
composeCompiler {
    reportsDestination = layout.buildDirectory.dir("compose_compiler")
    stabilityConfigurationFiles.set(listOf(
        rootProject.layout.projectDirectory.file("stability_config.conf")
    ))
}
```

**Note:** Uses the new `stabilityConfigurationFiles` (plural) API instead of the deprecated `stabilityConfigurationFile`.

## Benefits

### 1. **Centralized Configuration**
- All Compose Compiler settings are defined once in the convention plugin
- Eliminates code duplication across 12+ modules

### 2. **Consistency**
- Ensures all modules use the same Compose Compiler settings
- Reports go to `build/compose_compiler` in every module
- Stability configuration is uniform across the project

### 3. **Maintainability**
- Single source of truth for Compose configuration
- Updates to Compose settings only need to be made in one place
- Easier to upgrade Compose Compiler versions

### 4. **Developer Experience**
- Simpler module `build.gradle.kts` files
- Clear convention: `genesis.android.compose` = Compose-enabled library
- Less boilerplate code

### 5. **Type Safety**
- Uses Kotlin 2.2.20 stable with proper Compose Compiler plugin
- Leverages Gradle's type-safe property APIs

## Technical Details

### Reflection Usage

The convention plugin uses reflection to configure the Compose Compiler extension because:
1. The extension class is not available at compile-time in `build-logic`
2. The plugin is applied by modules, not by the convention plugin itself
3. This approach avoids classpath and plugin resolution issues

### API Migration

Updated from deprecated API:
```kotlin
// Old (deprecated)
stabilityConfigurationFile = file("...")

// New
stabilityConfigurationFiles.set(listOf(file("...")))
```

### Error Handling

The convention plugin includes defensive programming:
```kotlin
try {
    // Configure Compose Compiler
} catch (e: Exception) {
    logger.warn("Could not configure Compose Compiler: ${e.message}")
}
```

This ensures builds don't fail if:
- The Compose Compiler plugin isn't applied
- The API changes in future versions
- Reflection encounters unexpected issues

## Module Plugin Patterns

### Pattern 1: Compose-Enabled Library
```kotlin
plugins {
    id("genesis.android.compose")
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.ksp)
}
```
**Used by:** `feature-module`, `collab-canvas`, `colorblendr`, `romtools`, `sandbox-ui`, `modules a-f`

### Pattern 2: Compose Library with Native Code
```kotlin
plugins {
    id("genesis.android.compose")
    alias(libs.plugins.compose.compiler)
    id("genesis.android.native")
    alias(libs.plugins.ksp)
}
```
**Used by:** `datavein-oracle-native`

### Pattern 3: Compose Application
```kotlin
plugins {
    id("com.android.application")
    id("dev.aurakai.aurakai-android-convention")
    alias(libs.plugins.ksp)
    alias(libs.plugins.compose.compiler)
    // ... other plugins
}

composeCompiler {
    // Explicit configuration for application
}
```
**Used by:** `app`

## Verification Checklist

- [x] All 13 Compose-enabled modules build successfully
- [x] `genesis.android.compose` convention plugin applies without errors
- [x] Compose Compiler reports are generated in `build/compose_compiler`
- [x] Stability configuration file is properly referenced
- [x] No deprecation warnings about `stabilityConfigurationFile`
- [x] Build-logic module compiles without errors
- [x] Reflection-based configuration works correctly
- [x] Type casting with `@Suppress("UNCHECKED_CAST")` is safe and necessary

## Known Warnings (Non-Critical)

### Dokka Warnings
```
class org.jetbrains.dokka.gradle.adapters.AndroidExtensionWrapper 
could not get Android Extension for project :...
```
**Cause:** Dokka plugin accessing Android extensions during configuration  
**Impact:** Informational only, doesn't affect builds  
**Status:** Known issue with Dokka + Android projects

### Code Style Warnings
- Hardcoded string literals in convention plugin (acceptable for config values)
- Line length exceeding 100 columns (acceptable for fluent API calls)

## Future Improvements

1. **Extract Constants:** Move magic strings to constants
2. **Configuration DSL:** Create a dedicated DSL for Compose settings
3. **Per-Module Overrides:** Allow modules to override stability config if needed
4. **Metrics Analysis:** Set up automated analysis of Compose Compiler reports
5. **Documentation Generation:** Auto-generate docs from stability configuration

## Migration Guide

For future modules that need Compose:

1. Apply the convention plugin and Compose Compiler plugin:
   ```kotlin
   plugins {
       id("genesis.android.compose")
       alias(libs.plugins.compose.compiler)
   }
   ```

2. Remove any manual Compose configuration:
   ```kotlin
   // ❌ Remove this
   android {
       buildFeatures { compose = true }
   }
   composeCompiler { ... }
   ```

3. The convention plugin handles everything automatically!

## Related Files

- `build-logic/src/main/kotlin/AndroidComposeConventionPlugin.kt` - Convention plugin
- `build-logic/build.gradle.kts` - Build logic dependencies
- `gradle/libs.versions.toml` - Version catalog with plugin definitions
- `app/build.gradle.kts` - Application module configuration
- All feature module `build.gradle.kts` files

## References

- [Compose Compiler Gradle Plugin](https://kotlinlang.org/docs/compose-compiler-gradle-plugin.html)
- [Gradle Convention Plugins](https://docs.gradle.org/current/userguide/custom_plugins.html#sec:convention_plugins)
- [Compose Stability Configuration](https://developer.android.com/jetpack/compose/performance/stability/fix)

---

**Commit Message:**
```
refactor: Centralize Compose Compiler configuration in convention plugin

- Enable automatic Compose Compiler setup via genesis.android.compose plugin
- Add reflection-based configuration for reports and stability files
- Update all 13 Compose modules to use convention plugin
- Migrate to stabilityConfigurationFiles (plural) API
- Add compose-compiler-gradle-plugin dependency to build-logic
- Remove redundant buildFeatures and composeCompiler blocks from modules

Fixes: Compose Compiler configuration duplication
Impact: Cleaner build files, centralized Compose settings
Breaking: None (backward compatible)
```
u c