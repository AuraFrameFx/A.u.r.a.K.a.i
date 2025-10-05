// OpenAPI Generator Configuration for Kotlin

tasks.named("openApiGenerate") {
    doFirst {
        // Set the library template to use Kotlin's ExperimentalTime annotation
        val configFile = file("${buildDir}/tmp/openapi/openapi-generator-config.json")
        if (!configFile.parentFile.exists()) {
            configFile.parentFile.mkdirs()
        }

        configFile.writeText("""
        {
          "additionalModelTypeAnnotations": "@OptIn(kotlin.time.ExperimentalTime::class)",
          "additionalApiTypeAnnotations": "@OptIn(kotlin.time.ExperimentalTime::class)",
          "additionalEnumTypeAnnotations": "@OptIn(kotlin.time.ExperimentalTime::class)",
          "useCoroutines": true,
          "enumUnknownDefaultCase": true,
          "annotationLibrary": "kotlin"
        }
        """)

        val task = this as org.openapitools.generator.gradle.plugin.tasks.GenerateTask
        task.inputSpec.set(project.file("api/my-api-spec.yaml").absolutePath)
        task.outputDir.set("${buildDir}/generated/openapi")
        task.generatorName.set("kotlin")
        task.configFile.set(configFile.absolutePath)
        task.packageName.set("dev.aurakai.auraframefx.api")
        task.apiPackage.set("dev.aurakai.auraframefx.api")
        task.modelPackage.set("dev.aurakai.auraframefx.model")
        task.skipValidateSpec.set(true) // Skip validation to avoid errors with OpenAPI 3.1 features

        task.configOptions.set(
            mapOf(
                "dateLibrary" to "java8",
                "serializationLibrary" to "jackson",
                "useCoroutines" to "true",
                "enumUnknownDefaultCase" to "true",
                "modelMutable" to "true",
                "generateModels" to "true",
                "generateApiTests" to "false",
                "generateApiDocumentation" to "false",
                "generateModelDocumentation" to "false",
                "hideGenerationTimestamp" to "true"
            )
        )
    }
}
