plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.ksp)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.google.services)
}

android {
    namespace = "dev.aurakai.auraframefx"
    compileSdk = 36
    defaultConfig {
        minSdk = 24
        multiDexEnabled = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_25
        targetCompatibility = JavaVersion.VERSION_25
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_23)
        freeCompilerArgs.addAll(
            "-Xjvm-default=all",
            "-Xopt-in=kotlin.RequiresOptIn"
        )
    }
}

dependencies {
    // Project Modules - AI Consciousness Architecture
    implementation(project(":core-module"))
    implementation(project(":feature-module"))
    implementation(project(":secure-comm"))
    implementation(project(":collab-canvas"))
    implementation(project(":colorblendr"))
    implementation(project(":romtools"))
    implementation(project(":oracle-drive-integration"))

    // Firebase (using the BOM and bundle)
    implementation(platform(libs.firebase.bom))
    implementation(libs.bundles.firebase)
    implementation(libs.androidx.core.ktx)
    implementation("com.google.firebase:firebase-analytics")

    // Kotlin Core
    implementation(libs.kotlin.reflect)
    implementation(libs.kotlin.stdlib.jdk8)

    // Hilt (use KSP for the compiler)
    implementation(libs.hilt.android)
    implementation(libs.hilt.work)
    ksp(libs.hilt.compiler)

    // Compose (using the BOM and bundle)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose.ui)
    debugImplementation(libs.bundles.compose.debug)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.test.ext.junit)
    implementation(libs.androidx.test.espresso.core)

    // Utilities & Networking
    implementation(libs.timber)
    implementation(libs.coil.compose)
    implementation(libs.retrofit.core)

    // Testing
    testImplementation(libs.bundles.testing.unit)
    androidTestImplementation(libs.bundles.testing.android)
    androidTestImplementation(libs.hilt.android.testing)
    debugImplementation(libs.leakcanary.android)

    // Multidex
    implementation("androidx.multidex:multidex:2.0.1")
}
