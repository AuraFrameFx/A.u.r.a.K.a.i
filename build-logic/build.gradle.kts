// AOSP-ReGenesis/build-logic/build.gradle.kts

plugins {
    `kotlin-dsl`
}

group = "dev.aurakai.auraframefx.buildlogic"

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
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:2.2.20")
    implementation("com.google.dagger:hilt-android-gradle-plugin:2.51.1")
    
    // Test dependencies
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.2")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.10.2")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.10.2")
    testImplementation("org.gradle:gradle-tooling-api:9.0.0")
    testImplementation(gradleTestKit())
}

// Configure test execution (temporarily disabled for bleeding-edge compatibility)
tasks.test {
    useJUnitPlatform()
    enabled = false // Disable until AGP 9.0 test compatibility is resolved
}

// Disable test compilation temporarily
tasks.compileTestKotlin {
    enabled = false
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