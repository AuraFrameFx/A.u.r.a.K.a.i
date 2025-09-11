// ==== GENESIS PROTOCOL - ORACLE DRIVE INTEGRATION ====
// AI storage module using convention plugins

plugins {
    id("genesis.android.library")
    id("genesis.android.hilt")
}

android {
    namespace = "dev.aurakai.auraframefx.oracledriveintegration"
}

dependencies {
    implementation(project(":core-module"))
    implementation(project(":secure-comm"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.hilt.android)
    implementation(libs.hilt.work)
    implementation(libs.bundles.coroutines)
    // Add other module-specific dependencies here
}
