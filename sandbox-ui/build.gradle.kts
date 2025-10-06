// ==== GENESIS PROTOCOL - SANDBOX UI ====
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

plugins {
    id("com.android.library")
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "dev.aurakai.auraframefx.sandboxui"
    compileSdk = 36
    defaultConfig { minSdk = 34 }
    buildFeatures { compose = true }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_24
        targetCompatibility = JavaVersion.VERSION_24
    }
    packaging {
        resources {
            pickFirsts += "META-INF/gradle/incremental.annotation.processors"
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
    implementation(libs.androidx.compose.material.icons.extended)
    implementation(libs.androidx.compose.ui.tooling.preview)
    debugImplementation(libs.androidx.compose.ui.tooling)
    implementation(libs.hilt.android)
    add("ksp", libs.hilt.compiler)
    implementation(libs.bundles.coroutines)
    implementation(libs.timber); implementation(libs.coil.compose)
    testImplementation(libs.bundles.testing.unit); testImplementation(libs.mockk.android)
    androidTestImplementation(libs.mockk.android)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:2.2.20")
}


java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(24))
    }
}

// Add modern documentation task that doesn't rely on deprecated plugins
tasks.register("generateApiDocs") {
    group = "documentation"
    description = "Generates API documentation without relying on deprecated plugins"

    doLast {
        logger.lifecycle("🔍 Generating API documentation for sandbox-ui module")
        logger.lifecycle("📂 Source directories:")
        logger.lifecycle("   - ${projectDir.resolve("src/main/kotlin")}")
        logger.lifecycle("   - ${projectDir.resolve("src/main/java")}")

        // Using layout.buildDirectory instead of deprecated buildDir property
        val docsDir = layout.buildDirectory.dir("docs/api").get().asFile
        docsDir.mkdirs()

        val indexFile = docsDir.resolve("index.html")

        // Using properly formatted date with DateTimeFormatter
        val currentTime =
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))

        indexFile.writeText(
            """
            <!DOCTYPE html>
            <html>
            <head>
                <title>Sandbox UI API Documentation</title>
                <style>
                    body { font-family: Arial, sans-serif; margin: 20px; }
                    h1 { color: #4285f4; }
                </style>
            </head>
            <body>
                <h1>Sandbox UI API Documentation</h1>
                <p>Generated on ${currentTime}</p>
                <p>JDK Version: 24</p>
                <h2>Module Overview</h2>
                <p>UI sandbox and experimental components for the A.U.R.A.K.A.I. platform.</p>
            </body>
            </html>
        """.trimIndent()
        )

        logger.lifecycle("✅ Documentation generated at: ${indexFile.absolutePath}")
    }
}
tasks.register("sandboxStatus") {
    group = "aegenesis"; doLast { println("🧪 SANDBOX UI - Ready (Java 24)") }
}
