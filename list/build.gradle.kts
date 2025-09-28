plugins {
    id("plugins.android-base")
    alias(libs.plugins.ksp)
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    namespace = "dev.aurakai.auraframefx.list"
    defaultConfig {
        minSdk = 33
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_21)
    }
}

group = "dev.aurakai.auraframefx.list"
version = "1.0.0"

dependencies {
    implementation(libs.kotlin.stdlib.jdk8)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.bundles.coroutines)
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(libs.androidx.core.ktx)
    testImplementation(libs.bundles.testing.unit)
    androidTestImplementation(libs.bundles.testing.android)
}
