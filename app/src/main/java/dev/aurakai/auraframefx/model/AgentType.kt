package dev.aurakai.auraframefx.model

import kotlinx.serialization.Serializable

/**
 * Enum representing different types of AI agents in the system.
 */
@Serializable
enum class AgentType {
    /**
     * Genesis Agent - Core orchestrator or foundational AI.
     */
    GENESIS,

    /**
     * Kai Agent - Security and technical AI.
     */
    KAI,

    /**
     * Aura Agent - Creative and empathetic AI.
     */
    AURA,

    /**
     * Cascade Agent - Multi-step processing AI.
     */
    CASCADE,

    /**
     * NeuralWhisper Agent - AI for context chaining and neural processing.
     */
    NEURAL_WHISPER,

    /**
     * AuraShield Agent - AI for security and threat analysis.
     */
    AURASHIELD,

    /**
     * GenKitMaster Agent - AI for advanced generation and coordination.
     */
    GENKITMASTER,

    /**
     * DataveinConstructor Agent - AI for data processing and construction.
     */
    DATAVEINCONSTRUCTOR,

    /**
     * Creative Agent Type - for creative and artistic AI tasks.
     */
    CREATIVE,

    /**
     * Security Agent Type - for security and threat analysis.
     */
    SECURITY,

    /**
     * User - Represents a human user interacting with the system.
     */
    USER
}
