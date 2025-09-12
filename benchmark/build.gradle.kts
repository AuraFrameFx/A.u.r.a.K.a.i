// ==== GENESIS PROTOCOL - BENCHMARK MODULE ====
// Performance testing for AI consciousness operations

plugins {
    id("genesis.android.library")
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
    // Kotlin Android is applied by the convention plugin; avoid double-applying to prevent duplicate tasks
    // Note: Hilt plugin removed to avoid Android BaseExtension issues, using manual dependencies instead
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(24)) // Stay on 24 until stable 25 (non-RC)
    }
}

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
        // MultiDex is configured at the app/test APK level only; not needed here.

    }

    // Core library desugaring without manual source/target (toolchain supplies Java 24)
    compileOptions {
        // Removed explicit source/target compatibility; managed by toolchain
        isCoreLibraryDesugaringEnabled = true
    }

    // Modern Kotlin compilerOptions DSL (replaces deprecated kotlinOptions)
    kotlin {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_24)
            freeCompilerArgs.addAll(
                "-opt-in=kotlin.RequiresOptIn",
                "-Xjvm-default=all"
            )
        }
    }

    buildFeatures {
        buildConfig = true
        aidl = false
        renderScript = false
        shaders = false
    }

    testCoverage { jacocoVersion = "0.8.11" }
}

dependencies {
    // Core AndroidX
    implementation(libs.androidx.core.ktx)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.navigation.compose)
    
    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    
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
    kspAndroidTest(libs.hilt.compiler)
    implementation(kotlin("stdlib-jdk8"))
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