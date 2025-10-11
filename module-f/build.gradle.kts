// GENESIS PROTOCOL - MODULE F
plugins {
    id("com.android.library")
    alias(libs.plugins.ksp)
}

android {
    namespace = "dev.aurakai.auraframefx.module.f"

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_24
        targetCompatibility = JavaVersion.VERSION_24
    }
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

tasks.register("moduleFStatus") {
    group = "aegenesis"
    doLast { println("📦 MODULE F - Ready (Java 24)") }
}
