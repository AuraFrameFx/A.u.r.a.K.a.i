import kotlin.time.ExperimentalTime
import java.io.File

// List of API files that need to be fixed
val apiFiles = listOf(
    "ConferenceRoomApi.kt",
    "TasksApi.kt"
)

// Directory containing API files
val baseDir = "C:/ReGenesis-A.O.S.P/data/api/build/generated/openapi/src/main/kotlin/dev/aurakai/auraframefx/api"

var fixedCount = 0

// Process each file
apiFiles.forEach { fileName ->
    val file = File("$baseDir/$fileName")
    if (file.exists()) {
        val content = file.readText()
        if (!content.contains("@file:OptIn(kotlin.time.ExperimentalTime::class)")) {
            val updatedContent = content.replace(
                "package dev.aurakai.auraframefx.api",
                "@file:OptIn(kotlin.time.ExperimentalTime::class)\n\npackage dev.aurakai.auraframefx.api"
            )
            file.writeText(updatedContent)
            fixedCount++
            println("Added @OptIn annotation to $fileName")
        } else {
            println("$fileName already has the @OptIn annotation")
        }
    } else {
        println("File not found: $fileName")
    }
}

println("Fixed $fixedCount API files")
