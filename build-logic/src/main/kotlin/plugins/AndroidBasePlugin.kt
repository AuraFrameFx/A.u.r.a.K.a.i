package plugins

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Genesis Base Plugin - Gold Standard
 * Configuration-only: shared logic for Android modules.
 */
class AndroidBasePlugin : Plugin<Project> {
    /**
     * Applies the plugin to the given project, performing base Android module configuration.
     *
     * Currently this method does not modify the project; it serves as the centralized hook for
     * shared configuration that Android modules can rely on when the plugin is extended.
     *
     * @param target The Gradle project to configure when the plugin is applied.
     */
    override fun apply(target: Project) {
        with(target) {

        }
    }
}