// AOSP-ReGenesis/build-logic/build.gradle.kts

plugins {
    `kotlin-dsl`
}


repositories {
    gradlePluginPortal()
    google()
    mavenCentral()
    maven {
        url = uri("https://repo.gradle.org/gradle/libs-releases")
    }
}

// Dependencies required for the convention plugins themselves.
dependencies {
    implementation("com.android.tools.build:gradle:9.0.0-alpha02")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:2.2.20-RC")
    implementation("com.google.dagger:hilt-android-gradle-plugin:2.51.1")
}

gradlePlugin {
    plugins {
        }
        }
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
kotlin {
    jvmToolchain(23)
}