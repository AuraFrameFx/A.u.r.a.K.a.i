import kotlin.time.ExperimentalTime
import java.io.File

// List of model files that need to be fixed
val modelFiles = listOf(
    "AgentMessage.kt",
    "AgentResponse.kt",
    "ApiKey.kt",
    "AuditLogEntry.kt",
    "ConferenceRoom.kt",
    "Conversation.kt",
    "CreateApiKeyRequest.kt",
    "ErrorError.kt",
    "GenerateAudioResponse.kt",
    "GenerateImageResponse.kt",
    "GenerateMultimodalResponse.kt",
    "GenerateMultimodalResponseComponentsValue.kt",
    "GenerateTextResponse.kt",
    "GenerateVideoResponse.kt",
    "GetConferenceRoomSummary200Response.kt",
    "GetConferenceRoomSummary200ResponseActionItemsInner.kt",
    "ListThemeVersions200ResponseDataInner.kt",
    "OracleFile.kt",
    "Plugin.kt",
    "PreviewResponse.kt",
    "RGSSGate.kt",
    "ReceiveSystemUpdateWebhookRequest.kt",
    "SendConferenceRoomMessage200Response.kt",
    "Session.kt",
    "TaskScheduleRequestSchedule.kt",
    "TaskStatus.kt",
    "Theme.kt",
    "User.kt"
)

// Directory containing model files
val baseDir = "C:/ReGenesis-A.O.S.P/data/api/build/generated/openapi/src/main/kotlin/dev/aurakai/auraframefx/model"

var fixedCount = 0

// Process each file
modelFiles.forEach { fileName ->
    val file = File("$baseDir/$fileName")
    if (file.exists()) {
        val content = file.readText()
        if (!content.contains("@file:OptIn(kotlin.time.ExperimentalTime::class)")) {
            val updatedContent = content.replace(
                "package dev.aurakai.auraframefx.model",
                "@file:OptIn(kotlin.time.ExperimentalTime::class)\n\npackage dev.aurakai.auraframefx.model"
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

println("Fixed $fixedCount model files")
