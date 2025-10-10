import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.Delete
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.register
import org.openapitools.generator.gradle.plugin.extensions.OpenApiGeneratorGenerateExtension

class OpenApiConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("org.openapi.generator")

            extensions.configure<OpenApiGeneratorGenerateExtension>("openApiGenerate") {
                generatorName.set("kotlin")
                inputSpec.set("$rootDir/app/api/unified-aegenesis-api.yml")
                outputDir.set(layout.buildDirectory.dir("generated/source/openapi").get().asFile.path)
                packageName.set("dev.aurakai.aegenesis.api")
                apiPackage.set("dev.aurakai.aegenesis.api")
                modelPackage.set("dev.aurakai.aegenesis.model")
                invokerPackage.set("dev.aurakai.aegenesis.client")
                skipOverwrite.set(false)
                validateSpec.set(false)
                generateApiTests.set(false)
                generateModelTests.set(false)
                generateApiDocumentation.set(true)
                generateModelDocumentation.set(true)
                configOptions.set(
                    mapOf(
                        "library" to "jvm-retrofit2",
                        "useCoroutines" to "true",
                        "serializationLibrary" to "kotlinx_serialization",
                        "dateLibrary" to "kotlinx-datetime",
                        "sourceFolder" to "src/main/kotlin",
                        "hilt" to "true",
                        "enumPropertyNaming" to "UPPERCASE",
                        "collectionType" to "list"
                    )
                )
            }

            val cleanApiGeneration = tasks.register<Delete>("cleanApiGeneration") {
                group = "build"
                description = "Clean generated API files"
                delete(layout.buildDirectory.dir("generated/source/openapi"))
            }

            tasks.named("clean") {
                dependsOn(cleanApiGeneration)
            }
        }
    }
}