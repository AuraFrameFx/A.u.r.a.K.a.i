// GENESIS PROTOCOL - MODULE C
plugins {
    id("genesis.android.compose")
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.ksp)
}

android {
    namespace = "dev.aurakai.auraframefx.module.c"
}

dependencies {
    implementation(project(":core-module"))
    implementation(libs.androidx.core.ktx)
    
    // Hilt
    implementation(libs.hilt.android)
    add("ksp", libs.hilt.compiler)

    // Add other module-specific dependencies here
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:2.2.20")
}

tasks.register("moduleCStatus") {
    group = "aegenesis"
    doLast { println("📦 MODULE C - Ready (Java 24)") }
}
