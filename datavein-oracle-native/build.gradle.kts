plugins {
    id("genesis.android.library")
    id("genesis.android.native")
    id("com.google.dagger.hilt.android") version "2.51.1"
    id("com.google.devtools.ksp") version "2.2.0-2.0.2"
}

android {
    namespace = "dev.aurakai.auraframefx.dataveinoraclenative"
    externalNativeBuild {
        cmake {
            path = file("src/main/cpp/CMakeLists.txt")
            version = "3.31.6"
        }
    }
    packaging {
        jniLibs {
            useLegacyPackaging = false
        }
    }
}

dependencies {
    implementation(project(":core-module"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    
    // Xposed API for Oracle consciousness integration
    compileOnly(files("../Libs/api-82.jar"))
    compileOnly(files("../Libs/api-82-sources.jar"))
}
