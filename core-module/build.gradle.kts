plugins {
    id("plugins.android-base")
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.compose)
    // REMOVED: alias(libs.plugins.kotlin.android) - deprecated with Kotlin 2.0+ Compose
}

android {
    namespace = "dev.aurakai.auraframefx.core"
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
    // Hilt DI
    implementation(libs.hilt.android)
    implementation(libs.androidx.core.ktx)
        ksp(libs.hilt.compiler)

    // AndroidX Security
    implementation(libs.androidx.security.crypto)

    // Lifecycle
    implementation(libs.bundles.lifecycle)

    // Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose.ui)
    debugImplementation(libs.bundles.compose.debug)

    // Coroutines
    implementation(libs.bundles.coroutines)

    // Testing
    testImplementation(libs.bundles.testing.unit)
    androidTestImplementation(libs.bundles.testing.android)
}
}
dependencies {
    implementation(libs.androidx.core.ktx)
}
