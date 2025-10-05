// ==== GENESIS PROTOCOL - ANDROID HILT CONVENTION ====
// Hilt dependency injection configuration for Android modules

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidHiltConventionPlugin : Plugin<Project> {
    /**
     * Applies the Android Hilt convention to the given Gradle project.
     *
     * This will:
     * - Apply the base "genesis.android.library" convention plugin.
     * - Apply the Dagger Hilt Android plugin.
     * - Apply the KSP plugin for annotation processing.
     * - Add necessary Hilt dependencies.
     *
     * target The Gradle [Project] this plugin is being applied to.
     */
    override fun apply(target: Project) {
        with(target) {
            // Ensure Android plugin is applied FIRST
            val hasApp = plugins.hasPlugin("com.android.application")
            val hasLib = plugins.hasPlugin("com.android.library")

            if (!hasApp && !hasLib) {
                pluginManager.apply("genesis.android.library")
            }

            // Apply KSP first for annotation processing
            pluginManager.apply("com.google.devtools.ksp")

            // Use pluginManager.withPlugin to ensure Android is fully configured
            // This waits for the Android plugin to be applied and configured
            val androidPluginId = if (hasApp || plugins.hasPlugin("com.android.application")) {
                "com.android.application"
            } else {
                "com.android.library"
            }

            pluginManager.withPlugin(androidPluginId) {
                // AGP 9 alpha: ensure BaseExtension shim is available for Hilt
                pluginManager.apply("com.android.base")
                // Apply Hilt after Android is ready
                pluginManager.apply("com.google.dagger.hilt.android")

                // Configure dependencies through version catalog
                val libs = extensions.getByType(org.gradle.api.artifacts.VersionCatalogsExtension::class.java).named("libs")
                dependencies {
                    add("implementation", libs.findLibrary("hilt-android").get())
                    add("ksp", libs.findLibrary("hilt-compiler").get())
                }
            }
        }
    }
}
