plugins {
    id("com.android.library")  // Convention plugin: applies Android, Kotlin, Hilt
    alias(libs.plugins.ksp)
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(24)) // Stay on 24 until stable 25 (non-RC)
    }
}

android {
    namespace = "dev.aurakai.auraframefx.benchmark"
    compileSdk = 36

    defaultConfig {
        minSdk = 34
        testInstrumentationRunner = "androidx.benchmark.junit4.AndroidBenchmarkRunner"
        multiDexEnabled = true
    }

    buildTypes {
        maybeCreate("benchmark")
        getByName("benchmark") {
            matchingFallbacks += listOf("release")
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "benchmark-rules.pro"
            )
        }
    }

    // Core library desugaring without manual source/target
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        // Toolchain will configure source/target compatibility automatically
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.navigation.compose)
    
    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    // Coroutines
    implementation(libs.bundles.coroutines)

    // Room
    implementation(libs.bundles.room)
    ksp(libs.androidx.room.compiler)

    // Utilities
    implementation(libs.timber)
    coreLibraryDesugaring(libs.desugar.jdk.libs)
    implementation(libs.androidx.multidex)
    
    // Project dependencies
    implementation(project(":core-module"))
    implementation(project(":datavein-oracle-native"))
    implementation(project(":secure-comm"))
    implementation(project(":oracle-drive-integration"))

    // Benchmark testing
    androidTestImplementation(libs.androidx.benchmark.junit4)
    androidTestImplementation(libs.bundles.testing.android)

    // Unit testing
    testImplementation(libs.junit4)
    testImplementation(libs.mockk)
    androidTestImplementation(libs.mockk.android)

    // Hilt testing
    testImplementation(libs.hilt.android.testing)
    androidTestImplementation(libs.hilt.android.testing)
    kspAndroidTest(libs.hilt.compiler)
}
