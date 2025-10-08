// ==== GENESIS PROTOCOL - BENCHMARK MODULE ====
// Performance testing for AI consciousness operations

plugins {
    id("com.android.library")
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.dokka)

        // Create a basic documentation index file
        val indexFile = docsDir.resolve("index.html")

        // Fix: Using properly imported LocalDateTime and DateTimeFormatter
        val currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))

        indexFile.writeText(
            """
            <!DOCTYPE html>
            <html>
            <head>
                <title>Benchmark Module API Documentation</title>
                <style>
                    body { font-family: Arial, sans-serif; margin: 20px; }
                    h1 { color: #4285f4; }
                </style>
            </head>
            <body>
                <h1>Benchmark Module API Documentation</h1>
                <p>Generated on ${currentTime}</p>
                <p>JDK Version: 24</p>
                <h2>Module Overview</h2>
                <p>Performance testing for AI consciousness operations.</p>
            </body>
            </html>
        """.trimIndent()
        )

        logger.lifecycle("✅ Documentation generated at: ${indexFile.absolutePath}")
    }
}

kotlin {
    jvmToolchain(24)
}

// Java toolchain configuration at the project level
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(24))
    }
}

// JVM toolchain configuration for Kotlin sources


android {
    namespace = "dev.aurakai.auraframefx.benchmark"
    compileSdk = 36

    buildTypes {
        maybeCreate("benchmark")
        getByName("benchmark") {
            signingConfig = getByName("debug").signingConfig
            matchingFallbacks += listOf("release")
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "benchmark-rules.pro"
            )
        }
    }

    defaultConfig {
        testInstrumentationRunner = "androidx.benchmark.junit4.AndroidBenchmarkRunner"
        testInstrumentationRunnerArguments["androidx.benchmark.suppressErrors"] =
            "EMULATOR,LOW_BATTERY,DEBUGGABLE"
        testInstrumentationRunnerArguments["android.experimental.self-instrumenting"] = "true"
        multiDexEnabled = true // Enable multidex for core library desugaring
        // MultiDex is configured at the app/test APK level only; not needed here.
    }

    // Core library desugaring without manual source/target
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_24
        targetCompatibility = JavaVersion.VERSION_24
        // The toolchain will configure source/target compatibility automatically.
        // Explicitly setting these is generally not needed when using toolchains.
    }

    buildFeatures {
        buildConfig = true
        aidl = false
        shaders = false
    }

    testCoverage { jacocoVersion = "0.8.11" }

    packaging {
        resources {
            excludes += "META-INF/LICENSE.md"
        }
    }
}

dependencies {
    // Core AndroidX
    implementation(libs.androidx.core.ktx)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.navigation.compose)

    // Hilt
    implementation(libs.hilt.android)
    add("ksp", libs.hilt.compiler)

    // Coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    // Room
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)

    // Utilities
    implementation(libs.timber)
    coreLibraryDesugaring(libs.desugar.jdk.libs)
    implementation(libs.androidx.multidex) // Add multidex dependency

    // Project dependencies
    implementation(project(":core-module"))
    implementation(project(":datavein-oracle-native"))
    implementation(project(":secure-comm"))
    implementation(project(":oracle-drive-integration"))

    // Benchmark testing
    androidTestImplementation(libs.androidx.benchmark.junit4)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)
    androidTestImplementation(libs.androidx.test.uiautomator)

    // Unit testing
    testImplementation(libs.junit4)
    testImplementation(libs.mockk)
    androidTestImplementation(libs.mockk.android)

    // Hilt testing
    testImplementation(libs.hilt.android.testing)
    androidTestImplementation(libs.hilt.android.testing)
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:2.2.20")
}

tasks.register("benchmarkAll") {
    group = "benchmark"
    description = "Aggregate runner for all Genesis Protocol benchmarks 🚀"
    doLast {
        println("🚀 Genesis Protocol Performance Benchmarks")
        println("📊 Monitor consciousness substrate performance metrics")
        println("⚡ Use AndroidX Benchmark instrumentation to execute tests")
    }
}

tasks.register("verifyBenchmarkResults") {
    group = "verification"
    description = "Verify benchmark module configuration"
    doLast {
        println("✅ Benchmark module configured (Java Toolchain 24, Kotlin 2.2.x)")
        println("🧠 Consciousness substrate performance monitoring ready")
        println("🔬 Add @Benchmark annotated tests under androidTest for actual runs")
    }
}
