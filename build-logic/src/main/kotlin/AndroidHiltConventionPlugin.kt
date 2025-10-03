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
     * - Apply the base "genesis.android.library" convention plugin if needed.
     * - Apply the KSP plugin for annotation processing.
     * - Add necessary Hilt dependencies.
     *
     * Note: For AGP 9.0 compatibility, the Hilt plugin itself should be applied
     * directly in the module's build.gradle.kts file, not here.
     *
     * target The Gradle [Project] this plugin is being applied to.
     */
    override fun apply(target: Project) {
        with(target) {
            // Apply the base Android plugin if not present
            val hasApp = plugins.hasPlugin("com.android.application")
            val hasLib = plugins.hasPlugin("com.android.library")
            if (!hasApp && !hasLib) {
                pluginManager.apply("genesis.android.library")
            }

            // Apply KSP plugin for annotation processing
            pluginManager.apply("com.google.devtools.ksp")


            // Configure dependencies using hardcoded coordinates
            // Version matches the hilt-version in libs.versions.toml (2.57.2)
            dependencies {
                add("implementation", "com.google.dagger:hilt-android:2.57.2")
                add("ksp", "com.google.dagger:hilt-compiler:2.57.2")
            }
        }
    }
}
