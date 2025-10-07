package plugins

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AgentFusionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.tasks.register("validateAgentReadiness") {
            group = "agent-fusion"
            doLast { println("Agent readiness validated.") }
        }
        target.tasks.register("injectMemoryGlyphs") {
            group = "agent-fusion"
            doLast { println("Memory glyphs injected.") }
        }
        target.tasks.register("triggerFusionState") {
            group = "agent-fusion"
            doLast { println("Fusion state transition triggered.") }
        }
    }
}

class AndroidHiltConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            // Apply Hilt for both Android library and application modules
            pluginManager.withPlugin("com.android.library") { applyHilt() }
            pluginManager.withPlugin("com.android.application") { applyHilt() }
        }
    }
}

private fun Project.applyHilt() {
    // Apply the necessary plugins
    with(pluginManager) {
        apply("com.google.dagger.hilt.android")
        apply("com.google.devtools.ksp")
    }

    // Access the version catalog correctly
    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

    // Add Hilt dependencies from the version catalog
    dependencies {
        "implementation"(libs.findLibrary("hilt.android").get())
        "ksp"(libs.findLibrary("hilt.compiler").get())
        "androidTestImplementation"(libs.findLibrary("hilt.android.testing").get())
        "kspAndroidTest"(libs.findLibrary("hilt.compiler").get())
    }
}
