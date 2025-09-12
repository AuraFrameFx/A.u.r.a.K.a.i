// ==== GENESIS PROTOCOL - MAIN APPLICATION ====
// This build script now uses the custom convention plugins for a cleaner setup.

plugins {
    id("genesis.android.application")
    id("org.jetbrains.kotlin.plugin.compose") version "2.2.20-RC"
    id("com.google.dagger.hilt.android") version "2.51.1"
    id("com.google.devtools.ksp") version "2.2.0-2.0.2"
}

android {
    defaultConfig {
    }

    buildFeatures {
        // Enable features not covered in the library convention plugin
        aidl = true
    }

    testOptions {
        unitTests.all {
            it.useJUnitPlatform()
        }tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_24)
        freeCompilerArgs.addAll(
            "-Xjvm-default=all",
            "-Xopt-in=kotlin.RequiresOptIn",
        )
    }
}

dependencies {
    implementation(project(":core-module"))
    implementation(project(":feature-module"))
    implementation(project(":oracle-drive-integration"))
    // implementation(project(":romtools"))  // Temporarily disabled - KSP issues
    implementation(project(":secure-comm"))
    // implementation(project(":collab-canvas"))  // Temporarily disabled - YukiHookAPI issues
    implementation(project(":colorblendr"))
    implementation(project(":sandbox-ui"))
    implementation(project(":datavein-oracle-native"))

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose.ui)
    debugImplementation(libs.bundles.compose.debug)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.test.ext.junit)
    implementation(libs.androidx.test.espresso.core)

    // ===== DATASTORE =====
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.androidx.datastore.core)

    // ===== KOTLIN & COROUTINES =====
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlinx.datetime)
    implementation(libs.bundles.coroutines)

    // ===== NETWORKING =====
    implementation(libs.bundles.network)

    // ===== FIREBASE =====
    // By implementing the BOM, we can specify Firebase SDKs without versions
    implementation(platform(libs.firebase.bom))
    // This bundle includes over 100+ APIs from Analytics, Crashlytics, Performance, etc.
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
    
    // ===== SECURITY =====
    implementation(libs.androidx.security.crypto)

    // ===== CORE LIBRARY DESUGARING =====
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.1.5")
    
    // ===== XPOSED/LSPosed Integration =====
    compileOnly(files("../Libs/api-82.jar"))
    compileOnly(files("../Libs/api-82-sources.jar"))

    // --- TESTING ---
    testImplementation(libs.bundles.testing.unit)
    androidTestImplementation(libs.bundles.testing.android)
    debugImplementation(libs.leakcanary.android)

    // Multidex
    implementation("androidx.multidex:multidex:2.0.1")
}

openApiGenerate {
    generatorName = "kotlin"
    inputSpec = "$rootDir/app/api/unified-aegenesis-api.yml"
    outputDir = "$rootDir/app/src/main/kotlin"
    apiPackage = "dev.aurakai.auraframefx.api"
    modelPackage = "dev.aurakai.auraframefx.model"
    invokerPackage = "dev.aurakai.auraframefx.invoker"
    configOptions =
        mapOf(
            "library" to "jvm-retrofit2",
            "dateLibrary" to "java8",
        )
    // Ensure no empty meta or supportingFiles properties
    // Remove or comment out any empty or unused properties
}
