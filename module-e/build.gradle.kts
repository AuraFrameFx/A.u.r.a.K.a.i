plugins {
}

android {
    namespace = "dev.aurakai.auraframefx.module.e"
    compileSdk = 36
    defaultConfig {
        minSdk = 33
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_25
        targetCompatibility = JavaVersion.VERSION_25
    }
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_24)
            freeCompilerArgs.addAll(
                "-Xjvm-default=all",
                "-Xopt-in=kotlin.RequiresOptIn"
            )
        }
    }

    lint {
        targetSdk = 36
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    ndkVersion = "28.2.13676358"

    buildFeatures {
        buildConfig = true
        resValues = true
    }
}

dependencies {
    // Module dependencies
    implementation(project(":core-module"))

    // Core Android
    implementation(libs.androidx.core.ktx)

}
