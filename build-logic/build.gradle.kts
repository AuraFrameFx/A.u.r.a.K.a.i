// AOSP-ReGenesis/build-logic/build.gradle.kts
plugins {
    `kotlin-dsl`
}


group = "dev.aurakai.auraframefx.buildlogic"

// Dependencies required for the convention plugins themselves.
dependencies {
    implementation(libs.gradle)
    implementation(libs.kotlin.gradle.plugin)
    implementation(libs.dagger.hilt.android.gradle.plugin)
    implementation(libs.com.google.devtools.ksp.gradle.plugin)
    implementation(libs.kotlin.compose.compiler.gradle.plugin)
    implementation(libs.openapi.generator.gradle.plugin)


    // Test dependencies
    testImplementation(kotlin("test"))
    testImplementation(libs.junit.jupiter.api)
    testImplementation(libs.junit.jupiter.engine)
    testImplementation(libs.junit.jupiter.params)
    testImplementation(libs.gradle.tooling.api)
    testImplementation(gradleTestKit())
}

// Configure test execution (temporarily disabled for bleeding-edge compatibility)
tasks.test {
    useJUnitPlatform()
    enabled = true // Re-enabled for full test support
}

tasks.compileTestKotlin {
    enabled = true // Re-enabled for full test support
}

gradlePlugin {
    plugins {
        // Base plugin - applies Hilt + KSP to all Android modules
        register("androidBase") {
            id = "genesis.android.base"
            implementationClass = "plugins.AndroidBasePlugin"
        }
        // Application plugin - extends base with app-specific config
        register("androidApplication") {
            id = "genesis.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        // Library plugin - extends base with library-specific config
        register("androidLibrary") {
            id = "genesis.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        // Compose plugin - adds Compose dependencies
        register("androidCompose") {
            id = "genesis.android.compose"
            implementationClass = "AndroidComposeConventionPlugin"
        }
        // Native plugin - for JNI/NDK modules
        register("androidNative") {
            id = "genesis.android.native"
            implementationClass = "AndroidNativeConventionPlugin"
        }
        register("openapiConvention") {
            id = "genesis.openapi.convention"
            implementationClass = "OpenApiConventionPlugin"
        }
    }
}



java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(24))
    }
}
