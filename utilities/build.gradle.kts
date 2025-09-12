import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.dokka)
    alias(libs.plugins.spotless)
}
group = "dev.aurakai.auraframefx.utilities"
version = "1.0.0"

}

    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_24)
    }
}

dependencies {
    api(project(":list"))

    // Kotlin standard library
    implementation(libs.kotlin.stdlib)
    implementation(libs.bundles.coroutines)
    implementation(libs.kotlinx.serialization.json)

    // Utilities for file operations and compression
    implementation(libs.commons.io)
    implementation(libs.commons.compress)
    implementation(libs.xz)


    // Testing
    testImplementation(libs.bundles.testing)
    testImplementation(libs.mockk)
}

    useJUnitPlatform()
}
