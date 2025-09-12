// Apply plugins - versions managed by root project
plugins {
    // Replaced explicit versions with catalog aliases for consistency
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "dev.aurakai.auraframefx.core"
    compileSdk = 36

    defaultConfig {
    }

    buildFeatures {
        // Only enable what's required; remove renderScript/shaders
        // aidl = true  // Disabled temporarily to fix circular dependency
        buildConfig = true
    resValues = true}

    // Add Java toolchain 24
    java { toolchain { languageVersion.set(JavaLanguageVersion.of(24)) } }
    kotlin {
        jvmToolchain(24)
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_24)
            freeCompilerArgs.addAll(
                "-Xcontext-receivers"
            )
            progressiveMode.set(true)
        }
    }

    // OpenAPI generated sources directory removed
}

// OpenAPI generation task removed - no longer needed

dependencies {
    // Desugaring

    // Xposed API (using correct path)
    compileOnly(files("../Libs/api-82.jar"))
    compileOnly(files("../Libs/api-82-sources.jar"))

    // AndroidX & Compose
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(platform(libs.androidx.compose.bom))

    // Room
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)

    // Modules
    // Remove circular dependency on core-module
    // implementation(project(":core-module"))
    implementation(project(":feature-module"))
    implementation(project(":oracle-drive-integration"))
    implementation(project(":romtools"))
    implementation(project(":secure-comm"))
    implementation(project(":collab-canvas"))
    implementation(project(":colorblendr"))
    implementation(project(":sandbox-ui"))
    implementation(project(":datavein-oracle-native"))

    // DI (Hilt)
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    testImplementation(libs.hilt.android.testing)
    androidTestImplementation(libs.hilt.android.testing)
    kspTest(libs.hilt.compiler)
    kspAndroidTest(libs.hilt.compiler)


    implementation(libs.bundles.coroutines)
    implementation(libs.bundles.network)

    // Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.bundles.firebase)

    // Utilities
    implementation(libs.timber)
    implementation(libs.coil.compose)

    // Testing
    testImplementation(libs.bundles.testing.unit)
}
