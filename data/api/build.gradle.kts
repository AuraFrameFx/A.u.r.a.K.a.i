import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

// Apply the necessary plugins for a Kotlin/Java library.
// These provide the 'dependencies' and 'sourceSets' blocks.
plugins {
    id("org.openapi.generator") version "7.16.0"
    kotlin("jvm") // Or your project's specific Kotlin version, e.g., version "1.9.23"
    kotlin("plugin.serialization")
    `java-library`
}

afterEvaluate {
    openApiGenerate {
        generatorName = "kotlin"
        inputSpec = file("$rootDir/data/api/api/my-api-spec.yaml").toURI().toString()
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

// Add the generated sources to the project's main source set.
// This allows your IDE and the compiler to find the generated code.
sourceSets {
    named("main") {
        java {
            srcDir(layout.buildDirectory.dir("generated/openapi/src/main/kotlin"))
        }
    }
}

// Ensure the openApiGenerate task runs before any Kotlin compilation.
// This guarantees you are always compiling against the latest generated code.
tasks.withType<KotlinCompile>().configureEach {
    dependsOn(tasks.named("openApiGenerate"))
}

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
    implementation(kotlin("reflect"))

    // Ktor client dependencies
    implementation("io.ktor:ktor-client-core:2.3.10")
    implementation("io.ktor:ktor-client-cio:2.3.10")
    implementation("io.ktor:ktor-client-content-negotiation:2.3.10")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.10")

    // KotlinX dependencies
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.5.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")

    // Logging
    implementation("io.github.microutils:kotlin-logging-jvm:3.0.5")
    implementation("org.slf4j:slf4j-api:2.0.12")
    runtimeOnly("ch.qos.logback:logback-classic:1.5.19")

    // Testing dependencies
    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit5"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.2")
    testImplementation("io.mockk:mockk:1.13.10")
}

// Configure test task to use JUnit 5
tasks.withType<Test> {
    useJUnitPlatform()
}

// Make sure the generated code is included in the JAR
tasks.jar {
    dependsOn("openApiGenerate")
}
