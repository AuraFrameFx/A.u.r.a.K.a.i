package plugins

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * DEPRECATED: Hilt is now applied via genesis.android.base
 * This plugin is kept for backwards compatibility but does nothing.
 */
class AndroidHiltConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        // No-op: Hilt is now applied by AndroidBasePlugin
    }
}

