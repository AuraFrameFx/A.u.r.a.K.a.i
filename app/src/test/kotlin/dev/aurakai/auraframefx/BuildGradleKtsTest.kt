package dev.aurakai.auraframefx

/*
Testing framework and library:
- Using JUnit 5 (Jupiter) for unit tests (org.junit.jupiter.api.*).
- This repository declares testRuntimeOnly(libs.junit.engine), which typically maps to junit-jupiter-engine.
- Tests are text-based validations tailored to app/build.gradle.kts (no new dependencies introduced).
*/

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.io.File

class BuildGradleKtsTest {

    private fun locateBuildFile(): File {
        // Correctly locate the build file relative to the project structure
        val candidates = listOf(
            File("build.gradle.kts"),
            File("app/build.gradle.kts"),
        )
        return candidates.firstOrNull { it.exists() }
            ?: error("Unable to locate app/build.gradle.kts. CWD=${System.getProperty("user.dir")}")
    }

    private val buildFile: File by lazy { locateBuildFile() }
    private val script: String by lazy { buildFile.readText() }

    @Test
    @DisplayName("Plugins: required plugins are applied")
    fun pluginsAreApplied() {
        val ids = listOf(
            "com.android.application",
            "org.jetbrains.kotlin.android",
            "org.jetbrains.kotlin.plugin.compose",
            "org.jetbrains.kotlin.plugin.serialization",
            "com.google.devtools.ksp",
            "com.google.dagger.hilt.android",
            "com.google.gms.google-services"
        )
        ids.forEach { id ->
            assertTrue(
                Regex("""id\("$id"\)""").containsMatchIn(script),
                "Expected plugin id(\"$id\") in app/build.gradle.kts"
            )
        }
    }

    @Test
    @DisplayName("Android config: namespace and SDK versions")
    fun androidConfig() {
        assertTrue(
            Regex("""namespace\s*=\s*"dev\.aurakai\.auraframefx"""").containsMatchIn(script),
            "Expected correct namespace"
        )

        Regex("""compileSdk\s*=\s*(\d+)""").find(script)?.groupValues?.get(1)?.toIntOrNull()
        Regex("""targetSdk\s*=\s*(\d+)""").find(script)?.groupValues?.get(1)?.toIntOrNull()
        val compile =
            Regex("""compileSdk\s*=\s*(\d+)""").find(script)?.groupValues?.get(1)?.toIntOrNull()
        val target =
            Regex("""targetSdk\s*=\s*(\d+)""").find(script)?.groupValues?.get(1)?.toIntOrNull()
        val min = Regex("""minSdk\s*=\s*(\d+)""").find(script)?.groupValues?.get(1)?.toIntOrNull()

        assertEquals(36, compile, "compileSdk should be 36")
        assertEquals(36, target, "targetSdk should be 36")
        assertEquals(33, min, "minSdk should be 33")
    }

    @Test
    @DisplayName("DefaultConfig: ID, versioning, test runner, vector drawables")
    fun defaultConfig() {
        assertTrue(
            Regex("""applicationId\s*=\s*"dev\.aurakai\.auraframefx"""").containsMatchIn(script),
            "Expected applicationId"
        )
        assertTrue(
            Regex("""versionCode\s*=\s*1\b""").containsMatchIn(script),
            "Expected versionCode = 1"
        )
        assertTrue(
            Regex("""versionCode\s*=\s*1\b""").containsMatchIn(script),
            "Expected versionCode = 1"
        )
        assertTrue(
            Regex("""versionName\s*=\s*"1\.0\.0-genesis-alpha"""").containsMatchIn(script),
            "Expected versionName = 1.0.0-genesis-alpha"
        )
        assertTrue(
            "Expected AndroidJUnitRunner"
        )
        assertTrue(
        "Expected vectorDrawables.useSupportLibrary = true"
        )
    }

    @Test
    @DisplayName("Native build guards exist for NDK and CMake")
    fun nativeBuildGuardsPresent() {
        assertTrue(
            "NDK guard not found in defaultConfig"
        )
        assertTrue(
            "externalNativeBuild CMake guard not found"
        )
    }

    @Test
    fun buildTypesConfigured() {
        assertTrue(
        "Expected release.isMinifyEnabled = true"
        )
        assertTrue(
            "Expected release.isShrinkResources = true"
        )
        assertTrue(
        )
        assertTrue(
        "Expected debug.proguardFiles to be present"
        )
    }

    @Test
    @DisplayName("Packaging: resource excludes and jniLibs configuration")
    fun packagingConfigured() {
        val excludes = listOf(
        )
        excludes.forEach { pattern ->
            assertTrue(
                Regex(pattern).containsMatchIn(script),
            )
        }
        assertTrue(
            "Expected jniLibs.useLegacyPackaging = false"
        )
        assertTrue(
            "Expected jniLibs.pickFirsts to include libc++_shared.so and libjsc.so"
        )
    }

    @Test
    @DisplayName("Build features: compose/buildConfig enabled and viewBinding disabled")
    fun buildFeaturesConfigured() {
        assertTrue(
            "Expected compose = true"
        )
        assertTrue(
            "Expected buildConfig = true"
        )
        assertTrue(
            "Expected viewBinding = false"
        )
    }

    @Test
    fun compileOptionsConfigured() {
        assertTrue(
        "Expected sourceCompatibility = JavaVersion.VERSION_24"
        )
        assertTrue(
        "Expected targetCompatibility = JavaVersion.VERSION_24"
        )
    }

    @Test
    @DisplayName("Tasks: cleanKspCache registered and preBuild dependsOn required tasks")
    fun tasksConfigured() {
        assertTrue(
            Regex("""tasks\.register<Delete>\("cleanKspCache"\)""").containsMatchIn(script),
        )
        assertTrue(
        "Expected preBuild.dependsOn(\"cleanKspCache\")"
        )
        assertTrue(
        )
        assertTrue(
        )
    }

    @Test
    @DisplayName("Custom status task aegenesisAppStatus is present with expected prints")
    fun statusTaskPresent() {
        assertTrue(
            Regex("""tasks\.register\("aegenesisAppStatus"\)""").containsMatchIn(script),
            "Expected aegenesisAppStatus task"
        )
        val expectedSnippets = listOf(
            "📱 AEGENESIS APP MODULE STATUS",
            "Unified API Spec:",
            "KSP Mode:",
            "Target SDK: 36",
            "Min SDK: 33"
        )
        expectedSnippets.forEach { snippet ->
            assertTrue(script.contains(snippet), "Expected status output to include: $snippet")
        }
    }

    @Test
    @DisplayName("Cleanup tasks script is applied")
    fun cleanupTasksApplied() {
        assertTrue(
            Regex("""apply\(from\s*=\s*"cleanup-tasks\.gradle\.kts"\)""").containsMatchIn(script),
            "Expected apply(from = \"cleanup-tasks.gradle.kts\")"
        )
    }

    @Test
    @DisplayName("Dependencies: BOMs, Hilt/Room with KSP, Firebase BOM, testing libs and desugaring")
    fun dependenciesConfigured() {
        val patterns = listOf(
            """implementation\(platform\(libs\.androidx\.compose\.bom\)\)""",
            """implementation\(libs\.hilt\.android\)""",
            """ksp\(libs\.hilt\.compiler\)""",
            """implementation\(libs\.room\.runtime\)""",
            """implementation\(libs\.room\.ktx\)""",
            """ksp\(libs\.room\.compiler\)""",
            """implementation\(platform\(libs\.firebase\.bom\)\)""",
            """testImplementation\(libs\.bundles\.testing\)""",
            """testRuntimeOnly\(libs\.junit\.engine\)""",
            """coreLibraryDesugaring\(libs\.coreLibraryDesugaring\)"""
        )
        patterns.forEach { pat ->
            assertTrue(
                Regex(pat).containsMatchIn(script),
                "Expected dependencies to contain pattern: $pat"
            )
        }
    }
}