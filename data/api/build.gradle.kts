import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.openapi.generator") version "7.16.0"
    kotlin("jvm")
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

sourceSets {
    named("main") {
        java.srcDir(layout.buildDirectory.dir("generated/openapi/ecocore/src/main/kotlin"))
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
    delete(layout.buildDirectory.dir("generated/openapi"))
}

tasks.jar {
    dependsOn(tasks.named("openApiGenerate"))
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))

    implementation("io.ktor:ktor-client-core:3.3.0")
    implementation("io.ktor:ktor-client-cio:3.3.0")
    implementation("io.ktor:ktor-client-content-negotiation:3.3.0")
    implementation("io.ktor:ktor-serialization-kotlinx-json:3.3.0")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.9.0")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.7.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")

    implementation("io.github.microutils:kotlin-logging-jvm:3.0.5")
    implementation("org.slf4j:slf4j-api:2.0.17")

    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit5"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:6.0.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:6.0.0")
    testImplementation("io.mockk:mockk:1.14.6")
}

tasks.withType<Test> {
    useJUnitPlatform()
}