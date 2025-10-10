import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.openapi.generator")
    kotlin("jvm")
    kotlin("plugin.serialization")
    `java-library`
}

val ecoSpec = file("${rootDir}/data/api/ECO.yaml")
require(ecoSpec.exists()) { "OpenAPI spec not found at: ${ecoSpec.absolutePath}" }

openApiGenerate {
    generatorName = "kotlin"
    inputSpec = ecoSpec.absolutePath.replace("\\", "/")
    validateSpec = true
    outputDir = layout.buildDirectory.dir("generated/openapi/ecoai").get().asFile.path
    apiPackage = "dev.aurakai.auraframefx.api.ecoai"
    modelPackage = "dev.aurakai.auraframefx.model.ecoai"
    invokerPackage = "dev.aurakai.auraframefx.client.ecoai"
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
    outputs.dir(layout.buildDirectory.dir("generated/openapi/ecoai"))
}

sourceSets {
    named("main") {
        java.srcDir(layout.buildDirectory.dir("generated/openapi/ecoai/src/main/kotlin"))
    }
}

// ✅ Java 24 toolchain configuration
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(24))
    }
}

// ✅ CHANGED: finalizedBy → dependsOn (this is the ONLY change)
tasks.withType<KotlinCompile>().configureEach {
    dependsOn(tasks.named("openApiGenerate"))
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_24)
        freeCompilerArgs.addAll(
            "-opt-in=kotlin.time.ExperimentalTime",
            "-Xjvm-default=all"
        )
    }
}

tasks.named<Delete>("clean") {
    delete(layout.buildDirectory.dir("generated/openapi"))
}

tasks.jar {
    dependsOn(tasks.named("openApiGenerate"))
}

// Define the dependencies required by the generated Ktor client.
dependencies {
    // Core dependencies for Kotlin
    implementation(kotlin("stdlib"))
    implementation(libs.kotlin.reflect)

    implementation(libs.ktor.client.cio)
    implementation(libs.ktor.client.auth)
    implementation(libs.ktor.client.contentnegotiation)

    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlinx.datetime)
    implementation(libs.kotlinx.coroutines.core)

    // Logging
    implementation(libs.kotlin.logging.jvm)
    implementation(libs.slf4j.api)
    runtimeOnly(libs.logback.classic)

    // Testing dependencies
    testImplementation(kotlin("test"))
    testImplementation(libs.junit.jupiter.api)
    testRuntimeOnly(libs.junit.jupiter.engine)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
}

// Configure test task to use JUnit 5
tasks.withType<Test> {
    useJUnitPlatform()
}
