// GENESIS PROTOCOL - MODULE F
plugins {
    id("genesis.android.library")
    id("genesis.android.compose")
    alias(libs.plugins.ksp)
}

android {
    namespace = "dev.aurakai.auraframefx.module.f"
}

dependencies {
    implementation(project(":core-module"))
    implementation(libs.androidx.core.ktx)
    
    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    
    // Add other module-specific dependencies here
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.0")
}

tasks.register("moduleFStatus") {
    group = "aegenesis"
    doLast { println("📦 MODULE F - Ready (Java 24)") }
}

kotlin {
    jvmToolchain(24)
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(24))
    }
}
