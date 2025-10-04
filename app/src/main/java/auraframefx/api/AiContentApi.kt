package dev.aurakai.auraframefx.api

import dev.aurakai.auraframefx.ai.model.GenerateTextRequest
import dev.aurakai.auraframefx.ai.model.GenerateTextResponse

interface AiContentApi {
    /**
 * Produce text content using AI according to the supplied generation parameters.
 *
 * @param request Parameters for generation (prompt, model, and other settings).
 * @return A GenerateTextResponse containing the generated text and any related metadata.
 */
    suspend fun generateText(request: GenerateTextRequest): GenerateTextResponse
}