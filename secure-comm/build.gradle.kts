// ==== GENESIS PROTOCOL - SECURE COMMUNICATION MODULE ====
// Security module using convention plugins

plugins {
    id("genesis.android.library")
    id("genesis.android.native")
    id("genesis.android.hilt")
}

android {
    namespace = "dev.aurakai.auraframefx.securecomm"
    externalNativeBuild {
        cmake {
            path = file("src/main/cpp/CMakeLists.txt")
            version = "3.22.1"
        }
    }
}

dependencies {
    implementation(project(":core-module"))
    implementation(libs.hilt.android)
    implementation(libs.hilt.work)
    implementation(libs.androidx.work.runtime.ktx)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.bundles.coroutines)
    implementation(libs.bundles.network)
    implementation(platform(libs.firebase.bom))
    implementation(libs.bundles.firebase)
    implementation(libs.timber)
    implementation(libs.coil.compose)
    implementation(libs.mockk.agent)
    
    // Security - BouncyCastle for cryptography
    implementation(libs.bcprov.jdk18on)
    
    // Add other module-specific dependencies here
}
