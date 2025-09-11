// ==== GENESIS PROTOCOL - SANDBOX UI ====

plugins {
    alias(libs.plugins.ksp)
}

android {
    namespace = "dev.aurakai.auraframefx.sandboxui"
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose.ui)
}
