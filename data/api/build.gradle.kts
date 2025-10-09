import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.openapi.generator") version "7.16.0"
    kotlin("jvm") // Or your project's specific Kotlin version, e.g., version "1.9.23"
    kotlin("plugin.serialization")
    `java-library`
}

val ecoCoreSpec = file("${rootDir}/data/api/eco-core.yaml")
val ecoAiSpec = file("${rootDir}/data/api/eco-ai.yaml")

require(ecoCoreSpec.exists()) { "OpenAPI spec not found at: ${ecoCoreSpec.absolutePath}" }
require(ecoAiSpec.exists()) { "OpenAPI spec not found at: ${ecoAiSpec.absolutePath}" }

openApiGenerate {
    generatorName = "kotlin"
    inputSpec = ecoCoreSpec.toURI().toString()
    validateSpec = false
    outputDir = layout.buildDirectory.dir("generated/openapi/ecocore").get().asFile.path
    apiPackage = "dev.aurakai.auraframefx.api.ecocore"
    modelPackage = "dev.aurakai.auraframefx.model.ecocore"

    additionalProperties = mapOf(
        "skipValidateSpec" to "true",
        "legacyDiscriminatorBehavior" to "false"
    )

    configOptions = mapOf(
        "library" to "jvm-ktor",
        "serializationLibrary" to "kotlinx_serialization",
        "enumPropertyNaming" to "UPPERCASE",
        "collectionType" to "list",
        "dateLibrary" to "kotlinx-datetime",
        "useCoroutines" to "true",
        "omitGradlePluginVersions" to "false",
        "exceptionOnFailedStatusCodes" to "true",
        "generateModelDocumentation" to "false",
        "nonPublicApi" to "false",
        "hideGenerationTimestamp" to "true",
        "sortParamsByRequiredFlag" to "true",
        "sortModelPropertiesByRequiredFlag" to "true"
    )

    openapiNormalizer = mapOf(
        "REFACTOR_ALLOF_WITH_PROPERTIES_ONLY" to "true",
        "SIMPLIFY_ONEOF_ANYOF" to "true"
    )
}

tasks.register("openApiGenerateEcoAi", org.openapitools.generator.gradle.plugin.tasks.GenerateTask::class) {
    generatorName = "kotlin"
    inputSpec = ecoAiSpec.toURI().toString()
    validateSpec = false
    outputDir = layout.buildDirectory.dir("generated/openapi/ecoai").get().asFile.path
    apiPackage = "dev.aurakai.auraframefx.api.ecoai"
    modelPackage = "dev.aurakai.auraframefx.model.ecoai"

    additionalProperties = mapOf(
        "skipValidateSpec" to "true",
        "legacyDiscriminatorBehavior" to "false"
    )

    configOptions = mapOf(
        "library" to "jvm-ktor",
        "serializationLibrary" to "kotlinx_serialization",
        "enumPropertyNaming" to "UPPERCASE",
        "collectionType" to "list",
        "dateLibrary" to "kotlinx-datetime",
        "useCoroutines" to "true",
        "omitGradlePluginVersions" to "false",
        "exceptionOnFailedStatusCodes" to "true",
        "generateModelDocumentation" to "false",
        "nonPublicApi" to "false",
        "hideGenerationTimestamp" to "true",
        "sortParamsByRequiredFlag" to "true",
        "sortModelPropertiesByRequiredFlag" to "true"
    )

    openapiNormalizer = mapOf(
        "REFACTOR_ALLOF_WITH_PROPERTIES_ONLY" to "true",
        "SIMPLIFY_ONEOF_ANYOF" to "true"
    )
}

tasks.named("openApiGenerate") {
    outputs.dir(layout.buildDirectory.dir("generated/openapi/ecocore"))
}
tasks.named("openApiGenerateEcoAi") {
    outputs.dir(layout.buildDirectory.dir("generated/openapi/ecoai"))
}

sourceSets {
    named("main") {
        java.srcDir(layout.buildDirectory.dir("generated/openapi/eco/src/main/kotlin"))
        java.srcDir(layout.buildDirectory.dir("generated/openapi/ecoai/src/main/kotlin"))
    }
}

// ✅ CHANGED: finalizedBy → dependsOn (this is the ONLY change)
tasks.withType<KotlinCompile>().configureEach {
    dependsOn(tasks.named("openApiGenerate"))  // ✅ FIXED - was finalizedBy
    compilerOptions {
        freeCompilerArgs.add("-opt-in=kotlin.time.ExperimentalTime")
    }
}

tasks.named<Delete>("clean") {
    delete(file("${layout.buildDirectory.get().asFile}/generated/openapi"))
}

tasks.jar {
    dependsOn(tasks.named("openApiGenerate"))
}

val openApiGeneratedDir = layout.buildDirectory.dir("generated/openapi")

// Add a rule to the 'clean' task to delete the generated directory.
// This prevents stale or old generated files from causing issues.
tasks.named("clean") {
    doLast {
        delete(layout.buildDirectory.dir("generated/openapi"))
    }
}

// Define the dependencies required by the generated Ktor client.
// This block is now available because of the `java-library` plugin.
dependencies {
    // Core dependencies for Kotlin
    implementation(kotlin("stdlib"))
    implementation(libs.kotlin.reflect)

    implementation(libs.ktor.client.cio)
    implementation(libs.ktor.client.auth)

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
    testImplementation("org.junit.jupiter:junit-jupiter-api:6.0.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:6.0.0")
    testImplementation("io.mockk:mockk:1.14.6")
}

// Configure test task to use JUnit 5
tasks.withType<Test> {
    useJUnitPlatform()
}

// Make sure the generated code is included in the JAR
tasks.jar {
    dependsOn("openApiGenerate")
}
