plugins {
    id("plugins.android-base")
    // REMOVED: alias(libs.plugins.kotlin.android) - deprecated with Kotlin 2.0+ Compose
}

android {
    namespace = "dev.aurakai.auraframefx.modulea"
    defaultConfig {
        minSdk = 33
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
    implementation(platform(libs.firebase.bom))
    implementation(libs.bundles.firebase)
    implementation(libs.kotlin.stdlib.jdk8)
    testImplementation(libs.bundles.testing.unit)
    androidTestImplementation(libs.bundles.testing.android) {
        exclude(group = "androidx.test", module = "monitor")
    }
    androidTestImplementation(libs.hilt.android.testing)
}

tasks.register("moduleAStatus") {
    group = "aegenesis"
    doLast { println("📦 MODULE A - Ready (Java 25, JVM 25)") } // Updated
}
