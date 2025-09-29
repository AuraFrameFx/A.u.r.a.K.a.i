// ==== GENESIS PROTOCOL - COLLAB CANVAS MODULE ====
// Collaborative canvas module for real-time drawing

plugins {
    id("plugins.android-base")
    alias(libs.plugins.ksp)
    id("org.jetbrains.kotlin.plugin.compose")
    `maven-publish`
}

android {
    namespace = "dev.aurakai.auraframefx.collabcanvas"
    defaultConfig {
        minSdk = 33
    }

    buildFeatures {
        buildConfig = true
        resValues = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_25
        targetCompatibility = JavaVersion.VERSION_25
    }
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_24)
            freeCompilerArgs.addAll(
                "-Xjvm-default=all",
                "-Xopt-in=kotlin.RequiresOptIn",
            )
        }
    }
}

dependencies {
    // Module dependencies
    implementation(project(":core-module"))
    implementation(platform(libs.firebase.bom))
    implementation(libs.bundles.firebase)
    implementation(libs.kotlin.stdlib.jdk8)

    // Jetpack Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose.ui)
    debugImplementation(libs.bundles.compose.debug)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.material.icons.extended)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.compose.ui.tooling.preview)

    // Gson, OkHttp, Timber
    implementation(libs.gson)
    implementation(libs.retrofit.core)
    implementation(libs.okhttp.core)
    implementation(libs.okhttp.logging.interceptor)
    implementation(libs.timber)

    // Hilt
    implementation(libs.hilt.android)
    implementation(libs.androidx.core.ktx)
    ksp(libs.hilt.compiler)

    testImplementation(libs.bundles.testing.unit)
    androidTestImplementation(libs.bundles.testing.android) {
        exclude(group = "androidx.test", module = "monitor") // Exclude to avoid conflicts
    }
    androidTestImplementation(libs.hilt.android.testing)
    kspTest(libs.hilt.compiler) // Ensure Hilt annotation processor is present for tests

    // Desugaring
}
