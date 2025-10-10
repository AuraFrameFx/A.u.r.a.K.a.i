// GENESIS PROTOCOL - MODULES A-F
// Module E
plugins {
    id("com.android.compose")
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
    add("ksp", libs.hilt.compiler)

    // Add other module-specific dependencies here
}

tasks.register("moduleEStatus") {
    group = "aegenesis"
    doLast { println("📦 MODULE E - Ready (Java 24)") }
}
