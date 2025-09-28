plugins {
    id("plugins.android-base")
    id("com.google.devtools.ksp")
    id("org.jetbrains.kotlin.plugin.compose")
    id("org.jetbrains.kotlin.plugin.serialization")
    // REMOVED: alias(libs.plugins.kotlin.android) - deprecated with Kotlin 2.0+ Compose
}

android {
    namespace = "dev.aurakai.auraframefx.moduled"
    defaultConfig {
        minSdk = 33
    }
    compileOptions {
    }
}

dependencies {
    // Module dependencies
    implementation(project(":core-module"))

    // Core Android
    implementation(libs.androidx.core.ktx)
    implementation(libs.bundles.lifecycle)

    // Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose.ui)
    debugImplementation(libs.bundles.compose.debug)

    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    // Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.bundles.firebase)

    // Utilities
    implementation(libs.kotlin.stdlib.jdk8)

    // Testing
    testImplementation(libs.bundles.testing.unit)
    androidTestImplementation(libs.bundles.testing.android) {
        exclude(group = "androidx.test", module = "monitor")
    }
    androidTestImplementation(libs.hilt.android.testing)
    kspTest(libs.hilt.compiler)
}

tasks.register("moduleDStatus") {
    group = "aegenesis"
    doLast { println("📦 MODULE D - Ready (Java 25, JVM 25)") } // Updated
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_21)
    }
}
