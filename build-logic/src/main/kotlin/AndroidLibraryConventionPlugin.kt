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
     * Applies Android library and Kotlin Android plugins and configures project-level Android and Kotlin settings.
     *
     * Configures the Android LibraryExtension and KotlinJvmProjectExtension for the target project:
     * - Applies the "com.android.library" plugin.
     * - Sets Android compileSdk to 36 and default minSdk to 34.
     * - Forces Java source and target compatibility to Java 24.
     * - Sets the Kotlin JVM toolchain to Java 24.
     *
     * @param target The Gradle project to which the plugin is applied; this method mutates the project's plugins and extensions.
     */
    override fun apply(target: Project) {
        with(target) {
            // Apply plugins in correct order (per AGENT_INSTRUCTIONS.md section 2)
            with(pluginManager) {
                apply("com.android.library")
            }

            // Configure extensions AFTER plugin is applied (per AGENT_INSTRUCTIONS.md section 2)
            pluginManager.withPlugin("com.android.library") {
                try {
                    extensions.configure<LibraryExtension> {
                        compileSdk = 36

                        defaultConfig {
                            minSdk = 34
                            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                            
                            vectorDrawables {
                                useSupportLibrary = true
                            }
                        }

                        compileOptions {
                            sourceCompatibility = JavaVersion.VERSION_24
                            targetCompatibility = JavaVersion.VERSION_24
                        }
                        
                        // Lint configuration (per AGENT_INSTRUCTIONS.md section 6)
                        lint {
                            warningsAsErrors = false
                            abortOnError = false
                            disable.addAll(listOf("InvalidPackage", "OldTargetApi"))
                        }
                        
                        // Release build type configuration
                        buildTypes {
                            release {
                                isMinifyEnabled = true
                                proguardFiles(
                                    getDefaultProguardFile("proguard-android-optimize.txt"),
                                    "proguard-rules.pro"
                                )
                            }
                        }
                    }
                } catch (e: Exception) {
                    throw GradleException(
                        """
                        ERROR: AndroidLibraryConventionPlugin configuration failed
                        Project: ${project.path}
                        Expected: Android library plugin applied before configuring android { } block
                        Actual: ${e.message}
                        Action: Ensure 'com.android.library' plugin is available
                        Documentation: See AGENT_INSTRUCTIONS.md section 2
                        """.trimIndent(),
                        e
                    )
                }
            }
            
            // Configure Kotlin toolchain AFTER kotlin-android plugin is applied
            // (per AGENT_INSTRUCTIONS.md section 3)
            pluginManager.withPlugin("org.jetbrains.kotlin.android") {
                try {
                    extensions.configure<org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension> {
                        jvmToolchain(24)
                    }
                } catch (e: Exception) {
                    throw GradleException(
                        """
                        ERROR: Kotlin toolchain configuration failed
                        Project: ${project.path}
                        Expected: Java 24 toolchain available
                        Actual: ${e.message}
                        Action: Ensure org.gradle.java.installations.auto-download=true in gradle.properties
                        Documentation: See AGENT_INSTRUCTIONS.md section 3
                        """.trimIndent(),
                        e
                    )
                }
            }
            
            // Register clean task for generated sources (per AGENT_INSTRUCTIONS.md section 4)
            tasks.register("cleanGeneratedSources", Delete::class.java) {
                group = "build setup"
                description = "Clean generated sources"
                delete(
                    layout.buildDirectory.dir("generated/ksp"),
                    layout.buildDirectory.dir("generated/source")
                )
            }
            
            // Wire clean task to preBuild
            tasks.named("preBuild") {
                dependsOn("cleanGeneratedSources")
            }
        }
    }
}
