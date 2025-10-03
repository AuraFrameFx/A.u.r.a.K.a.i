// Task to add @OptIn annotations to generated OpenAPI files
tasks.register("fixOpenApiAnnotations") {
    description = "Adds @OptIn annotations to generated OpenAPI files that use ExperimentalTime"
    group = "build"

    doLast {
        // Use configuration-cache-safe project directory reference
        val modelDir = project.layout.projectDirectory.dir("build/generated/openapi/src/main/kotlin/dev/aurakai/auraframefx/model").asFile
        val apiDir = project.layout.projectDirectory.dir("build/generated/openapi/src/main/kotlin/dev/aurakai/auraframefx/api").asFile

        // Fix model files
        if (modelDir.exists()) {
            modelDir.listFiles()?.forEach { file ->
                if (file.isFile && file.extension == "kt") {
                    var content = file.readText()
                    if (!content.contains("@file:OptIn(kotlin.time.ExperimentalTime::class)") &&
                        content.contains("kotlin.time")) {
                        content = content.replace(
                            "package dev.aurakai.auraframefx.model",
                            "@file:OptIn(kotlin.time.ExperimentalTime::class)\n\npackage dev.aurakai.auraframefx.model"
                        )
                        file.writeText(content)
                        println("Added @OptIn annotation to "+file.name)
                    }
                }
            }
        } else {
            println("Model directory not found: $modelDir")
        }

        // Fix API files
        if (apiDir.exists()) {
            apiDir.listFiles()?.forEach { file ->
                if (file.isFile && file.extension == "kt") {
                    var content = file.readText()
                    if (!content.contains("@file:OptIn(kotlin.time.ExperimentalTime::class)") &&
                        content.contains("kotlin.time")) {
                        content = content.replace(
                            "package dev.aurakai.auraframefx.api",
                            "@file:OptIn(kotlin.time.ExperimentalTime::class)\n\npackage dev.aurakai.auraframefx.api"
                        )
                        file.writeText(content)
                        println("Added @OptIn annotation to "+file.name)
                    }
                }
            }
        } else {
            println("API directory not found: $apiDir")
        }
    }
}

// Make the openApiGenerate task finalized by fixOpenApiAnnotations
tasks.findByName("openApiGenerate")?.finalizedBy("fixOpenApiAnnotations")

// Make the compileKotlin task depend on the fixOpenApiAnnotations task
tasks.findByName("compileKotlin")?.dependsOn("fixOpenApiAnnotations")
