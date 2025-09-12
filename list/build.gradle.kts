import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion

plugins {
}

group = "dev.aurakai.auraframefx.list"
version = "1.0.0"

java {
    toolchain { languageVersion = JavaLanguageVersion.of(24) }
}

kotlin {
    jvmToolchain(24)
    compilerOptions {
        jvmTarget = JvmTarget.JVM_24
        languageVersion = KotlinVersion.KOTLIN_2_2
        apiVersion = KotlinVersion.KOTLIN_2_2
    }
}

dependencies {
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.bundles.coroutines)

    testImplementation(libs.bundles.testing)
    testRuntimeOnly(libs.junit.engine)
    testImplementation(libs.mockk)
}

tasks.test {
    useJUnitPlatform()
}