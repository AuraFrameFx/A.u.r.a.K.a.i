// GENESIS PROTOCOL - MODULE B  
plugins {
    id("genesis.android.library")
    id("genesis.android.compose")
    id("genesis.android.hilt")
}

android {
    namespace = "dev.aurakai.auraframefx.module.b"
}

dependencies {
    implementation(project(":core-module"))
    implementation(libs.androidx.core.ktx)
    // Add other module-specific dependencies here
    implementation(kotlin("stdlib-jdk8"))
}

tasks.register("moduleBStatus") {
    group = "aegenesis"
    doLast { println("📦 MODULE B - Ready (Java 24)") }
}

kotlin {
    jvmToolchain(24)
}
