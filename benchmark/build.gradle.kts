plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp") version "2.2.20-2.0.3"
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
        minSdk = 26
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
    implementation("androidx.core:core-ktx:1.15.0")
    implementation(platform("androidx.compose:compose-bom:2024.12.01"))
    implementation("androidx.activity:activity-compose:1.9.3")
    implementation("androidx.navigation:navigation-compose:2.8.5")
    
    // Hilt
    implementation("com.google.dagger:hilt-android:2.57.2")
    ksp("com.google.dagger:hilt-compiler:2.57.2")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.10.1")

    // Room
    implementation("androidx.room:room-runtime:2.7.0-alpha12")
    implementation("androidx.room:room-ktx:2.7.0-alpha12")
    ksp("androidx.room:room-compiler:2.7.0-alpha12")

    // Utilities
    implementation("com.jakewharton.timber:timber:5.0.1")
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.1.4")
    implementation("androidx.multidex:multidex:2.0.1")
    
    // Project dependencies
    implementation(project(":core-module"))
    implementation(project(":datavein-oracle-native"))
    implementation(project(":secure-comm"))
    implementation(project(":oracle-drive-integration"))

    // Benchmark testing
    androidTestImplementation("androidx.benchmark:benchmark-junit4:1.4.0")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    androidTestImplementation("androidx.test.uiautomator:uiautomator:2.3.0")

    // Unit testing
    testImplementation("junit:junit:4.13.2")
    testImplementation("io.mockk:mockk:1.13.14")
    androidTestImplementation("io.mockk:mockk-android:1.13.14")

    // Hilt testing
    testImplementation("com.google.dagger:hilt-android-testing:2.57.2")
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.57.2")
    kspAndroidTest("com.google.dagger:hilt-compiler:2.57.2")
}
