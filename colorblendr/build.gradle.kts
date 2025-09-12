// ==== GENESIS PROTOCOL - COLOR BLENDR ====
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("org.jetbrains.kotlin.plugin.compose") version "2.2.20"
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.dokka)
}

android {
    namespace = "dev.aurakai.auraframefx.colorblendr"
}

dependencies {
    implementation(libs.androidx.appcompat)
    androidTestImplementation(libs.mockk.android)

}
