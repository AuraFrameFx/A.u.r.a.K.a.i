// ==== GENESIS PROTOCOL - SANDBOX UI ====

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
}

android {
    namespace = "dev.aurakai.auraframefx.sandboxui"
    compileSdk = 36
    defaultConfig { minSdk = 34 }
    java { toolchain { languageVersion.set(JavaLanguageVersion.of(24)) } }
    kotlin { jvmToolchain(24); compilerOptions { jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_24) } }
    // buildFeatures { compose = true } // Disabled due to Kotlin 2.2.20-RC compatibility
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose.ui)
}
