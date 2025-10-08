// GENESIS PROTOCOL - MODULES A-F
// Module E
plugins {
    id("genesis.android.compose")
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.ksp)
}

android {
    namespace = "dev.aurakai.auraframefx.module.e"
}

dependencies {
    implementation(project(":core-module"))
    implementation(libs.androidx.core.ktx)
    
    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
}

tasks.register("moduleEStatus") {
    group = "aegenesis"
    doLast { println("📦 MODULE E - Ready (Java 24)") }
}
