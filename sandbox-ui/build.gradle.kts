// ==== GENESIS PROTOCOL - SANDBOX UI ====

plugins {
    id("plugins.android-base")
    alias(libs.plugins.ksp)
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    namespace = "dev.aurakai.auraframefx.sandboxui"
    defaultConfig {
        minSdk = 33
    }
    buildFeatures {
        buildConfig = true
        resValues = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_21)
    }
}

dependencies {
    implementation(project(":core-module"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.bundles.lifecycle)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose.ui)
    debugImplementation(libs.bundles.compose.debug)
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    testImplementation(libs.bundles.testing.unit)
    androidTestImplementation(libs.bundles.testing.android)
}
