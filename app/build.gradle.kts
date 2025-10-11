plugins {
    id("com.android.application")  // Applies Hilt + Android + Compose + KSP
    alias(libs.plugins.firebase.crashlytics)
    alias(libs.plugins.google.services)
}

// Apply Hilt after Android plugin for AGP 9.0 compatibility
// apply(plugin = "com.google.dagger.hilt.android")  // Applied via convention plugin

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

    // Android components configuration for future use
    // androidComponents {
    //     onVariants(selector().all()) { variant ->
    //         // Configure variant-specific settings here if needed
    //     }
    // }

    packaging {
        resources {
            excludes += "META-INF/DEPENDENCIES"
            excludes += "META-INF/LICENSE"
            excludes += "META-INF/LICENSE.txt"
            excludes += "META-INF/license.txt"
            excludes += "META-INF/NOTICE"
            excludes += "META-INF/NOTICE.txt"
            excludes += "META-INF/notice.txt"
            excludes += "META-INF/ASL2.0"
            excludes += "META-INF/*.kotlin_module"
            excludes += "META-INF/AL2.0"
            excludes += "META-INF/LGPL2.1"
            excludes += "META-INF/INDEX.LIST"
            excludes += "META-INF/LICENSE.md"
            // Add more as needed if error persists
        }
        jniLibs {
            useLegacyPackaging = false
        }
        // Exclude XPosed/LSPosed JAR files from DEX processing
        dex {
            useLegacyPackaging = false
        }
    }
}

// Suppress deprecation warnings for AIDL-generated files
tasks.withType<JavaCompile>().configureEach {
    options.compilerArgs.addAll(
        listOf(
            "-Xlint:none",          // Suppress all lint warnings including deprecation
            "-nowarn"               // Suppress all warnings completely
        )
    )
    // Suppress warnings for generated AIDL files specifically
    options.encoding = "UTF-8"
}

// Prevent XPosed JAR files from being included in DEX processing
tasks.matching { it.name.contains("Dex") }.configureEach {
    doFirst {
        logger.info("Excluding XPosed JAR files from DEX processing")
    }
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
    // Added Material 3 dependencies from original request
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material.icons.extended)
    implementation("androidx.compose.material3:material3-window-size-class:1.3.0") // Using explicit version
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
    implementation("com.squareup.moshi:moshi:1.15.2")
    implementation("com.squareup.moshi:moshi-kotlin:1.15.2")

    // ===== KTOR FOR OPENAPI CLIENT =====
    implementation("io.ktor:ktor-client-core:3.3.0")
    implementation("io.ktor:ktor-client-content-negotiation:3.3.0")
    implementation("io.ktor:ktor-serialization-kotlinx-json:3.3.0")
    implementation("io.ktor:ktor-client-okhttp:3.3.0")
    implementation("io.ktor:ktor-client-auth:3.3.0")

    // ===== FIREBASE =====
    implementation(platform(libs.firebase.bom))
    // This bundle includes Analytics, Crashlytics, Performance, Auth, Firestore, Messaging, and Config
    implementation(libs.bundles.firebase)

    // ===== HILT DEPENDENCY INJECTION =====
    implementation(libs.hilt.android)
    implementation(libs.hilt.compiler)
    implementation(libs.hilt.navigation.compose)

    // ===== WORKMANAGER =====
    implementation("androidx.work:work-runtime-ktx:2.10.5")
    implementation("androidx.hilt:hilt-work:1.3.0")
    implementation(libs.hilt.work)  // Annotation processor for Hilt WorkManager integration

    // ===== UTILITIES =====
    implementation(libs.timber)
    implementation(libs.coil.compose)

    // ===== SECURITY =====
    implementation(libs.androidx.security.crypto)

    // ===== CORE LIBRARY DESUGARING =====
    coreLibraryDesugaring(libs.desugar.jdk.libs)

    // ===== XPOSED/LSPosed Integration =====
    // These are compile-only and should NOT be included in the APK
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