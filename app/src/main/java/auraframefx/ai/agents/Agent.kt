package dev.aurakai.auraframefx.ai.agents

import dev.aurakai.auraframefx.model.AgentResponse
import dev.aurakai.auraframefx.model.AgentType
import dev.aurakai.auraframefx.model.AiRequest
import kotlinx.coroutines.flow.Flow

/**
 * Top-level value declaration for versioning or identification.
 */
const val TOPL_VL: String = "1.0.0"

/**
 * Interface representing an AI agent.
 */
interface Agent {
    /**
 * Gets the agent's display name.
 *
 * @return The agent's name, or `null` if it is not specified.
 */
    fun getName(): String?

    /**
 * Gets the agent's type or model.
 *
 * @return The agent's type or model as an AgentType.
 */
    fun getType(): AgentType

    /**
 * Processes an AI request within the provided context and produces an AgentResponse.
 *
 * @param request The AI request to process.
 * @param context Supplemental context used to influence or ground the agent's response.
 * @return The agent's response to the given request and context.
 */
    suspend fun processRequest(request: AiRequest, context: String): AgentResponse

    /**
 * Streams responses for the given AI request as they become available.
 *
 * @param request The AI request to process and stream responses for.
 * @return A Flow that emits one or more AgentResponse objects representing incremental or final responses.
 */
    fun processRequestFlow(request: AiRequest): Flow<AgentResponse>
}