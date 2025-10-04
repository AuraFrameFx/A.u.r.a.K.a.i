package dev.aurakai.auraframefx.network.api

import dev.aurakai.auraframefx.network.model.AgentRequest
import dev.aurakai.auraframefx.network.model.AgentResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * API interface for interacting with AI agents in the Trinity system.
 */
interface AIAgentApi {
    /**
     * Retrieve the current status and metadata for a specific AI agent.
     *
     * @param agentType The agent type identifier (e.g., "genesis", "aura", "kai").
     * @return The agent's status and related information.
     */
    @GET("agent/{agentType}/status")
    suspend fun getAgentStatus(
        @Path("agentType") agentType: String,
    ): AgentResponse

    /**
     * Send a processing request to the specified AI agent.
     *
     * @param agentType Identifier of the agent type to route the request to (used in the request path).
     * @param request Payload containing the data for the agent to process.
     * @return The agent's response to the provided request.
     */
    @POST("agent/{agentType}/process-request")
    suspend fun processRequest(
        @Path("agentType") agentType: String,
        @Body request: AgentRequest,
    ): AgentResponse

    // Add more agent-related endpoints as needed
}