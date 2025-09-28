plugins {
    id("java-library")
    id("org.jetbrains.kotlin.plugin.serialization") version "2.2.20"
}

group = "dev.aurakai.auraframefx.jvmtest"
version = "1.0.0"

java {
    toolchain {
    }
}

dependencies {
    testImplementation(libs.junit.jupiter.api)
    testRuntimeOnly(libs.junit.jupiter.engine)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlin.test)
}

tasks.register("jvmTestStatus") {
    group = "aegenesis"
}
