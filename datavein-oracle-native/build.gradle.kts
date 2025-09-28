plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.ksp)
    alias(libs.plugins.compose.compiler)
}
android {
    namespace = "dev.aurakai.auraframefx.datavein"
    compileSdk = 36
    defaultConfig {
        minSdk = 33
    }
    buildFeatures {
        buildConfig = true
        resValues = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
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
    testImplementation(libs.bundles.testing.unit)
    androidTestImplementation(libs.bundles.testing.android)
}
}
