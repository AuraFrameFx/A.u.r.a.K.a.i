// ==== GENESIS PROTOCOL - ANDROID LIBRARY CONVENTION ====
// Standard Android library configuration for all modules
// AGP 9.0+ with built-in Kotlin support

import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

class AndroidLibraryConventionPlugin : Plugin<Project> {
    /**
     * Applies Android library plugin and configures Kotlin defaults.
     *
     * Configures the Android LibraryExtension and KotlinJvmProjectExtension for the target project:
     * - Applies the "com.android.library" plugin.
     * - Sets Android compileSdk to 36 and default minSdk to 34.
     * - Forces Java source and target compatibility to Java 25.
     * - Sets the Kotlin JVM toolchain to Java 25.
     *
     * @param target The Gradle project to which the plugin is applied; this method mutates the project's plugins and extensions.
     */
    override fun apply(target: Project) {
        with(target) {
            // Apply Android library plugin first
            pluginManager.apply("com.android.library")
            // Apply Hilt and KSP plugins after Android plugin
            pluginManager.apply("com.google.dagger.hilt.android")
            pluginManager.apply("com.google.devtools.ksp")
            // Apply base configuration (configuration-only)
            pluginManager.apply("genesis.android.base")

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

            extensions.configure<LibraryExtension> {
                compileSdk = libs.findVersion("compileSdk").get().toString().toInt()

                defaultConfig {
                    minSdk = 34
                }

                // ✅ Java 24 for consistency across all modules (Firebase requirement)
                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_24
                    targetCompatibility = JavaVersion.VERSION_24
                }
            }
            // Kotlin JVM toolchain - use 24 for stable compatibility
            pluginManager.withPlugin("org.jetbrains.kotlin.android") {
                extensions.configure<org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension> {
                    jvmToolchain(24)
                    compilerOptions {
                        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_24)
                        freeCompilerArgs.addAll(
                            "-opt-in=kotlin.RequiresOptIn",
                            "-Xjvm-default=all"
                        )
                    }
                }
            }
        }
    }
}