// ==== GENESIS PROTOCOL - ANDROID LIBRARY CONVENTION ====
// Standard Android library configuration for all modules
// Follows best practices from AGENT_INSTRUCTIONS.md

import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.GradleException
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.Delete
import org.gradle.kotlin.dsl.configure

class AndroidLibraryConventionPlugin : Plugin<Project> {
    /**
     * Applies Android library and Kotlin Android plugins and configures project-level Android and Kotlin defaults.
     *
     * Configures the target project's Android LibraryExtension and Kotlin JVM toolchain:
     * - Sets Android compileSdk to 36 and default minSdk to 34.
     * - Sets Java sourceCompatibility and targetCompatibility to Java 24.
     * - Configures the Kotlin JVM toolchain to use Java 24 (applied only after the Kotlin Android plugin is present).
     *
     * @param target The Gradle project to configure; this method mutates the project's plugins and extensions.
     */
    override fun apply(target: Project) {
        with(target) {
            // Apply plugins in correct order (per AGENT_INSTRUCTIONS.md section 2)
            with(pluginManager) {
                apply("com.android.library")
            }

            extensions.configure<LibraryExtension> {
                compileSdk = 36

                defaultConfig {
                    minSdk = 34
                }

                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_24
                    targetCompatibility = JavaVersion.VERSION_24
                }
            }
            // Kotlin JVM toolchain (only configure after kotlin-android is applied)
            pluginManager.withPlugin("org.jetbrains.kotlin.android") {
                extensions.configure<org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension> {
                    jvmToolchain(24)
                }
            }
        }
    }
}