import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.openapi.generator") version "7.16.0"
    kotlin("jvm")
    kotlin("plugin.serialization")
    `java-library`
}

val specPath = file("my-api-spec-FULL.yaml")

require(specPath.exists()) {
    "OpenAPI spec not found at: ${specPath.absolutePath}"
}

openApiGenerate {
    generatorName = "kotlin"
    inputSpec = specPath.absolutePath
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
        "generateModelDocumentation" to "false",
        "nonPublicApi" to "false",
        "hideGenerationTimestamp" to "true"
    )
}

sourceSets {
    named("main") {
        java.srcDir(layout.buildDirectory.dir("generated/openapi/src/main/kotlin"))
    }
}

tasks.withType<KotlinCompile>().configureEach {
    dependsOn(tasks.named("openApiGenerate"))
}

tasks.named("clean") {
    doLast {
        delete(layout.buildDirectory.dir("generated/openapi"))
    }
}

tasks.jar {
    dependsOn(tasks.named("openApiGenerate"))
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))
    
    implementation("io.ktor:ktor-client-core:2.3.10")
    implementation("io.ktor:ktor-client-cio:2.3.10")
    implementation("io.ktor:ktor-client-content-negotiation:2.3.10")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.10")
    
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.7.1-0.6.x-compat")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
    
    implementation("io.github.microutils:kotlin-logging-jvm:3.0.5")
    implementation("org.slf4j:slf4j-api:2.0.12")
    runtimeOnly("ch.qos.logback:logback-classic:1.5.3")
    
    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit5"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.2")
    testImplementation("io.mockk:mockk:1.13.10")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
