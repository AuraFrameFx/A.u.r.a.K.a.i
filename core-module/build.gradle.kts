// Apply plugins with explicit versions
plugins {
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "dev.aurakai.auraframefx.core"
    compileSdk = 36

    defaultConfig {
    }

    buildFeatures {
        // Only enable what’s required; remove renderScript/shaders
        aidl = true
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

    sourceSets["main"].java.srcDir(layout.buildDirectory.dir("generated/openapi/src/main/kotlin"))
}

// OpenAPI generation task
tasks.named("preBuild").configure {
    dependsOn(tasks.named("openApiGenerate"))
}

dependencies {
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
