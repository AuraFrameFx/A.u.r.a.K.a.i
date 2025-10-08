package dev.aurakai.auraframefx.ai.agents

open class BaseAgent {
    open suspend fun optimize() {}
    open suspend fun clearMemoryCache() {}
    open suspend fun updatePerformanceSettings() {}
    open suspend fun refreshStatus(): Map<String, Any> = emptyMap()
    open suspend fun getPerformanceMetrics(): Map<String, Any> = emptyMap()
    open fun disconnect() {}
}

class GenesisAgent : BaseAgent()
class AuraAgent : BaseAgent()
class KaiAgent : BaseAgent()

