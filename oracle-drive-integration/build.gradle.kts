// ==== GENESIS PROTOCOL - ORACLE DRIVE INTEGRATION ====
// AI storage module using convention plugins

plugins {
    id("genesis.android.library")
    id("genesis.android.native")
    alias(libs.plugins.ksp)

}

android {
    namespace = "dev.aurakai.auraframefx.oracledriveintegration"
    
    defaultConfig {
        minSdk = 34
    }
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
    add("ksp", libs.hilt.compiler)
    implementation(libs.hilt.work)
    implementation(libs.bundles.coroutines)
    // Add other module-specific dependencies here
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:2.2.20")
}


java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(24))
    }
}
