plugins {
    alias(libs.plugins.ksp)
}

android {
    namespace = "dev.aurakai.auraframefx.romtools"
}


dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.bundles.lifecycle)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose.ui)
    testImplementation(libs.bundles.testing.unit)
}
