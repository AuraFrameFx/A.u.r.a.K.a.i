// ==== GENESIS PROTOCOL - ANDROID HILT CONVENTION ====
// Hilt dependency injection configuration for Android modules

import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.*

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
     * @param target The Gradle [Project] this plugin is being applied to.
     */
    override fun apply(target: Project) {
        with(target) {
            // Apply the base library convention first
            pluginManager.apply("genesis.android.library")

            // Apply Hilt-specific plugins
            with(pluginManager) {
                apply("dagger.hilt.android.plugin")
                // Note: KSP plugin has compatibility issues with Kotlin 2.2.20-RC
                // Modules using Hilt will need to apply KSP manually if needed
            }

            // Configure dependencies through version catalog
            dependencies {
                add("implementation", project.dependencies.create("com.google.dagger:hilt-android:2.51.1"))
                add("annotationProcessor", project.dependencies.create("com.google.dagger:hilt-compiler:2.51.1"))
            }
        }
    }
}
