// GENESIS PROTOCOL - MODULES A-F
// Module E
plugins {
    id("genesis.android.library")
    id("genesis.android.compose")
    id("genesis.android.hilt")
}

android {
    namespace = "dev.aurakai.auraframefx.module.e"
}

dependencies {
    implementation(project(":core-module"))
    implementation(libs.androidx.core.ktx)
    // Add other module-specific dependencies here
}

tasks.register("moduleEStatus") {
    group = "aegenesis"
    doLast { println("📦 MODULE E - Ready (Java 24)") }
}
