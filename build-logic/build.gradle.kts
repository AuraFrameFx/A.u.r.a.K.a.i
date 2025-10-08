// AOSP-ReGenesis/build-logic/build.gradle.kts
plugins {
    `kotlin-dsl`

}


group = "dev.aurakai.auraframefx.buildlogic"

// Dependencies required for the convention plugins themselves.
dependencies {
    implementation("com.android.tools.build:gradle:9.0.0-alpha09")
    implementation(libs.kotlin.gradle.plugin)
    implementation("org.jetbrains.kotlin:compose-compiler-gradle-plugin:2.2.20")
    implementation("com.google.dagger:hilt-android-gradle-plugin:2.57.2")

    // Test dependencies
    testImplementation(kotlin("test"))
    testImplementation(libs.junit.jupiter.api)
    testImplementation(libs.junit.jupiter.engine)
    testImplementation(libs.junit.jupiter.params)
    testImplementation("org.gradle:gradle-tooling-api:9.1.0")
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
        register("androidApplication") {
            id = "genesis.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidLibrary") {
            id = "genesis.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidHilt") {
            id = "genesis.android.hilt"
            implementationClass = "plugins.AndroidHiltConventionPlugin"
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

tasks.withType<JavaCompile> {
    sourceCompatibility = "24"
    targetCompatibility = "24"
}

kotlin {
    jvmToolchain(24)
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(24))
    }
}
