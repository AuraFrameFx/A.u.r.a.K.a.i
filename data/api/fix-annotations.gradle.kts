import org.gradle.api.DefaultTask
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.TaskAction
import java.io.File

// Configuration cache-compatible task to add @OptIn annotations to generated OpenAPI files
abstract class FixOpenApiAnnotationsTask : DefaultTask() {
    @get:InputDirectory
    abstract val modelDir: DirectoryProperty

    @get:InputDirectory
    abstract val apiDir: DirectoryProperty

    @TaskAction
    fun fixAnnotations() {
        // Fix model files
        val modelDirFile = modelDir.get().asFile
        if (modelDirFile.exists()) {
            modelDirFile.listFiles()?.forEach { file ->
                if (file.isFile && file.extension == "kt") {
                    var content = file.readText()
                    var modified = false

                    // Add @OptIn annotation if needed
                    if (!content.contains("@file:OptIn(kotlin.time.ExperimentalTime::class)") &&
                        content.contains("kotlin.time")) {
                        content = content.replace(
                            "package dev.aurakai.auraframefx.model",
                            "@file:OptIn(kotlin.time.ExperimentalTime::class)\n\npackage dev.aurakai.auraframefx.model"
                        )
                        modified = true
                        println("Added @OptIn annotation to ${file.name}")
                    }

                    // Replace kotlin.Any with JsonElement ONLY in Map types for proper serialization
                    if (content.contains("Map<String, kotlin.Any>") || content.contains("Map<kotlin.String, kotlin.Any>")) {
                        // Add JsonElement import if not present
                        if (!content.contains("import kotlinx.serialization.json.JsonElement")) {
                            content = content.replace(
                                "import kotlinx.serialization.SerialName",
                                "import kotlinx.serialization.SerialName\nimport kotlinx.serialization.json.JsonElement"
                            )
                        }

                        // Remove @Contextual annotations that were added before
                        content = content.replace("@Contextual ", "")
                        content = content.replace("\nimport kotlinx.serialization.Contextual", "")

                        // Replace kotlin.Any with JsonElement only in Map<String, kotlin.Any>
                        content = content.replace("Map<String, kotlin.Any>", "Map<String, JsonElement>")
                        content = content.replace("Map<kotlin.String, kotlin.Any>", "Map<kotlin.String, JsonElement>")

                        modified = true
                        println("Replaced Map<String, kotlin.Any> with Map<String, JsonElement> in ${file.name}")
                    }

                    // Add @Contextual for UUID, Instant, and URI types
                    if ((content.contains(": UUID") || content.contains(": Instant") || content.contains(": URI"))
                        && !content.contains("import kotlinx.serialization.Contextual")) {
                        content = content.replace(
                            "import kotlinx.serialization.SerialName",
                            "import kotlinx.serialization.SerialName\nimport kotlinx.serialization.Contextual"
                        )
                        modified = true
                    }

                    // Add @Contextual to UUID properties
                    if (content.contains(": UUID")) {
                        content = content.replace(
                            Regex("""(\s+@SerialName\(value = "[^"]+"\)\n)(\s+val [^:]+: UUID[?\s])"""),
                            "$1    @Contextual\n$2"
                        )
                        modified = true
                        println("Added @Contextual for UUID in ${file.name}")
                    }

                    // Add @Contextual to Instant properties
                    if (content.contains(": Instant")) {
                        content = content.replace(
                            Regex("""(\s+@SerialName\(value = "[^"]+"\)\n)(\s+val [^:]+: Instant[?\s])"""),
                            "$1    @Contextual\n$2"
                        )
                        modified = true
                        println("Added @Contextual for Instant in ${file.name}")
                    }

                    // Add @Contextual to URI properties
                    if (content.contains(": URI")) {
                        content = content.replace(
                            Regex("""(\s+@SerialName\(value = "[^"]+"\)\n)(\s+val [^:]+: URI[?\s])"""),
                            "$1    @Contextual\n$2"
                        )
                        modified = true
                        println("Added @Contextual for URI in ${file.name}")
                    }

                    if (modified) {
                        file.writeText(content)
                    }
                }
            }
        } else {
            println("Model directory not found: $modelDirFile")
        }

        // Fix API files
        val apiDirFile = apiDir.get().asFile
        if (apiDirFile.exists()) {
            apiDirFile.listFiles()?.forEach { file ->
                if (file.isFile && file.extension == "kt") {
                    var content = file.readText()
                    var modified = false

                    // Add @OptIn annotation if needed
                    if (!content.contains("@file:OptIn(kotlin.time.ExperimentalTime::class)") &&
                        content.contains("kotlin.time")) {
                        content = content.replace(
                            "package dev.aurakai.auraframefx.api",
                            "@file:OptIn(kotlin.time.ExperimentalTime::class)\n\npackage dev.aurakai.auraframefx.api"
                        )
                        modified = true
                        println("Added @OptIn annotation to ${file.name}")
                    }

                    // Fix incorrect import statements with "1" suffix (OpenAPI generator bug)
                    if (content.contains("Request1")) {
                        // Remove imports with "1" suffix that are duplicates
                        content = content.replace(Regex("""import dev\.aurakai\.auraframefx\.model\.\w+Request1\n"""), "")
                        modified = true
                        println("Removed incorrect Request1 imports from ${file.name}")
                    }

                    // Fix incorrect model name references
                    content = content.replace("CreateThemeRequest", "ThemeCreateRequest")
                    content = content.replace("UpdateCurrentUserRequest", "UserUpdateRequest")

                    if (content != file.readText()) {
                        modified = true
                    }

                    // For API files, DON'T replace kotlin.Any in RequestConfig type parameters
                    // This is legitimate usage

                    if (modified) {
                        file.writeText(content)
                    }
                }
            }
        } else {
            println("API directory not found: $apiDirFile")
        }
    }
}

// Register the task with configuration cache-compatible properties
tasks.register<FixOpenApiAnnotationsTask>("fixOpenApiAnnotations") {
    description = "Adds @OptIn annotations to generated OpenAPI files that use ExperimentalTime"
    group = "build"

    modelDir.set(layout.projectDirectory.dir("build/generated/openapi/src/main/kotlin/dev/aurakai/auraframefx/model"))
    apiDir.set(layout.projectDirectory.dir("build/generated/openapi/src/main/kotlin/dev/aurakai/auraframefx/api"))
}

// Make the fixOpenApiAnnotations task run after the openApiGenerate task (if it exists)
tasks.findByName("openApiGenerate")?.let { openApiTask ->
    tasks.findByName("fixOpenApiAnnotations")?.dependsOn(openApiTask)
}

// Make the compileKotlin task depend on the fixOpenApiAnnotations task
tasks.findByName("compileKotlin")?.dependsOn("fixOpenApiAnnotations")
