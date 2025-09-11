// GENESIS PROTOCOL - MODULE A
plugins {
}

android {
}

dependencies {
    implementation(project(":core-module"))
    implementation(libs.androidx.core.ktx)
}

tasks.register("moduleAStatus") {
    group = "aegenesis"
}
