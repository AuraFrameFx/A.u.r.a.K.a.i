// GENESIS PROTOCOL - MODULE A
plugins {
    id("genesis.android.library")
    id("genesis.android.compose")
    id("genesis.android.hilt")
}

android {
    namespace = "dev.aurakai.auraframefx.module.a"
}

dependencies {
    implementation(project(":core-module"))
    implementation(libs.androidx.core.ktx)
    // Add other module-specific dependencies here
}

tasks.register("moduleAStatus") {
    group = "aegenesis"
    doLast { println("📦 MODULE A - Ready (Java 24)") }
}
