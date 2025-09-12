// ==== GENESIS PROTOCOL - MAIN APPLICATION ====
// This build script now uses the custom convention plugins for a cleaner setup.

plugins {
    id("genesis.android.application")
    id("org.jetbrains.kotlin.plugin.compose") version "2.2.20-RC"
    id("com.google.dagger.hilt.android") version "2.51.1"
    id("com.google.devtools.ksp") version "2.2.20-2.0.3"
}

android {
    namespace = "dev.aurakai.auraframefx" // Using the namespace from your convention plugin

    defaultConfig {
        // App-specific configurations that aren't in the convention plugin
        vectorDrawables.useSupportLibrary = true
    }

    buildFeatures {
        // Enable features not covered in the library convention plugin
        aidl = true
    }

    testOptions {
        unitTests.all {
            it.useJUnitPlatform()
        }
    }
}

dependencies {
    // ===== MODULE DEPENDENCIES =====
    implementation(project(":core-module"))
    implementation(project(":feature-module"))
    implementation(project(":oracle-drive-integration"))
    implementation(project(":romtools"))
    implementation(project(":secure-comm"))
    implementation(project(":collab-canvas"))
    implementation(project(":colorblendr"))
    implementation(project(":sandbox-ui"))
    implementation(project(":datavein-oracle-native"))

    // ===== ANDROIDX & COMPOSE =====
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose.ui)
    debugImplementation(libs.bundles.compose.debug)


    // ===== LIFECYCLE =====
    implementation(libs.bundles.lifecycle)

    // ===== DATABASE - ROOM =====
    implementation(libs.bundles.room)
    ksp(libs.bundles.room.compiler)

    // ===== KOTLIN & COROUTINES =====
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.bundles.coroutines)

    // ===== NETWORKING =====
    implementation(libs.bundles.network)

    // ===== FIREBASE =====
    // By implementing the BOM, we can specify Firebase SDKs without versions
    implementation(platform(libs.firebase.bom))
    // This bundle includes Analytics, Crashlytics, Performance, Auth, Firestore, Messaging, and Config
    implementation(libs.bundles.firebase)

    // Alternative: Use specific Firebase bundles for modular approach
    // implementation(libs.bundles.firebase.core)     // Analytics, Crashlytics, Performance only
    // implementation(libs.bundles.firebase.auth)     // Authentication
    // implementation(libs.bundles.firebase.database) // Firestore, Realtime Database, Storage
    // implementation(libs.bundles.firebase.messaging) // FCM, Remote Config

    // ===== HILT DEPENDENCY INJECTION =====
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    // ===== UTILITIES =====
    implementation(libs.timber)
    implementation(libs.coil.compose)
    
    // ===== CORE LIBRARY DESUGARING =====
    coreLibraryDesugaring(libs.desugar.jdk.libs)
    
    // ===== XPOSED/LSPosed Integration =====
    compileOnly(files("../Libs/api-82.jar"))
    compileOnly(files("../Libs/api-82-sources.jar"))

    // --- TESTING ---
    testImplementation(libs.bundles.testing.unit)
    androidTestImplementation(libs.bundles.testing.android)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.hilt.android.testing) // For Hilt in Android tests

    // --- DEBUGGING ---
    debugImplementation(libs.leakcanary.android)
}
