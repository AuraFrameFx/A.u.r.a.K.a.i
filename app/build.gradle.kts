plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.ksp)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.google.services)
    alias(libs.plugins.firebase.crashlytics)
    id("org.openapi.generator") version "7.15.0"
}

openApiGenerate {
    generatorName = "kotlin"
    inputSpec = "$rootDir/app/api/unified-aegenesis-api.yml".replace("\\", "/")
    outputDir = "$rootDir/app/src/main/kotlin"
    apiPackage = "dev.aurakai.auraframefx.api"
    modelPackage = "dev.aurakai.auraframefx.model"
    invokerPackage = "dev.aurakai.auraframefx.invoker"
    configOptions = mapOf(
        "library" to "jvm-retrofit2",
        "dateLibrary" to "java8",
    )
}

android {
    namespace = "dev.aurakai.auraframefx"
    compileSdk = 34
    defaultConfig {
        applicationId = "dev.aurakai.auraframefx"
        minSdk = 33
        multiDexEnabled = true
        targetSdkPreview = "CANARY"
    }
    buildFeatures {
        buildConfig = true
        resValues = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_24
        targetCompatibility = JavaVersion.VERSION_24
    }
    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(24))
        }
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    compilerOptions {

        freeCompilerArgs.addAll(
            "-Xjvm-default=all",
            "-Xopt-in=kotlin.RequiresOptIn",
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
    implementation(project(":module-a"))
    implementation(project(":module-b"))
    implementation(project(":module-c"))
    implementation(project(":module-d"))
    implementation(project(":module-e"))
    implementation(project(":module-f"))
    implementation(project(":core-module"))
    implementation(project(":screenshot-tests"))
    implementation(project(":app"))
    implementation(project(":datavein-oracle-native"))

    // Firebase (using the BOM and bundle)


    // Kotlin Core
    implementation(libs.kotlin.reflect)
    implementation(libs.kotlin.stdlib.jdk8)

    implementation(libs.hilt.android)
    implementation(libs.hilt.work)
    ksp(libs.hilt.compiler)

    // Compose (using the BOM and bundle)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose.ui)
    debugImplementation(libs.bundles.compose.debug)
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material3:material3-window-size-class")
    implementation("androidx.activity:activity-compose")
    implementation(libs.androidx.navigation.compose)
    implementation("androidx.hilt:hilt-navigation-compose")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose")
    implementation("androidx.compose.material:material-icons-extended")

    // Utilities & Networking
    implementation(libs.timber)
    implementation(libs.coil.compose)
    implementation(libs.retrofit.core)
    implementation("com.google.android.material:material:1.13.0")

    // Testing
    testImplementation(libs.bundles.testing.unit)
    androidTestImplementation(libs.bundles.testing.android)
    androidTestImplementation(libs.hilt.android.testing)
    debugImplementation(libs.leakcanary.android)
    // Move test libs off implementation (they bloat APK):
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)
}