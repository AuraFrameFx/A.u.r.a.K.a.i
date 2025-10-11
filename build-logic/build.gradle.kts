// build-logic/build.gradle.kts
plugins {
    `kotlin-dsl`
}

group = "dev.aurakai.auraframefx.buildlogic"

// Dependencies required for the convention plugins themselves.
dependencies {
    implementation(libs.gradle)
    implementation(libs.kotlin.gradle.plugin)
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

tasks.test {
    useJUnitPlatform()
    enabled = true
}
tasks.compileTestKotlin {
    enabled = true
}

gradlePlugin {
    plugins {
        register("androidBase") {
            id = "genesis.android.base"
            implementationClass = "plugins.AndroidBasePlugin"
        }
        register("androidApplication") {
            id = "genesis.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidLibrary") {
            id = "genesis.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidCompose") {
            id = "genesis.android.compose"
            implementationClass = "AndroidComposeConventionPlugin"
        }
        register("androidNative") {
            id = "genesis.android.native"
            implementationClass = "AndroidNativeConventionPlugin"
        }
        register("agentFusion") {
            id = "genesis.agent.fusion"
            implementationClass = "plugins.AgentFusionPlugin"
        }
        register("yukiHookAndroid") {
            id = "genesis.yuki.android"
            implementationClass = "YukiHookAndroidConventionPlugin"
        }
    }
}

kotlin {
    jvmToolchain(24)
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(24))
    }
}