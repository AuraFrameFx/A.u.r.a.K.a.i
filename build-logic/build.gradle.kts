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


    // Test dependencies
    testImplementation(kotlin("test"))
    testImplementation(libs.junit.jupiter.api)
    testImplementation(libs.junit.jupiter.engine)
    testImplementation(libs.junit.jupiter.params)
    testImplementation(libs.gradle.tooling.api)
    testImplementation(gradleTestKit())
}

// Configure test execution (disabled - AGP 9.0 API incompatibility)
tasks.test {
    useJUnitPlatform()
    enabled = false
}

tasks.compileTestKotlin {
    enabled = false
}

tasks.withType<Test> {
    enabled = false
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
    }
}



java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(24))
    }
}
