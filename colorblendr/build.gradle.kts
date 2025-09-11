// ==== GENESIS PROTOCOL - COLOR BLENDR ====
plugins {
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
