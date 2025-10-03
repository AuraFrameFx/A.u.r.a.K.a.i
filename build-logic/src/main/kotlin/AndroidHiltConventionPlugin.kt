import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidHiltConventionPlugin : Plugin<Project> {
    /**
     * Applies the Android Hilt convention to the given Gradle project.
     *
     * This will:
     * - Apply the base "genesis.android.library" convention plugin if needed.
     * - Apply the Hilt Gradle plugin (com.google.dagger.hilt.android).
     * - Apply the KSP plugin for annotation processing.
     * - Add necessary Hilt dependencies (not included in this snippet but implied).
     *
     * @param target The Gradle [Project] this plugin is being applied to.
     */
    override fun apply(target: Project) {
        with(target) {
            // Use com.android.base as the trigger for applying Hilt, per Hilt plugin requirements
            plugins.withId("com.android.base") {
                pluginManager.apply("com.google.dagger.hilt.android")
                pluginManager.apply("com.google.devtools.ksp")
            }
        }
    }
}