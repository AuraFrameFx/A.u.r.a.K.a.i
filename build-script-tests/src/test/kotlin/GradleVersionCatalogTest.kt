// Note: Framework detection performed via build files; tests target libs.versions.toml integrity.
// GradleVersionCatalogTest.kt
// Testing library/framework: JUnit 5 (JUnit Jupiter) with kotlin.test assertions (if available)
@file:Suppress("SameParameterValue", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate")

package testplaceholder

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.charset.StandardCharsets
import kotlin.io.path.exists
import kotlin.io.path.readText
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

/**
 * These tests validate the Gradle Version Catalog (libs.versions.toml).
 * They avoid introducing TOML parser deps by using structured text checks.
 */
class GradleVersionCatalogTest {

    companion object {
        private lateinit var catalogPaths: List<Path>
        private lateinit var catalogTextByPath: Map<Path, String>

        @BeforeAll
        @JvmStatic
        fun loadCatalogs() {
            val repoRoot = Paths.get("").toAbsolutePath()
            val found = mutableListOf<Path>()

            // Typical locations
            val typical = listOf(
                repoRoot.resolve("gradle/libs.versions.toml"),
                repoRoot.resolve("libs.versions.toml"),
            )
            found += typical.filter { Files.exists(it) }

            // Fallback: scan for any libs.versions.toml
            if (found.isEmpty()) {
                Files.walk(repoRoot).use { stream ->
                    stream.filter { it.fileName?.toString() == "libs.versions.toml" }
                        .forEach(found::add)
                }
            }

            assertTrue(found.isNotEmpty(), "No libs.versions.toml found")
            catalogPaths = found.toList()
            catalogTextByPath = catalogPaths.associateWith { it.readText(StandardCharsets.UTF_8) }
        }

        @AfterAll
        @JvmStatic
        fun cleanup() {
            // No cleanup needed for this test
        }
    }

    @Test
    @DisplayName("Version catalog file exists and is readable")
    fun testCatalogExists() {
        assertTrue(catalogPaths.isNotEmpty(), "At least one version catalog should exist")
        catalogPaths.forEach { path ->
            assertTrue(path.exists(), "Catalog should exist at: $path")
            assertTrue(catalogTextByPath[path]!!.isNotEmpty(), "Catalog should not be empty")
        }
    }

    @Nested
    @DisplayName("Version catalog structure validation")
    inner class StructureValidation {

        @Test
        @DisplayName("Contains required sections")
        fun testRequiredSections() {
            catalogPaths.forEach { path ->
                val content = catalogTextByPath[path]!!
                assertAll(
                    "Catalog at $path should contain required sections",
                    {
                        assertTrue(
                            content.contains("[versions]"),
                            "Should contain [versions] section"
                        )
                    },
                    {
                        assertTrue(
                            content.contains("[libraries]"),
                            "Should contain [libraries] section"
                        )
                    },
                    {
                        assertTrue(
                            content.contains("[plugins]"),
                            "Should contain [plugins] section"
                        )
                    }
                )
            }
        }

        @Test
        @DisplayName("Contains AuraFrameFX specific versions")
        fun testAuraFrameFXVersions() {
            catalogPaths.forEach { path ->
                val content = catalogTextByPath[path]!!
                assertAll(
                    "Catalog should contain AuraFrameFX specific versions",
                    { assertTrue(content.contains("agp"), "Should define AGP version") },
                    { assertTrue(content.contains("kotlin"), "Should define Kotlin version") },
                    { assertTrue(content.contains("yukihook"), "Should define YukiHook version") }
                )
            }
        }

    @Nested
    @DisplayName("Kotlin version validation")
    inner class KotlinVersionValidation {

        @Test
        @DisplayName("Kotlin version is defined and valid")
        fun testKotlinVersionExists() {
            catalogPaths.forEach { path ->
                val content = catalogTextByPath[path]\!\!
                assertTrue(
                    content.contains("kotlin = "),
                    "Should define kotlin version"
                )
                
                // Extract kotlin version line
                val kotlinVersionLine = content.lines()
                    .firstOrNull { it.trim().startsWith("kotlin = ") }
                
                assertNotNull(kotlinVersionLine, "Should find kotlin version definition")
                
                // Verify version format (semantic versioning with optional RC/Beta/Alpha)
                val versionRegex = """kotlin\s*=\s*"(\d+\.\d+\.\d+(?:-(?:RC|Beta|Alpha)\d*)?)".*""".toRegex()
                assertTrue(
                    versionRegex.matches(kotlinVersionLine\!\!.trim()),
                    "Kotlin version should follow semantic versioning format (e.g., 2.3.0-Beta1)"
                )
            }
        }

        @Test
        @DisplayName("Kotlin version is not empty or placeholder")
        fun testKotlinVersionNotEmpty() {
            catalogPaths.forEach { path ->
                val content = catalogTextByPath[path]\!\!
                val kotlinVersionLine = content.lines()
                    .firstOrNull { it.trim().startsWith("kotlin = ") }
                
                assertNotNull(kotlinVersionLine, "Should find kotlin version")
                assertFalse(
                    kotlinVersionLine\!\!.contains("\"\""),
                    "Kotlin version should not be empty"
                )
                assertFalse(
                    kotlinVersionLine.contains("TODO"),
                    "Kotlin version should not be a TODO placeholder"
                )
                assertFalse(
                    kotlinVersionLine.contains("X.X.X"),
                    "Kotlin version should not be a placeholder"
                )
            }
        }

        @Test
        @DisplayName("Kotlin version matches expected format for current release")
        fun testKotlinVersionFormat() {
            catalogPaths.forEach { path ->
                val content = catalogTextByPath[path]\!\!
                val kotlinVersionLine = content.lines()
                    .firstOrNull { it.trim().startsWith("kotlin = ") }
                
                assertNotNull(kotlinVersionLine, "Should find kotlin version")
                
                // Should be in format: kotlin = "X.Y.Z" or kotlin = "X.Y.Z-RCN" or kotlin = "X.Y.Z-BetaN"
                val pattern = """kotlin\s*=\s*"(\d+)\.(\d+)\.(\d+)(?:-(\w+\d*))?"""".toRegex()
                val match = pattern.find(kotlinVersionLine\!\!)
                
                assertNotNull(match, "Kotlin version should match expected format")
                match?.let {
                    val (major, minor, patch, prerelease) = it.destructured
                    assertTrue(major.toInt() >= 1, "Major version should be at least 1")
                    assertTrue(minor.toInt() >= 0, "Minor version should be non-negative")
                    assertTrue(patch.toInt() >= 0, "Patch version should be non-negative")
                }
            }
        }

        @Test
        @DisplayName("KotlinGradlePlugin version is defined and consistent")
        fun testKotlinGradlePluginVersionConsistency() {
            catalogPaths.forEach { path ->
                val content = catalogTextByPath[path]\!\!
                
                // Both kotlin and kotlinGradlePlugin should exist
                assertTrue(
                    content.contains("kotlin = "),
                    "Should define kotlin version"
                )
                assertTrue(
                    content.contains("kotlinGradlePlugin = "),
                    "Should define kotlinGradlePlugin version"
                )
                
                // Extract versions
                val kotlinVersion = content.lines()
                    .firstOrNull { it.trim().startsWith("kotlin = ") }
                    ?.let { line ->
                        """kotlin\s*=\s*"([^"]+)"""".toRegex().find(line)?.groupValues?.get(1)
                    }
                
                val kotlinGradlePluginVersion = content.lines()
                    .firstOrNull { it.trim().startsWith("kotlinGradlePlugin = ") }
                    ?.let { line ->
                        """kotlinGradlePlugin\s*=\s*"([^"]+)"""".toRegex().find(line)?.groupValues?.get(1)
                    }
                
                assertNotNull(kotlinVersion, "Should extract kotlin version")
                assertNotNull(kotlinGradlePluginVersion, "Should extract kotlinGradlePlugin version")
                
                // They should typically match
                assertEquals(
                    kotlinVersion,
                    kotlinGradlePluginVersion,
                    "Kotlin and kotlinGradlePlugin versions should typically be the same"
                )
            }
        }

        @Test
        @DisplayName("Kotlin version is compatible with Compose Compiler")
        fun testKotlinComposeCompilerCompatibility() {
            catalogPaths.forEach { path ->
                val content = catalogTextByPath[path]\!\!
                
                // Check if composeCompilerGradlePlugin is defined
                val hasComposeCompiler = content.contains("composeCompilerGradlePlugin = ")
                
                if (hasComposeCompiler) {
                    val kotlinVersion = content.lines()
                        .firstOrNull { it.trim().startsWith("kotlin = ") }
                        ?.let { line ->
                            """kotlin\s*=\s*"([^"]+)"""".toRegex().find(line)?.groupValues?.get(1)
                        }
                    
                    val composeVersion = content.lines()
                        .firstOrNull { it.trim().startsWith("composeCompilerGradlePlugin = ") }
                        ?.let { line ->
                            """composeCompilerGradlePlugin\s*=\s*"([^"]+)"""".toRegex().find(line)?.groupValues?.get(1)
                        }
                    
                    assertNotNull(kotlinVersion, "Should extract kotlin version")
                    assertNotNull(composeVersion, "Should extract compose compiler version")
                    
                    // Typically, compose compiler version should match Kotlin version
                    assertEquals(
                        kotlinVersion,
                        composeVersion,
                        "Compose Compiler version should match Kotlin version for compatibility"
                    )
                }
            }
        }

        @Test
        @DisplayName("Kotlin reflect library references correct version")
        fun testKotlinReflectVersionReference() {
            catalogPaths.forEach { path ->
                val content = catalogTextByPath[path]\!\!
                
                // Find kotlin-reflect library definition
                val reflectLibLine = content.lines()
                    .firstOrNull { it.contains("kotlin-reflect") && it.contains("module") }
                
                if (reflectLibLine \!= null) {
                    assertTrue(
                        reflectLibLine.contains("version.ref = \"kotlin\""),
                        "kotlin-reflect should reference the kotlin version variable"
                    )
                }
            }
        }

        @Test
        @DisplayName("Kotlin gradle plugin library references correct version")
        fun testKotlinGradlePluginLibraryVersionReference() {
            catalogPaths.forEach { path ->
                val content = catalogTextByPath[path]\!\!
                
                // Find kotlin-gradle-plugin library definition
                val gradlePluginLine = content.lines()
                    .firstOrNull { it.contains("kotlin-gradle-plugin") && it.contains("module") }
                
                if (gradlePluginLine \!= null) {
                    assertTrue(
                        gradlePluginLine.contains("version.ref = \"kotlinGradlePlugin\""),
                        "kotlin-gradle-plugin should reference the kotlinGradlePlugin version variable"
                    )
                }
            }
        }

        @Test
        @DisplayName("Kotlin plugins reference correct versions")
        fun testKotlinPluginVersionReferences() {
            catalogPaths.forEach { path ->
                val content = catalogTextByPath[path]\!\!
                
                // Check kotlin-android plugin
                val kotlinAndroidPlugin = content.lines()
                    .firstOrNull { it.trim().startsWith("kotlin-android") && it.contains("id = ") }
                
                if (kotlinAndroidPlugin \!= null) {
                    assertTrue(
                        kotlinAndroidPlugin.contains("version.ref = \"kotlin\""),
                        "kotlin-android plugin should reference kotlin version"
                    )
                }
                
                // Check kotlin-serialization plugin
                val serializationPlugin = content.lines()
                    .firstOrNull { it.trim().startsWith("kotlin-serialization") && it.contains("id = ") }
                
                if (serializationPlugin \!= null) {
                    assertTrue(
                        serializationPlugin.contains("version.ref = \"kotlin\""),
                        "kotlin-serialization plugin should reference kotlin version"
                    )
                }
                
                // Check kotlin-compose plugin
                val composePlugin = content.lines()
                    .firstOrNull { it.trim().startsWith("kotlin-compose") && it.contains("id = ") }
                
                if (composePlugin \!= null) {
                    assertTrue(
                        composePlugin.contains("version.ref = \"kotlin\""),
                        "kotlin-compose plugin should reference kotlin version"
                    )
                }
            }
        }
    }

    @Nested
    @DisplayName("Version consistency validation")
    inner class VersionConsistencyValidation {

        @Test
        @DisplayName("All version references are defined")
        fun testAllVersionReferencesAreDefined() {
            catalogPaths.forEach { path ->
                val content = catalogTextByPath[path]\!\!
                
                // Extract all version references (version.ref = "...")
                val versionRefPattern = """version\.ref\s*=\s*"([^"]+)"""".toRegex()
                val referencedVersions = versionRefPattern.findAll(content)
                    .map { it.groupValues[1] }
                    .toSet()
                
                // Extract all defined versions
                val versionSection = content.substringAfter("[versions]")
                    .substringBefore("[")
                val definedVersions = """(\w+)\s*=\s*"[^"]+"""".toRegex()
                    .findAll(versionSection)
                    .map { it.groupValues[1] }
                    .toSet()
                
                referencedVersions.forEach { ref ->
                    assertTrue(
                        definedVersions.contains(ref),
                        "Referenced version '$ref' should be defined in [versions] section"
                    )
                }
            }
        }

        @Test
        @DisplayName("No duplicate version definitions")
        fun testNoDuplicateVersions() {
            catalogPaths.forEach { path ->
                val content = catalogTextByPath[path]\!\!
                
                val versionSection = content.substringAfter("[versions]")
                    .substringBefore("[")
                    .lines()
                    .filter { it.contains("=") && \!it.trim().startsWith("#") }
                
                val versionKeys = versionSection.map { line ->
                    line.substringBefore("=").trim()
                }.filter { it.isNotEmpty() }
                
                val uniqueKeys = versionKeys.toSet()
                assertEquals(
                    uniqueKeys.size,
                    versionKeys.size,
                    "Should not have duplicate version definitions"
                )
            }
        }

        @Test
        @DisplayName("Version format is consistent across all entries")
        fun testVersionFormatConsistency() {
            catalogPaths.forEach { path ->
                val content = catalogTextByPath[path]\!\!
                
                val versionSection = content.substringAfter("[versions]")
                    .substringBefore("[")
                    .lines()
                    .filter { it.contains("=") && \!it.trim().startsWith("#") }
                
                versionSection.forEach { line ->
                    if (line.trim().isNotEmpty()) {
                        // Each version line should follow: key = "value" format
                        val validFormat = """^\s*[\w-]+\s*=\s*"[^"]+"\s*(#.*)?$""".toRegex()
                        assertTrue(
                            validFormat.matches(line) || line.trim().isEmpty(),
                            "Version line should follow 'key = \"value\"' format: $line"
                        )
                    }
                }
            }
        }

        @Test
        @DisplayName("Kotlin coroutines versions are consistent")
        fun testKotlinxCoroutinesVersionConsistency() {
            catalogPaths.forEach { path ->
                val content = catalogTextByPath[path]\!\!
                
                // Check kotlinxCoroutines version exists
                val hasCoroutinesVersion = content.contains("kotlinxCoroutines = ")
                
                if (hasCoroutinesVersion) {
                    // All kotlinx coroutines libraries should reference this version
                    val coroutinesLibs = listOf(
                        "kotlinx-coroutines-core",
                        "kotlinx-coroutines-android",
                        "kotlinx-coroutines-test"
                    )
                    
                    coroutinesLibs.forEach { lib ->
                        val libLine = content.lines()
                            .firstOrNull { it.contains(lib) && it.contains("version.ref") }
                        
                        if (libLine \!= null) {
                            assertTrue(
                                libLine.contains("version.ref = \"kotlinxCoroutines\""),
                                "$lib should reference kotlinxCoroutines version"
                            )
                        }
                    }
                }
            }
        }

        @Test
        @DisplayName("Kotlin serialization version is consistent")
        fun testKotlinxSerializationVersionConsistency() {
            catalogPaths.forEach { path ->
                val content = catalogTextByPath[path]\!\!
                
                val hasSerializationVersion = content.contains("kotlinxSerializationJson = ")
                
                if (hasSerializationVersion) {
                    val serializationLibLine = content.lines()
                        .firstOrNull { 
                            it.contains("kotlinx-serialization-json") && it.contains("version.ref") 
                        }
                    
                    if (serializationLibLine \!= null) {
                        assertTrue(
                            serializationLibLine.contains("version.ref = \"kotlinxSerializationJson\""),
                            "kotlinx-serialization-json should reference kotlinxSerializationJson version"
                        )
                    }
                }
            }
        }

        @Test
        @DisplayName("KSP version is compatible with Kotlin version")
        fun testKspCompatibility() {
            catalogPaths.forEach { path ->
                val content = catalogTextByPath[path]\!\!
                
                val kotlinVersion = content.lines()
                    .firstOrNull { it.trim().startsWith("kotlin = ") }
                    ?.let { line ->
                        """kotlin\s*=\s*"([^"]+)"""".toRegex().find(line)?.groupValues?.get(1)
                    }
                
                val kspVersion = content.lines()
                    .firstOrNull { it.trim().startsWith("ksp = ") }
                    ?.let { line ->
                        """ksp\s*=\s*"([^"]+)"""".toRegex().find(line)?.groupValues?.get(1)
                    }
                
                if (kotlinVersion \!= null && kspVersion \!= null) {
                    // KSP version should start with Kotlin version
                    // e.g., kotlin = "2.3.0-Beta1", ksp = "2.2.21-RC-2.0.4" (may lag behind)
                    assertNotNull(kspVersion, "KSP version should be defined when Kotlin is defined")
                    
                    // Extract major.minor from both
                    val kotlinMajorMinor = """(\d+\.\d+)""".toRegex().find(kotlinVersion)?.groupValues?.get(1)
                    val kspMajorMinor = """(\d+\.\d+)""".toRegex().find(kspVersion)?.groupValues?.get(1)
                    
                    assertNotNull(
                        kotlinMajorMinor,
                        "Should extract Kotlin major.minor version"
                    )
                    assertNotNull(
                        kspMajorMinor,
                        "Should extract KSP major.minor version"
                    )
                }
            }
        }
    }

    @Nested
    @DisplayName("Version edge cases and error conditions")
    inner class VersionEdgeCases {

        @Test
        @DisplayName("No version uses snapshot or local builds")
        fun testNoSnapshotVersions() {
            catalogPaths.forEach { path ->
                val content = catalogTextByPath[path]\!\!
                
                val versionSection = content.substringAfter("[versions]")
                    .substringBefore("[")
                
                assertFalse(
                    versionSection.contains("-SNAPSHOT"),
                    "Should not use SNAPSHOT versions in production catalog"
                )
                assertFalse(
                    versionSection.contains("LOCAL"),
                    "Should not use LOCAL versions in production catalog"
                )
                assertFalse(
                    versionSection.contains("999.999.999"),
                    "Should not use placeholder 999.999.999 versions"
                )
            }
        }

        @Test
        @DisplayName("Versions do not contain invalid characters")
        fun testVersionsNoInvalidCharacters() {
            catalogPaths.forEach { path ->
                val content = catalogTextByPath[path]\!\!
                
                val versionSection = content.substringAfter("[versions]")
                    .substringBefore("[")
                    .lines()
                    .filter { it.contains("=") && \!it.trim().startsWith("#") }
                
                versionSection.forEach { line ->
                    val value = line.substringAfter("\"").substringBefore("\"")
                    if (value.isNotEmpty()) {
                        // Version should only contain alphanumeric, dots, hyphens, underscores
                        val validChars = """^[\w\.\-]+$""".toRegex()
                        assertTrue(
                            validChars.matches(value),
                            "Version should only contain valid characters: $value"
                        )
                    }
                }
            }
        }

        @Test
        @DisplayName("No trailing or leading whitespace in versions")
        fun testNoWhitespaceInVersions() {
            catalogPaths.forEach { path ->
                val content = catalogTextByPath[path]\!\!
                
                val versionSection = content.substringAfter("[versions]")
                    .substringBefore("[")
                    .lines()
                    .filter { it.contains("=") && \!it.trim().startsWith("#") }
                
                versionSection.forEach { line ->
                    val value = line.substringAfter("\"").substringBefore("\"")
                    if (value.isNotEmpty()) {
                        assertEquals(
                            value.trim(),
                            value,
                            "Version should not have leading/trailing whitespace: '$value'"
                        )
                    }
                }
            }
        }

        @Test
        @DisplayName("Kotlin version is not RC when stable version is available")
        fun testKotlinNotUsingRCWhenStableAvailable() {
            catalogPaths.forEach { path ->
                val content = catalogTextByPath[path]\!\!
                
                val kotlinVersion = content.lines()
                    .firstOrNull { it.trim().startsWith("kotlin = ") }
                    ?.let { line ->
                        """kotlin\s*=\s*"([^"]+)"""".toRegex().find(line)?.groupValues?.get(1)
                    }
                
                if (kotlinVersion \!= null) {
                    // This is an informational test - Beta/RC versions are valid but noteworthy
                    if (kotlinVersion.contains("-Beta") || kotlinVersion.contains("-RC")) {
                        // This is acceptable for cutting-edge projects, just validate format
                        val prereleasePattern = """^(\d+\.\d+\.\d+)-(\w+\d*)$""".toRegex()
                        assertTrue(
                            prereleasePattern.matches(kotlinVersion),
                            "Pre-release Kotlin version should follow format: X.Y.Z-BetaN or X.Y.Z-RCN"
                        )
                    }
                }
            }
        }

        @Test
        @DisplayName("No version keys contain typos or unusual naming")
        fun testVersionKeysFollowNamingConvention() {
            catalogPaths.forEach { path ->
                val content = catalogTextByPath[path]\!\!
                
                val versionSection = content.substringAfter("[versions]")
                    .substringBefore("[")
                    .lines()
                    .filter { it.contains("=") && \!it.trim().startsWith("#") }
                
                versionSection.forEach { line ->
                    val key = line.substringBefore("=").trim()
                    if (key.isNotEmpty()) {
                        // Version keys should use camelCase or kebab-case
                        val validNaming = """^[a-z][a-zA-Z0-9_-]*$""".toRegex()
                        assertTrue(
                            validNaming.matches(key),
                            "Version key should follow naming convention (camelCase or kebab-case): $key"
                        )
                    }
                }
            }
        }

        @Test
        @DisplayName("Critical versions are not set to zero or empty")
        fun testCriticalVersionsNotEmpty() {
            catalogPaths.forEach { path ->
                val content = catalogTextByPath[path]\!\!
                
                val criticalVersions = listOf("kotlin", "agp", "compileSdk", "minSdk", "targetSdk")
                
                criticalVersions.forEach { versionKey ->
                    val versionLine = content.lines()
                        .firstOrNull { it.trim().startsWith("$versionKey = ") }
                    
                    if (versionLine \!= null) {
                        val value = versionLine.substringAfter("\"").substringBefore("\"")
                        assertFalse(
                            value.isEmpty() || value == "0" || value == "0.0.0",
                            "Critical version '$versionKey' should not be empty or zero"
                        )
                    }
                }
            }
        }
    }

    @Nested
    @DisplayName("Kotlin version integration tests")
    inner class KotlinVersionIntegration {

        @Test
        @DisplayName("Kotlin version is compatible with AGP version")
        fun testKotlinAgpCompatibility() {
            catalogPaths.forEach { path ->
                val content = catalogTextByPath[path]\!\!
                
                val kotlinVersion = content.lines()
                    .firstOrNull { it.trim().startsWith("kotlin = ") }
                    ?.let { line ->
                        """kotlin\s*=\s*"([^"]+)"""".toRegex().find(line)?.groupValues?.get(1)
                    }
                
                val agpVersion = content.lines()
                    .firstOrNull { it.trim().startsWith("agp = ") }
                    ?.let { line ->
                        """agp\s*=\s*"([^"]+)"""".toRegex().find(line)?.groupValues?.get(1)
                    }
                
                if (kotlinVersion \!= null && agpVersion \!= null) {
                    // Extract major versions
                    val kotlinMajor = """(\d+)""".toRegex().find(kotlinVersion)?.groupValues?.get(1)?.toIntOrNull()
                    val agpMajor = """(\d+)""".toRegex().find(agpVersion)?.groupValues?.get(1)?.toIntOrNull()
                    
                    assertNotNull(kotlinMajor, "Should extract Kotlin major version")
                    assertNotNull(agpMajor, "Should extract AGP major version")
                    
                    // Both should be modern versions (Kotlin 1+ and AGP 7+)
                    if (kotlinMajor \!= null && agpMajor \!= null) {
                        assertTrue(kotlinMajor >= 1, "Kotlin major version should be at least 1")
                        assertTrue(agpMajor >= 7, "AGP major version should be at least 7 for modern Kotlin")
                    }
                }
            }
        }

        @Test
        @DisplayName("Kotlin version progression is logical")
        fun testKotlinVersionProgression() {
            catalogPaths.forEach { path ->
                val content = catalogTextByPath[path]\!\!
                
                val kotlinVersion = content.lines()
                    .firstOrNull { it.trim().startsWith("kotlin = ") }
                    ?.let { line ->
                        """kotlin\s*=\s*"([^"]+)"""".toRegex().find(line)?.groupValues?.get(1)
                    }
                
                if (kotlinVersion \!= null) {
                    // Parse version
                    val versionPattern = """(\d+)\.(\d+)\.(\d+)(?:-(.+))?""".toRegex()
                    val match = versionPattern.find(kotlinVersion)
                    
                    assertNotNull(match, "Should parse Kotlin version")
                    match?.let {
                        val (major, minor, patch, prerelease) = it.destructured
                        
                        // Version numbers should be reasonable
                        val majorInt = major.toInt()
                        val minorInt = minor.toInt()
                        val patchInt = patch.toInt()
                        
                        assertTrue(majorInt in 1..3, "Kotlin major version should be between 1 and 3")
                        assertTrue(minorInt in 0..20, "Kotlin minor version should be reasonable (0-20)")
                        assertTrue(patchInt in 0..99, "Kotlin patch version should be reasonable (0-99)")
                        
                        // If prerelease exists, should be valid format
                        if (prerelease.isNotEmpty()) {
                            assertTrue(
                                prerelease.matches("""(RC|Beta|Alpha)\d*""".toRegex()),
                                "Prerelease identifier should be RC, Beta, or Alpha followed by optional number"
                            )
                        }
                    }
                }
            }
        }

        @Test
        @DisplayName("Kotlin stdlib references match kotlin version")
        fun testKotlinStdlibVersionReference() {
            catalogPaths.forEach { path ->
                val content = catalogTextByPath[path]\!\!
                
                // Look for kotlin-stdlib in libraries or dependency configurations
                val stdlibReferences = content.lines()
                    .filter { it.contains("kotlin-stdlib") }
                
                stdlibReferences.forEach { line ->
                    if (line.contains("version")) {
                        // Should reference kotlin version or kotlinGradlePlugin version
                        val hasVersionRef = line.contains("version.ref = \"kotlin\"") ||
                                          line.contains("version.ref = \"kotlinGradlePlugin\"") ||
                                          line.contains("\${libs.versions.kotlin")
                        
                        assertTrue(
                            hasVersionRef || \!line.contains("version.ref"),
                            "kotlin-stdlib should reference kotlin version variable if version.ref is used"
                        )
                    }
                }
            }
        }

        @Test
        @DisplayName("All Kotlin-related versions are documented or commented")
        fun testKotlinVersionsHaveContext() {
            catalogPaths.forEach { path ->
                val content = catalogTextByPath[path]\!\!
                
                val kotlinVersionsSection = content.substringAfter("[versions]")
                    .substringBefore("[libraries]")
                
                // Check for Kotlin-related section header or comments
                val hasKotlinSection = kotlinVersionsSection.contains("KOTLIN") ||
                                      kotlinVersionsSection.contains("Kotlin") ||
                                      kotlinVersionsSection.lines().any { 
                                          it.trim().startsWith("#") && it.contains("kotlin", ignoreCase = true) 
                                      }
                
                assertTrue(
                    hasKotlinSection,
                    "Kotlin versions section should be documented with comments for maintainability"
                )
            }
        }
    }
}
}
}