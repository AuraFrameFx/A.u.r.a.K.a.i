#!/usr/bin/env kotlin

import java.io.File

// List of files that need the annotation
val files = listOf(
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

// Base directory containing the model files
val baseDir = "C:\\ReGenesis-A.O.S.P\\data\\api\\build\\generated\\openapi\\src\\main\\kotlin\\dev\\aurakai\\auraframefx\\model"

// Process each file
files.forEach { fileName ->
    val file = File("$baseDir\\$fileName")
    if (file.exists()) {
        val content = file.readText()
        if (!content.contains("@file:OptIn(kotlin.time.ExperimentalTime::class)")) {
            val updatedContent = content.replace(
                "@file:Suppress(",
                "@file:Suppress(\n    \"ArrayInDataClass\",\n    \"EnumEntryName\",\n    \"RemoveRedundantQualifierName\",\n    \"UnusedImport\"\n)\n@file:OptIn(kotlin.time.ExperimentalTime::class)\n\npackage"
            )
            file.writeText(updatedContent)
            println("Added @OptIn annotation to $fileName")
        } else {
            println("$fileName already has the @OptIn annotation")
        }
    } else {
        println("File not found: $fileName")
    }
}

println("Completed adding @OptIn annotations to all files")
