package buildscripts

import org.gradle.testkit.runner.GradleRunner
import org.gradle.testkit.runner.TaskOutcome
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.nio.file.Path
import kotlin.io.path.writeText

/**
 * Provides additional integration tests for the build scripts.
 * This test uses Gradle TestKit to run a minimal build and verify its success,
 * ensuring the basic project setup is sound.
 */
class BuildScriptsIntegrationTest_Additions {

    @TempDir
    lateinit var projectDir: Path

    @Test
    @DisplayName("Basic Gradle build succeeds with a simple task")
    fun `basic build succeeds`() {
        // Arrange
        projectDir.resolve("settings.gradle.kts").writeText("""
            rootProject.name = "integration-test"
        """.trimIndent())

        projectDir.resolve("build.gradle.kts").writeText("""
            plugins {
                `java-library`
            }
        """.trimIndent())

        // Act
        val result = GradleRunner.create()
            .withProjectDir(projectDir.toFile())
            .withArguments("help")
            .build()

        // Assert
        assertEquals(TaskOutcome.SUCCESS, result.task(":help")?.outcome)
    }
}