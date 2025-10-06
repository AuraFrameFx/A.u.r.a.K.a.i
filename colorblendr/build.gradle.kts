// ==== GENESIS PROTOCOL - COLORBLENDR MODULE ====
// Color utility and theming module

plugins {
    id("genesis.android.library")
    alias(libs.plugins.ksp)
    alias(libs.plugins.compose.compiler)
    // Note: Hilt plugin removed to avoid Android BaseExtension issues, using manual dependencies instead
}

android {
    namespace = "dev.aurakai.auraframefx.colorblendr"
    
    defaultConfig {
        minSdk = 34
    }
    
    buildFeatures {
        buildConfig = true
        compose = true
    }
}



java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(24))
    }
}

dependencies {
    val composeBom = platform("androidx.compose:compose-bom:2025.09.01")
    implementation(composeBom)
    androidTestImplementation(composeBom)

    // Material Design 3
    implementation("androidx.compose.material3:material3")
    // Foundation components
    implementation("androidx.compose.foundation:foundation")
    // Main UI toolkit APIs
    implementation("androidx.compose.ui:ui")
    // Android Studio Preview support
    implementation("androidx.compose.ui:ui-tooling-preview")
    debugImplementation("androidx.compose.ui:ui-tooling")
    // UI Tests
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.9.2")
    // Optional - Add window size utils
    implementation("androidx.compose.material3.adaptive:adaptive")
    // Optional - Integration with activities
    implementation("androidx.activity:activity-compose:1.11.0")
    // Optional - Integration with ViewModels
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.9.4")
    // Optional - Integration with LiveData
    implementation("androidx.compose.runtime:runtime-livedata:1.9.2")
    // Optional - Integration with RxJava
    implementation("androidx.compose.runtime:runtime-rxjava2:1.9.2")

    // Core
    implementation(project(":core-module"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    
    // Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose.ui)
    debugImplementation(libs.bundles.compose.debug)
    
    // Lifecycle
    implementation(libs.bundles.lifecycle)
    
    // Hilt
    implementation(libs.hilt.android)
    add("ksp", libs.hilt.compiler)

    // Utilities
    implementation(libs.timber)
    
    // Testing
    testImplementation(libs.junit4)
    testImplementation(libs.mockk)
    androidTestImplementation(libs.bundles.testing.android)
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:2.2.20")
}
