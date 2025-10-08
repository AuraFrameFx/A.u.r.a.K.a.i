import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

// Apply the necessary plugins for a Kotlin/Java library.
// These provide the 'dependencies' and 'sourceSets' blocks.
plugins {
    id("org.openapi.generator") version "7.15.0"
    kotlin("jvm") // Or your project's specific Kotlin version, e.g., version "1.9.23"
    `java-library`
}

afterEvaluate {
    openApiGenerate {
        generatorName = "kotlin"
        inputSpec = file("$rootDir/data/api/api/Eco.yml").toURI().toString()
        validateSpec = false
        outputDir = layout.buildDirectory.dir("generated/openapi").get().asFile.path
        apiPackage = "dev.aurakai.auraframefx.api"
        modelPackage = "dev.aurakai.auraframefx.model"
        configOptions = mapOf(
            "library" to "jvm-ktor",
            "serializationLibrary" to "kotlinx_serialization"
    )}
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

val openApiGeneratedDir = layout.buildDirectory.dir("generated/openapi")

// Add a rule to the 'clean' task to delete the generated directory.
// This prevents stale or old generated files from causing issues.
tasks.named<Delete>("clean") {
    delete(layout.buildDirectory.dir("generated/openapi"))
}

// Define the dependencies required by the generated Ktor client.
// This block is now available because of the `java-library` plugin.
dependencies {
    implementation("io.ktor:ktor-client-core:3.3.0")
    implementation("io.ktor:ktor-client-okhttp:3.3.0")
    implementation("io.ktor:ktor-client-content-negotiation:3.3.0")
    implementation("io.ktor:ktor-serialization-kotlinx-json:3.3.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.9.0")
}
