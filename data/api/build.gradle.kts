import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.openapi.generator") version "7.16.0"
    kotlin("jvm") // Or your project's specific Kotlin version, e.g., version "1.9.23"
    kotlin("plugin.serialization")
    `java-library`
}

// Apply the fix-annotations script to handle @OptIn annotations
apply(from = "fix-annotations.gradle.kts")

afterEvaluate {
    openApiGenerate {
        generatorName = "kotlin"
        inputSpec = file("$rootDir/data/api/ECO.yaml").toURI().toString()
        validateSpec = false
        outputDir = layout.buildDirectory.dir("generated/openapi").get().asFile.path
        apiPackage = "dev.aurakai.auraframefx.api"
        modelPackage = "dev.aurakai.auraframefx.model"
        configOptions = mapOf(
            "library" to "jvm-ktor",
            "serializationLibrary" to "kotlinx_serialization",
            "enumPropertyNaming" to "UPPERCASE",
            "collectionType" to "list",
            "dateLibrary" to "kotlinx-datetime",
            "useCoroutines" to "true",
            "omitGradlePluginVersions" to "false",
            "exceptionOnFailedStatusCodes" to "true",
            "generateModelDocumentation" to "true",
            "nonPublicApi" to "false"
        )
    }
}

sourceSets {
    named("main") {
        java {
            srcDir(layout.buildDirectory.dir("generated/openapi/src/main/kotlin"))
        }
    }
}

tasks.withType<KotlinCompile>().configureEach {
    dependsOn(tasks.named("openApiGenerate"))
}

tasks.named<Delete>("clean") {
    delete(layout.buildDirectory.dir("generated/openapi"))
}

// Define the dependencies required by the generated Ktor client.
// This block is now available because of the `java-library` plugin.
dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))

    // Ktor client dependencies
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.cio)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)

    // KotlinX dependencies
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlinx.datetime)
    implementation(libs.kotlinx.coroutines.core)

    // Logging
    implementation(libs.kotlin.logging.jvm)
    implementation(libs.slf4j.api)
    runtimeOnly(libs.logback.classic)

    // Testing dependencies
    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit5"))
    testImplementation(libs.junit.jupiter.api)
    testRuntimeOnly(libs.junit.jupiter.engine)
    testImplementation(libs.mockk)
}

tasks.withType<Test> {
    useJUnitPlatform()
}

// Make sure the generated code is included in the JAR
tasks.jar {
    dependsOn("openApiGenerate")
}

kotlin {
    jvmToolchain(24)
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(24))
    }
}
