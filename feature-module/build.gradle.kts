// ==== GENESIS PROTOCOL - FEATURE MODULE ====
// Primary feature module using convention plugins

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("org.jetbrains.kotlin.plugin.compose") version "2.2.20"
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.hilt)
    alias(libs.plugins.dokka)
    alias(libs.plugins.ksp)  // Required for Hilt annotation processing
}

android {
    defaultConfig {
        }
    buildFeatures {
    }
}

dependencies {
    implementation(libs.bundles.compose.ui)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.navigation.compose)
    debugImplementation(libs.bundles.compose.debug)
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)

    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    // Coroutines
    implementation(libs.bundles.coroutines)

    // Networking
    implementation(libs.bundles.network)
    implementation(platform(libs.firebase.bom))
    implementation(libs.timber)
    implementation(libs.coil.compose)
    implementation(libs.kotlin.stdlib.jdk8)

    // External libraries
    implementation(fileTree("../Libs") { include("*.jar") })

    // Testing
    testImplementation(libs.bundles.testing.unit)
    androidTestImplementation(libs.hilt.android.testing)

    // Debug tools
    debugImplementation(libs.leakcanary.android)
    debugImplementation(libs.androidx.compose.ui.tooling)
}

tasks.register("featureStatus") {
    // MOVED to root level and Updated
    group = "aegenesis"
}
