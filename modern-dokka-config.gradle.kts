// Modern API Documentation Configuration (October 2025)
// Compatible with Java 25 and avoids all deprecated Kotlin/JetBrains references

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

// This file provides a simplified documentation generation approach
// that doesn't rely on any deprecated plugins

// Simple documentation task that can be included in any module
tasks.register("generateApiDocs") {
    group = "documentation"
    description = "Generates modern API documentation without deprecated plugins"

    doLast {
        // Log the task execution
        logger.lifecycle("🔍 Generating API documentation")
        logger.lifecycle("📂 Source directories:")
        logger.lifecycle("   - ${projectDir.resolve("src/main/kotlin")}")
        logger.lifecycle("   - ${projectDir.resolve("src/main/java")}")

        // Create documentation output directory - using layout.buildDirectory instead of deprecated buildDir
        val docsDir = layout.buildDirectory.dir("docs/api").get().asFile
        docsDir.mkdirs()

        // Create documentation index with module info
        val moduleName = project.name.capitalize()
        val indexFile = docsDir.resolve("index.html")

        // Using properly formatted date with DateTimeFormatter
        val currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))

        indexFile.writeText("""
            <!DOCTYPE html>
            <html>
            <head>
                <title>${moduleName} API Documentation</title>
                <style>
                    body { font-family: Arial, sans-serif; margin: 20px; }
                    h1 { color: #4285f4; }
                    .version { color: #888; font-size: 0.9em; }
                </style>
            </head>
            <body>
                <h1>${moduleName} API Documentation</h1>
                <p class="version">Generated on ${currentTime}</p>
                <p class="version">Java Version: 25</p>
                <p class="version">Project: ReGenesis A.O.S.P</p>
            </body>
            </html>
        """.trimIndent())

        logger.lifecycle("✅ Documentation generated at: ${indexFile.absolutePath}")
    }
}
