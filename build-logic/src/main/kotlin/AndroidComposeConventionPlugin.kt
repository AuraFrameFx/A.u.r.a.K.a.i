// ==== GENESIS PROTOCOL - ANDROID COMPOSE CONVENTION ====
// Compose-enabled Android library configuration

import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.*

class AndroidComposeConventionPlugin : Plugin<Project> {
    /**
     * Applies the Android Compose convention to the given Gradle project.
     *
     * This will:
     * - Apply the base "genesis.android.library" convention plugin.
     * - Configure the Android LibraryExtension to enable Compose build features.
     * - Configure Compose Compiler metrics and stability configuration (if plugin is applied).
     *
     * Note: Modules using this plugin must also apply the Compose Compiler plugin via:
     * alias(libs.plugins.compose.compiler)
     *
     * @param target The Gradle [Project] this plugin is being applied to.
     */
    override fun apply(target: Project) {
        with(target) {
            // Apply the base library convention first
            pluginManager.apply("genesis.android.library")

            extensions.configure<LibraryExtension> {
                buildFeatures {
                    compose = true
                }
            }

            // Configure Compose Compiler if the plugin is applied
            // The plugin must be applied separately: alias(libs.plugins.compose.compiler)
            afterEvaluate {
                extensions.findByName("composeCompiler")?.apply {
                    val reportsDir = layout.buildDirectory.dir("compose_compiler")
                    val stabilityFile = rootProject.layout.projectDirectory
                        .file("stability_config.conf")

                    // Use reflection to access properties and set their values
                    try {
                        val reportsDestProp = javaClass.getMethod("getReportsDestination")
                            .invoke(this) as org.gradle.api.file.DirectoryProperty
                        reportsDestProp.set(reportsDir)

                        val stabilityFilesProp =
                            javaClass.getMethod("getStabilityConfigurationFiles")
                                .invoke(this)
                        @Suppress("UNCHECKED_CAST")
                        (stabilityFilesProp as org.gradle.api.provider.ListProperty<Any>)
                            .set(listOf(stabilityFile))
                    } catch (e: Exception) {
                        logger.warn("Could not configure Compose Compiler: ${e.message}")
                    }
                }
            }
        }
    }
}
