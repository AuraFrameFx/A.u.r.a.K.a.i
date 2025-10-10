plugins {
    id("com.android.application")  // Convention plugin: applies Android, Kotlin, Hilt, Compose
    alias(libs.plugins.ksp)
    alias(libs.plugins.firebase.crashlytics)
}

// Note: google-services plugin is applied by genesis.android.application convention

// ==== GENESIS PROTOCOL - MAIN APPLICATION ====
android {
    namespace = "dev.aurakai.auraframefx"
    compileSdk = 36
    defaultConfig {
        applicationId = "dev.aurakai.auraframefx"
        minSdk = 34
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
        }

        externalNativeBuild {
            cmake {
                // No arguments here!
            }
        }
    }

    externalNativeBuild {
        cmake {
            path = file("src/main/cpp/CMakeLists.txt")
            version = "3.22.1"
        }
    }

    splits {
        abi {
            isEnable = true // Correct property name in Kotlin DSL
            reset()
            include("arm64-v8a", "x86_64")
            isUniversalApk = false // Correct property name in Kotlin DSL
        }
    }

    sourceSets {
        getByName("main") {
            java.srcDirs("src/main/java", "src/main/kotlin")
        }
        getByName("test") {
            java.srcDirs("src/test/java", "src/test/kotlin")
        }
        getByName("androidTest") {
            java.srcDirs("src/androidTest/java", "src/androidTest/kotlin")
            manifest.srcFile("src/androidTest/AndroidManifest.xml")
            assets.srcDirs("src/androidTest/assets")
            res.srcDirs("src/androidTest/res")
        }
        getByName("debug") {
            java.srcDirs("src/debug/java", "src/debug/kotlin")
            manifest.srcFile("src/debug/AndroidManifest.xml")
            assets.srcDirs("src/debug/assets")
            res.srcDirs("src/debug/res")
        }
        getByName("release") {
            java.srcDirs("src/release/java", "src/release/kotlin")
            manifest.srcFile("src/release/AndroidManifest.xml")
            assets.srcDirs("src/release/assets")
            res.srcDirs("src/release/res")
        }
    }

    buildTypes {
        debug {
            isDebuggable = true
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-DEBUG"
        }
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        aidl = true
        compose = true
    }

    lint {
        disable += "HardcodedText"
        disable += "EnsureInitializerMetadata"
        abortOnError = false
    }

    testOptions {
        unitTests.all {
            it.useJUnitPlatform()
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.toVersion("24")
        targetCompatibility = JavaVersion.toVersion("24")
        isCoreLibraryDesugaringEnabled = true
    }

    packaging {
        resources {
            excludes += "META-INF/INDEX.LIST"
            excludes += "META-INF/LICENSE.md"
        }
    }
}

// Suppress AIDL deprecation warnings
tasks.withType<JavaCompile>().configureEach {
    options.compilerArgs.add("-Xlint:none")
}

dependencies {
    // ===== MODULE DEPENDENCIES =====
    implementation(project(":core-module")) {
        exclude(group = "org.jetbrains.dokka")
    }
    implementation(project(":feature-module"))
    implementation(project(":romtools"))
    implementation(project(":secure-comm"))
    implementation(project(":collab-canvas"))
    implementation(project(":colorblendr"))
    implementation(project(":sandbox-ui"))
    implementation(project(":datavein-oracle-native"))
    implementation(project(":module-a"))
    implementation(project(":module-b"))
    implementation(project(":module-c"))
    implementation(project(":module-d"))
    implementation(project(":module-e"))
    implementation(project(":module-f"))
    implementation(project(":benchmark"))
    implementation(project(":data:api")) // Add dependency on new OpenAPI module

    // ===== ANDROIDX & COMPOSE =====
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose.ui)
    implementation(libs.androidx.core.ktx)
    debugImplementation(libs.bundles.compose.debug)

    // ===== LIFECYCLE =====
    implementation(libs.bundles.lifecycle)

    // ===== DATABASE - ROOM =====
    implementation(libs.bundles.room)

    // ===== DATASTORE =====
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.androidx.datastore.core)

    // ===== KOTLIN & COROUTINES =====
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlinx.datetime)
    implementation(libs.bundles.coroutines)

    // ===== NETWORKING =====
    implementation(libs.bundles.network)
    implementation(libs.moshi)
    implementation(libs.moshi.kotlin)

    // ===== KTOR FOR OPENAPI CLIENT =====
    implementation(libs.ktor.client.cio)
    implementation(libs.ktor.client.auth)

    // ===== FIREBASE =====
    implementation(platform(libs.firebase.bom))
    // This bundle includes Analytics, Crashlytics, Performance, Auth, Firestore, Messaging, and Config
    implementation(libs.bundles.firebase)

    // ===== HILT DEPENDENCY INJECTION =====
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler) // Use KSP for Hilt annotation processor
    implementation(libs.hilt.navigation.compose)
    implementation(libs.androidx.work.runtime.ktx)
    implementation(libs.hilt.work)
    ksp(libs.hilt.work)  // Annotation processor for Hilt WorkManager integration

    // ===== UTILITIES =====
    implementation(libs.timber)
    implementation(libs.coil.compose)

    // ===== SECURITY =====
    implementation(libs.androidx.security.crypto)

    // ===== CORE LIBRARY DESUGARING =====
    coreLibraryDesugaring(libs.desugar.jdk.libs)

    // ===== XPOSED/LSPosed Integration =====
    compileOnly(files("../Libs/api-82.jar"))
    compileOnly(files("../Libs/api-82-sources.jar"))

    // --- TESTING ---
    testImplementation(libs.bundles.testing.unit)
    androidTestImplementation(libs.bundles.testing.android)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.hilt.android.testing) // For Hilt in Android tests

    // --- DEBUGGING ---
    debugImplementation(libs.leakcanary.android)

    implementation(libs.kotlin.reflect)
}
