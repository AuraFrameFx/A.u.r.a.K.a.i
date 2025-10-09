package plugins

import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidHiltConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.withPlugin("com.android.library") { buildFile }
            pluginManager.withPlugin("com.android.application") { getPlugins() }
        }
    }
}

