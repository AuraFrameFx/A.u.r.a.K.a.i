plugins {
    id("plugins.android-base")
    alias(libs.plugins.ksp)
}

android {
    namespace = "dev.aurakai.auraframefx.modulea"
    defaultConfig {
        minSdk = 33
    }
}

dependencies {
    implementation(project(":core-module"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.bundles.lifecycle)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose.ui)
    debugImplementation(libs.bundles.compose.debug)
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(platform(libs.firebase.bom))
    implementation(libs.bundles.firebase)
    implementation(libs.kotlin.stdlib.jdk8)
    implementation(libs.timber)
    testImplementation(libs.bundles.testing.unit)
    androidTestImplementation(libs.bundles.testing.android) {
        exclude(group = "androidx.test", module = "monitor")
    }
    androidTestImplementation(libs.hilt.android.testing)
}

tasks.register("moduleAStatus") {
    group = "aegenesis"
    doLast { println("📦 MODULE A - Ready (Java 24, JVM 24)") }
}