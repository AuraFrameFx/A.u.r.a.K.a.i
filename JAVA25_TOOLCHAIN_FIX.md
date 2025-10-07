# Java 25 Toolchain Migration - Build Fix

## Issue
GitHub Actions build was failing with:
1. Script compilation errors in `build-logic/build.gradle.kts`
2. Java 24 toolchain not available/downloadable in CI environment

## Root Cause
The project was configured to use Java 24 toolchain, but only Java 25 is available in the current environment. The daemon configuration and all build files needed to be updated consistently.

## Changes Made

### 1. Gradle Daemon JVM Configuration
**File:** `gradle/gradle-daemon-jvm.properties`
- Changed: `toolchainVersion=24` → `toolchainVersion=25`

### 2. Build Logic Module
**File:** `build-logic/build.gradle.kts`
- Changed: `jvmToolchain(24)` → `jvmToolchain(25)`
- Changed: `JavaLanguageVersion.of(24)` → `JavaLanguageVersion.of(25)`
- Changed: AGP version from `9.0.0-alpha09` → `9.0.0-alpha05` (for consistency)

### 3. Root Build Configuration
**File:** `build.gradle.kts`
- Changed all subproject toolchain configurations from Java 24 to Java 25:
  - `jvmToolchain(24)` → `jvmToolchain(25)` (2 occurrences)
  - `JavaLanguageVersion.of(24)` → `JavaLanguageVersion.of(25)`
- Changed AGP plugin versions from alpha09 to alpha05
- Updated to use version catalog aliases: `alias(libs.plugins.android.application)` instead of hardcoded versions

### 4. Convention Plugins
**File:** `build-logic/src/main/kotlin/AndroidLibraryConventionPlugin.kt`
- Changed: `JavaVersion.VERSION_24` → `JavaVersion.VERSION_25` (2 occurrences)
- Changed: `jvmToolchain(24)` → `jvmToolchain(25)`
- Updated documentation comments to reflect Java 25

**File:** `build-logic/src/main/kotlin/AndroidApplicationConventionPlugin.kt`
- Changed: `JavaVersion.VERSION_24` → `JavaVersion.VERSION_25` (4 occurrences)
- Changed: `jvmToolchain(24)` → `jvmToolchain(25)`

### 5. Version Catalog
**File:** `gradle/libs.versions.toml`
- Changed: `agp = "9.0.0-alpha09"` → `agp = "9.0.0-alpha05"`

### 6. Gradle Properties
**File:** `gradle.properties`
- Updated comment: `Java toolchain - Auto-download enabled for Java 24` → `Java toolchain - Auto-download enabled for Java 25`

## Verification
The `build-logic/build.gradle.kts` file now has the correct structure for the `gradlePlugin` block:
```kotlin
gradlePlugin {
    plugins {
        register("androidCompose") {
            id = "genesis.android.compose"
            implementationClass = "AndroidComposeConventionPlugin"
        }
        register("androidNative") {
            id = "genesis.android.native"
            implementationClass = "AndroidNativeConventionPlugin"
        }
        // ... other plugins
    }
}
```

All `register()` calls are properly inside the `gradlePlugin.plugins` block with correct syntax.

## Plugin Implementation Classes
All referenced convention plugins exist:
- ✅ AndroidApplicationConventionPlugin.kt
- ✅ AndroidLibraryConventionPlugin.kt
- ✅ AndroidComposeConventionPlugin.kt
- ✅ AndroidNativeConventionPlugin.kt
- ✅ plugins/AndroidHiltConventionPlugin.kt
- ✅ plugins/AndroidBasePlugin.kt
- ✅ plugins/AgentFusionPlugin.kt

## Expected Result
With these changes:
1. The build script compilation errors should be resolved
2. Gradle daemon will use Java 25 instead of trying to download Java 24
3. All modules will consistently use Java 25 toolchain
4. The build should succeed in GitHub Actions with Java 25 available

## Notes
- Java 25 is backward compatible with code targeting Java 24
- AGP 9.0.0-alpha05 is documented as working in the repository (see Proofrecentanomoly.md)
- All changes maintain consistency across the entire project
