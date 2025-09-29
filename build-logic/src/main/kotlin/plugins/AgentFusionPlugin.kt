package plugins

import org.gradle.api.Plugin
import org.gradle.api.Project

class AgentFusionPlugin : Plugin<Project> {
    /**
     * Configures the given project when the plugin is applied.
     *
     * Registers three tasks grouped under "agent-fusion" ("validateAgentReadiness", "injectMemoryGlyphs",
     * and "triggerFusionState") and sets the project's extra property `id` to "plugins.agent-fusion".
     *
     * @param target The Gradle project to configure.
     */
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
        target.extensions.extraProperties["id"] = "plugins.agent-fusion"
    }
}
