// AOSP-ReGenesis/build-logic/build.gradle.kts

plugins {
    `kotlin-dsl`
}

group = "dev.aurakai.auraframefx.buildlogic"

// Dependencies required for the convention plugins themselves.
dependencies {
    implementation("com.android.tools.build:gradle:9.0.0-alpha02")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:2.2.20-RC")
    implementation("com.google.dagger:hilt-android-gradle-plugin:2.51.1")
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "genesis.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidLibrary") {
            id = "genesis.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        // This is the new registration for the Hilt convention plugin
        register("androidHilt") {
            id = "genesis.android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }
        register("androidCompose") {
            id = "genesis.android.compose"
            implementationClass = "AndroidComposeConventionPlugin"
        }
        register("androidNative") {
            id = "genesis.android.native"
            implementationClass = "AndroidNativeConventionPlugin"
        }
    }
}