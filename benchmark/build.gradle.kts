plugins {
    id("com.android.library")
    id("com.google.devtools.ksp") version "2.2.21-RC-2.0.4"
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
            matchingFallbacks += listOf("release")
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "benchmark-rules.pro"
            )
        }
    }

    // Core library desugaring without manual source/target (toolchain supplies Java 24)
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
    implementation("androidx.core:core-ktx:1.17.0")
    implementation(platform("androidx.compose:compose-bom:2025.09.01"))
    implementation("androidx.activity:activity-compose:1.11.0")
    implementation("androidx.navigation:navigation-compose:2.9.5")
    // Hilt
    implementation("com.google.dagger:hilt-android:2.51.1")
    ksp("com.google.dagger:hilt-compiler:2.51.1")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.9.0")

    // Room
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    ksp("androidx.room:room-compiler:2.6.1")

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
    androidTestImplementation("androidx.benchmark:benchmark-junit4:1.3.3")
    androidTestImplementation("androidx.test.ext:junit:1.3.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.7.0")
    androidTestImplementation(libs.androidx.test.uiautomator)

    // Unit testing
    testImplementation("junit:junit:4.13.2")
    testImplementation("io.mockk:mockk:1.14.6")
    androidTestImplementation("io.mockk:mockk-android:1.14.6")

    // Hilt testing
    testImplementation("com.google.dagger:hilt-android-testing:2.51.1")
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.51.1")
    kspAndroidTest("com.google.dagger:hilt-compiler:2.51.1")
    implementation(kotlin("stdlib-jdk8"))
}
