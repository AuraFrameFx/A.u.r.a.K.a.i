plugins {
    id("genesis.android.application")

}

// ==== GENESIS PROTOCOL - MAIN APPLICATION ====
android {
    namespace = "dev.aurakai"
    compileSdk = 36

    defaultConfig {
        applicationId = "dev.aurakai"
        minSdk = 34
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            isDebuggable = true
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-DEBUG"
        }
    }

    // Enable AIDL for the app module
    buildFeatures {
        aidl = true
        compose = true
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
    implementation("com.squareup.moshi:moshi:1.15.1")
    implementation("com.squareup.moshi:moshi-kotlin:1.15.1")

    // ===== KTOR FOR OPENAPI CLIENT =====
    implementation("io.ktor:ktor-client-core:2.3.7")
    implementation("io.ktor:ktor-client-content-negotiation:2.3.7")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.7")
    implementation("io.ktor:ktor-client-okhttp:2.3.7")
    implementation("io.ktor:ktor-client-auth:2.3.7")

    // ===== FIREBASE =====
    implementation(platform(libs.firebase.bom))
    // This bundle includes Analytics, Crashlytics, Performance, Auth, Firestore, Messaging, and Config
    implementation(libs.bundles.firebase)

    // ===== HILT DEPENDENCY INJECTION =====
    // Auto-added by genesis.android.application convention plugin

    // ===== WORKMANAGER =====
    implementation("androidx.work:work-runtime-ktx:2.9.0")
    implementation("androidx.hilt:hilt-work:1.2.0")

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
