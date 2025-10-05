// ==== GENESIS PROTOCOL - FEATURE MODULE ====
// Primary feature module using convention plugins

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

plugins {
    id("com.android.library")
    id("com.android.base")
    // Hilt MUST be applied explicitly for annotation processing
    alias(libs.plugins.ksp)
    alias(libs.plugins.compose.compiler)
}

// Add modern documentation task that doesn't rely on deprecated plugins
tasks.register("generateApiDocs") {
    group = "documentation"
    description = "Generates API documentation without relying on deprecated plugins"

    doLast {
        logger.lifecycle("🔍 Generating API documentation for feature-module")
        logger.lifecycle("📂 Source directories:")
        logger.lifecycle("   - ${projectDir.resolve("src/main/kotlin")}")
        logger.lifecycle("   - ${projectDir.resolve("src/main/java")}")

        // Using layout.buildDirectory instead of deprecated buildDir property
        val docsDir = layout.buildDirectory.dir("docs/api").get().asFile
        docsDir.mkdirs()

        val indexFile = docsDir.resolve("index.html")

        // Using properly formatted date with DateTimeFormatter
        val currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))

        indexFile.writeText("""
            <!DOCTYPE html>
            <html>
            <head>
                <title>Feature Module API Documentation</title>
                <style>
                    body { font-family: Arial, sans-serif; margin: 20px; }
                    h1 { color: #4285f4; }
                </style>
            </head>
            <body>
                <h1>Feature Module API Documentation</h1>
                <p>Generated on ${currentTime}</p>
                <p>JDK Version: 24</p>
                <h2>Module Overview</h2>
                <p>Primary feature module for A.U.R.A.K.A.I. system functionality.</p>
            </body>
            </html>
        """.trimIndent())

        logger.lifecycle("✅ Documentation generated at: ${indexFile.absolutePath}")
    }
}

android {
    namespace = "dev.aurakai.auraframefx.featuremodule"
    compileSdk = 36

    defaultConfig {
        minSdk = 34
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

    buildFeatures {
        compose = true
    }

    packaging {
        resources {
            excludes += "META-INF/LICENSE.md"
        }
    }
}

dependencies {
    api(project(":core-module"))
    implementation(libs.bundles.androidx.core)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose.ui)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(libs.bundles.coroutines)
    implementation(libs.bundles.network)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)
    implementation(platform(libs.firebase.bom))
    implementation(libs.bundles.firebase)
    implementation(libs.timber)
    implementation(libs.coil.compose)
    implementation(fileTree("../Libs") { include("*.jar") })
    testImplementation(libs.bundles.testing.unit)
    testImplementation(libs.mockk.android)
    androidTestImplementation(libs.mockk.android)
    testImplementation(libs.hilt.android.testing)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.hilt.android.testing)
    debugImplementation(libs.leakcanary.android)
    debugImplementation(libs.androidx.compose.ui.tooling)
    implementation(kotlin("stdlib-jdk8"))
}
tasks.register("featureStatus") {
    group = "aegenesis"
    doLast { println("🚀 FEATURE MODULE - ${android.namespace} - Ready (Java 24)!") }
}