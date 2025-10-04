package plugins

import org.gradle.api.Plugin
import org.gradle.api.Project

class AgentFusionPlugin : Plugin<Project> {
    /**
     * Applies the plugin to the given Gradle project by registering three tasks under the "agent-fusion" group.
     *
     * Registers the tasks:
     * - `validateAgentReadiness` — prints "Agent readiness validated."
     * - `injectMemoryGlyphs` — prints "Memory glyphs injected."
     * - `triggerFusionState` — prints "Fusion state transition triggered."
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
    }
}
