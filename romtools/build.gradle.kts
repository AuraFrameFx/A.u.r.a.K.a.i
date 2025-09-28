plugins {
    id("plugins.android-base")
    alias(libs.plugins.ksp)
    alias(libs.plugins.compose.compiler)
    // REMOVED: alias(libs.plugins.kotlin.android) - deprecated with Kotlin 2.0+ Compose
}

android {
    namespace = "dev.aurakai.auraframefx.romtools"
    defaultConfig {
        minSdk = 33
    }

    buildFeatures {
        buildConfig = true
        resValues = true
    }

    compileOptions {
    }
        }
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        compilerOptions {
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
    implementation(libs.kotlin.stdlib.jdk8)
    testImplementation(libs.bundles.testing.unit)
    androidTestImplementation(libs.bundles.testing.android)
}
}
dependencies {
    implementation(libs.androidx.core.ktx)
}
