// GENESIS PROTOCOL - MODULE F
plugins {
    id("genesis.android.library")
    id("genesis.android.compose")
    id("genesis.android.hilt")
}

android {
    namespace = "dev.aurakai.auraframefx.module.f"
}

dependencies {
    implementation(project(":core-module"))
    implementation(libs.androidx.core.ktx)
    // Add other module-specific dependencies here
}

tasks.register("moduleFStatus") {
    group = "aegenesis"
    doLast { println("📦 MODULE F - Ready (Java 24)") }
}
