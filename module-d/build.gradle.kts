// GENESIS PROTOCOL - MODULE D
plugins {
    id("genesis.android.library")
    id("genesis.android.compose")
    id("genesis.android.hilt")
}

android {
    namespace = "dev.aurakai.auraframefx.module.d"
}

dependencies {
    implementation(project(":core-module"))
    implementation(libs.androidx.core.ktx)
    // Add other module-specific dependencies here
}

tasks.register("moduleDStatus") {
    group = "aegenesis"
    doLast { println("📦 MODULE D - Ready (Java 24)") }
}
