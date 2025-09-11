// GENESIS PROTOCOL - MODULE D
plugins {
}

android {
}

dependencies {
    // Module dependencies
    implementation(project(":core-module"))

    // Core Android
    implementation(libs.androidx.core.ktx)
}

tasks.register("moduleDStatus") {
    group = "aegenesis"
}
