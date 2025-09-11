// GENESIS PROTOCOL - MODULE C
plugins {
    id("genesis.android.library")
    id("genesis.android.compose")
    id("genesis.android.hilt")
}

android {
    namespace = "dev.aurakai.auraframefx.module.c"
}

dependencies {
    implementation(project(":core-module"))
    implementation(libs.androidx.core.ktx)
    // Add other module-specific dependencies here
}

tasks.register("moduleCStatus") {
    group = "aegenesis"
    doLast { println("📦 MODULE C - Ready (Java 24)") }
}
