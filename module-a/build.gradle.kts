// GENESIS PROTOCOL - MODULE A
plugins {
    id("genesis.android.compose")
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.ksp)
}

android {
    namespace = "dev.aurakai.auraframefx.module.a"
}

dependencies {
    implementation(project(":core-module"))
    implementation(libs.androidx.core.ktx)
    
    // Hilt
    implementation(libs.hilt.android)
    add("ksp", libs.hilt.compiler)

    // Add oth`er module-specific dependencies here
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.0")
}

tasks.register("moduleAStatus") {
    group = "aegenesis"
    doLast { println("📦 MODULE A - Ready (Java 24)") }
}

