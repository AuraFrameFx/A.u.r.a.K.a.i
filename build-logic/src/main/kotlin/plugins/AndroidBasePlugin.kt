package plugins

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Genesis Base Plugin - Gold Standard
 * Applies Android, Kotlin, Hilt, and KSP plugins in the correct order for robust DI and Compose support.
 */
class AndroidBasePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target.pluginManager) {
            // 1. Apply Android plugin first (application or library as needed)
            apply("com.android.application")
            // 2. Apply Kotlin Android plugin
            // 3. Apply Hilt for dependency injection
            apply("com.google.dagger.hilt.android")
            // 4. Apply KSP for annotation processing
            apply("com.google.devtools.ksp")
        }
    }
}
