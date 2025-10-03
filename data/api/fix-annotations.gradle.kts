import org.gradle.api.DefaultTask
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.TaskAction
import java.io.File

// Configuration cache-compatible task to fix all issues in generated OpenAPI files
abstract class FixOpenApiAnnotationsTask : DefaultTask() {
    @get:InputDirectory
    abstract val modelDir: DirectoryProperty

    @get:InputDirectory
    abstract val apiDir: DirectoryProperty

    @TaskAction
    fun fixAnnotations() {
        println("========================================")
        println("Fixing OpenAPI Generated Files")
        println("========================================")

        // Fix model files
        val modelDirFile = modelDir.get().asFile
        if (modelDirFile.exists()) {
            modelDirFile.listFiles()?.filter { it.extension == "kt" }?.forEach { file ->
                var content = file.readText()
                val originalContent = content

                // 1. Add @OptIn annotation if needed (for kotlin.time types)
                if (!content.contains("@file:OptIn(kotlin.time.ExperimentalTime::class)") &&
                    content.contains("kotlin.time")) {
                    content = content.replace(
                        "package dev.aurakai.auraframefx.model",
                        "@file:OptIn(kotlin.time.ExperimentalTime::class)\n\npackage dev.aurakai.auraframefx.model"
                    )
                }

                // 2. Replace kotlin.Any with JsonElement in Map types
                if (content.contains("Map<String, kotlin.Any>") || content.contains("Map<kotlin.String, kotlin.Any>")) {
                    if (!content.contains("import kotlinx.serialization.json.JsonElement")) {
                        content = content.replace(
                            "import kotlinx.serialization.SerialName",
                            "import kotlinx.serialization.SerialName\nimport kotlinx.serialization.json.JsonElement"
                        )
                    }
                    content = content.replace("Map<String, kotlin.Any>", "Map<String, JsonElement>")
                    content = content.replace("Map<kotlin.String, kotlin.Any>", "Map<kotlin.String, JsonElement>")
                }

                // 3. Fix java.net.URI properties - remove duplicates first, then add @Contextual
                if (content.contains(": java.net.URI")) {
                    // Clean up any existing duplicate @Contextual annotations
                    content = content.replace("@Contextual @property:Contextual @Contextual", "")
                    content = content.replace("@Contextual @Contextual", "")
                    content = content.replace("@property:Contextual", "")

                    // Add Contextual import if not present
                    if (!content.contains("import kotlinx.serialization.Contextual")) {
                        content = content.replace(
                            "import kotlinx.serialization.SerialName",
                            "import kotlinx.serialization.SerialName\nimport kotlinx.serialization.Contextual"
                        )
                    }

                    // Add @Contextual to URI properties that don't have it yet
                    content = content.replace(
                        Regex("""(\s+)@SerialName\(value = "([^"]+)"\)\s*\n(\s+val [^:]+: java\.net\.URI)"""),
                        "$1@Contextual @SerialName(value = \"$2\")\n$3"
                    )

                    // Final cleanup of any duplicates created
                    content = content.replace("@Contextual @Contextual", "@Contextual")
                }

                // Write file only if modified
                if (content != originalContent) {
                    file.writeText(content)
                    println("✓ Fixed ${file.name}")
                }
            }
        } else {
            println("⚠ Model directory not found: $modelDirFile")
        }

        // Fix API files
        val apiDirFile = apiDir.get().asFile
        if (apiDirFile.exists()) {
            apiDirFile.listFiles()?.filter { it.extension == "kt" }?.forEach { file ->
                var content = file.readText()
                val originalContent = content

                // 1. Add @OptIn annotation if needed
                if (!content.contains("@file:OptIn(kotlin.time.ExperimentalTime::class)") &&
                    content.contains("kotlin.time")) {
                    content = content.replace(
                        "package dev.aurakai.auraframefx.api",
                        "@file:OptIn(kotlin.time.ExperimentalTime::class)\n\npackage dev.aurakai.auraframefx.api"
                    )
                }

                // 2. Remove duplicate and conflicting imports
                val lines = content.split("\n")
                val seenImports = mutableSetOf<String>()
                val filteredLines = mutableListOf<String>()

                for (line in lines) {
                    val trimmed = line.trim()

                    // Skip imports with numbered suffixes (e.g., ThemeCreateRequest1, UserUpdateRequest1)
                    if (trimmed.matches(Regex("""import dev\.aurakai\.auraframefx\.model\.\w+Request\d+"""))) {
                        println("  ↳ Removed conflicting import: $trimmed from ${file.name}")
                        continue
                    }

                    // Fix incorrect model names in imports
                    var processedLine = line
                    if (trimmed == "import dev.aurakai.auraframefx.model.CreateThemeRequest") {
                        processedLine = line.replace("CreateThemeRequest", "ThemeCreateRequest")
                        println("  ↳ Fixed import: CreateThemeRequest -> ThemeCreateRequest in ${file.name}")
                    }
                    if (trimmed == "import dev.aurakai.auraframefx.model.UpdateCurrentUserRequest") {
                        processedLine = line.replace("UpdateCurrentUserRequest", "UserUpdateRequest")
                        println("  ↳ Fixed import: UpdateCurrentUserRequest -> UserUpdateRequest in ${file.name}")
                    }

                    // Deduplicate imports
                    val processedTrimmed = processedLine.trim()
                    if (processedTrimmed.startsWith("import ")) {
                        if (seenImports.add(processedTrimmed)) {
                            filteredLines.add(processedLine) // Keep first occurrence
                        } else {
                            println("  ↳ Removed duplicate import: $processedTrimmed from ${file.name}")
                        }
                    } else {
                        filteredLines.add(processedLine) // Keep all non-import lines
                    }
                }
                content = filteredLines.joinToString("\n")

                // 3. Fix any references to incorrectly named model classes in the code body
                content = content.replace(Regex("""\bCreateThemeRequest\b"""), "ThemeCreateRequest")
                content = content.replace(Regex("""\bUpdateCurrentUserRequest\b"""), "UserUpdateRequest")
                content = content.replace(Regex("""\bThemeCreateRequest1\b"""), "ThemeCreateRequest")
                content = content.replace(Regex("""\bUserUpdateRequest1\b"""), "UserUpdateRequest")

                // Write file only if modified
                if (content != originalContent) {
                    file.writeText(content)
                    println("✓ Fixed ${file.name}")
                }
            }
        } else {
            println("⚠ API directory not found: $apiDirFile")
        }

        println("========================================")
        println("OpenAPI Fix Complete")
        println("========================================")
    }
}

// Register the task
tasks.register<FixOpenApiAnnotationsTask>("fixOpenApiAnnotations") {
    description = "Fixes all issues in generated OpenAPI files (annotations, duplicates, imports)"
    group = "build"

    modelDir.set(layout.projectDirectory.dir("build/generated/openapi/src/main/kotlin/dev/aurakai/auraframefx/model"))
    apiDir.set(layout.projectDirectory.dir("build/generated/openapi/src/main/kotlin/dev/aurakai/auraframefx/api"))
}

// Make fixOpenApiAnnotations run after openApiGenerate
tasks.named("openApiGenerate").configure {
    finalizedBy("fixOpenApiAnnotations")
}

// Make compileKotlin depend on fixOpenApiAnnotations
tasks.named("compileKotlin").configure {
    dependsOn("fixOpenApiAnnotations")
}
