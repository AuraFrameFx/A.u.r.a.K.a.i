// ==== GENESIS PROTOCOL - ANDROID APPLICATION CONVENTION ====
// Standard Android application configuration
// Follows best practices from AGENT_INSTRUCTIONS.md

import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.GradleException
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.tasks.Delete
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin : Plugin<Project> {
    /**
     * Applies Android application and Kotlin plugins and configures project-level settings.
     *
     * Configures the Android ApplicationExtension and KotlinAndroidProjectExtension:
     * - Applies plugins in correct order (per AGENT_INSTRUCTIONS.md section 2)
     * - Sets Android compileSdk to 36 and targetSdk/minSdk to 36/34
     * - Configures Java 24 compatibility and toolchain
     * - Sets up proper error handling with clear messages
     *
     * @param target The Gradle project to which the plugin is applied
     */
    override fun apply(target: Project) {
        with(target) {
            // Apply plugins in correct order (per AGENT_INSTRUCTIONS.md section 2)
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.plugin.compose")
            }

            // Wait for Android plugin to be fully ready before configuring
            // (per AGENT_INSTRUCTIONS.md section 2)
            pluginManager.withPlugin("com.android.application") {
                try {
                    extensions.configure<ApplicationExtension> {
                        compileSdk = 36

                        defaultConfig {
                            targetSdk = 36
                            minSdk = 34

                            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

                            vectorDrawables {
                                useSupportLibrary = true
                            }
                        }

                        buildTypes {
                            release {
                                isMinifyEnabled = true
                                isShrinkResources = true
                                proguardFiles(
                                    getDefaultProguardFile("proguard-android-optimize.txt"),
                                    "proguard-rules.pro"
                                )
                            }
                        }

                        buildFeatures {
                            compose = true
                            buildConfig = true
                            viewBinding = false
                            dataBinding = false
                        }

                        compileOptions {
                            sourceCompatibility = JavaVersion.VERSION_17
                            targetCompatibility = JavaVersion.VERSION_17
                            isCoreLibraryDesugaringEnabled = true
                        }

                        packaging {
                            resources {
                                excludes += setOf(
                                    "/META-INF/{AL2.0,LGPL2.1}",
                                    "/META-INF/AL2.0",
                                    "/META-INF/LGPL2.1",
                                    "/META-INF/DEPENDENCIES",
                                    "/META-INF/LICENSE",
                                    "/META-INF/LICENSE.txt",
                                    "/META-INF/NOTICE",
                                    "/META-INF/NOTICE.txt",
                                    "META-INF/*.kotlin_module",
                                    "**/kotlin/**",
                                    "**/*.txt"
                                )
                            }
                            jniLibs {
                                useLegacyPackaging = false
                                pickFirsts += listOf("**/libc++_shared.so", "**/libjsc.so")
                            }
                        }

                        // Lint configuration (per AGENT_INSTRUCTIONS.md section 6)
                        lint {
                            warningsAsErrors = false
                            abortOnError = false
                            disable.addAll(listOf("InvalidPackage", "OldTargetApi"))
                        }
                    }

                    extensions.configure<JavaPluginExtension>("java") {
                        sourceCompatibility = JavaVersion.VERSION_17
                        targetCompatibility = JavaVersion.VERSION_17
                    }

                    // Clean tasks for app module (per AGENT_INSTRUCTIONS.md section 4)
                    tasks.register("cleanKspCache", Delete::class.java) {
                        group = "build setup"
                        description = "Clean KSP caches (fixes NullPointerException)"
                        delete(
                            layout.buildDirectory.dir("generated/ksp"),
                            layout.buildDirectory.dir("tmp/kapt3"),
                            layout.buildDirectory.dir("tmp/kotlin-classes"),
                            layout.buildDirectory.dir("kotlin"),
                            layout.buildDirectory.dir("generated/source/ksp")
                        )
                    }

                    // Wire clean task to run before build (per AGENT_INSTRUCTIONS.md section 4)
                    tasks.named("preBuild") {
                        dependsOn("cleanKspCache")
                    }
                } catch (e: Exception) {
                    throw GradleException(
                        """
                        ERROR: AndroidApplicationConventionPlugin configuration failed
                        Project: ${project.path}
                        Expected: Android application plugin applied before configuring android { } block
                        Actual: ${e.message}
                        Action: Ensure 'com.android.application' plugin is available
                        Documentation: See AGENT_INSTRUCTIONS.md section 2
                        """.trimIndent(),
                        e
                    )
                }
            }

            // Kotlin JVM toolchain (only configure after kotlin-android is applied)
            // (per AGENT_INSTRUCTIONS.md section 3)
            pluginManager.withPlugin("org.jetbrains.kotlin.android") {
                try {
                    extensions.configure<org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension> {
                        jvmToolchain(17)
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
        }
    }
}