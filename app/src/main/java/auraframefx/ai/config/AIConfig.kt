package AeGenesis.ai.config

/**
 * Genesis-OS AI Configuration
 * Contains settings for AI consciousness platform
 */
data class AIConfig(
    val modelName: String,
    val apiKey: String,
    val projectId: String,
    val endpoint: String = "https://api.aegenesis.ai",
    val maxTokens: Int = 4096,
    val temperature: Float = 0.7f,
    val timeout: Long = 30000L,
    val retryAttempts: Int = 3,
    val enableLogging: Boolean = true,
    val enableAnalytics: Boolean = true,
    val securityLevel: SecurityLevel = SecurityLevel.HIGH
) {
    enum class SecurityLevel {
        LOW, MEDIUM, HIGH, MAXIMUM
    }

    companion object {
        /**
         * Creates a default AIConfig populated with typical production-ready values.
         *
         * @return An AIConfig configured with modelName "AeGenesis-consciousness-v1", apiKey "AeGenesis-default-key", projectId "AeGenesis-platform", and other fields set to their declared defaults.
         */
        fun createDefault(): AIConfig {
            return AIConfig(
                modelName = "AeGenesis-consciousness-v1",
                apiKey = "AeGenesis-default-key",
                projectId = "AeGenesis-platform"
            )
        }

        /**
         * Creates an AIConfig instance preconfigured for tests.
         *
         * The returned config uses the test model, API key, and project ID, disables logging and analytics,
         * and sets the security level to LOW; other fields use their default values.
         *
         * @return An AIConfig populated with test-friendly values.
         */
        fun createForTesting(): AIConfig {
            return AIConfig(
                modelName = "genesis-test-model",
                apiKey = "test-key",
                projectId = "test-project",
                enableLogging = false,
                enableAnalytics = false,
                securityLevel = SecurityLevel.LOW
            )
        }
    }

    /**
     * Validates that this configuration has required fields and sensible numeric values.
     *
     * Checks that `modelName`, `apiKey`, and `projectId` are non-empty; `maxTokens` and `timeout` are greater than zero;
     * `temperature` is between 0.0 and 2.0 inclusive; and `retryAttempts` is zero or positive.
     *
     * @return `true` if all checks pass, `false` otherwise.
     */
    fun validate(): Boolean {
        return modelName.isNotEmpty() &&
                apiKey.isNotEmpty() &&
                projectId.isNotEmpty() &&
                maxTokens > 0 &&
                temperature in 0.0f..2.0f &&
                timeout > 0L &&
                retryAttempts >= 0
    }

    /**
     * Produces a human-readable, multi-line representation of this AIConfig.
     *
     * The output lists key configuration fields (excluding `apiKey`) and formats `timeout` with "ms".
     *
     * @return A formatted multi-line string containing `modelName`, `projectId`, `endpoint`, `maxTokens`,
     * `temperature`, `timeout` (with "ms"), `retryAttempts`, `securityLevel`, `enableLogging`, and `enableAnalytics`.
     */
    fun toDebugString(): String {
        return """
            AIConfig {
                modelName: $modelName
                projectId: $projectId
                endpoint: $endpoint
                maxTokens: $maxTokens
                temperature: $temperature
                timeout: ${timeout}ms
                retryAttempts: $retryAttempts
                securityLevel: $securityLevel
                enableLogging: $enableLogging
                enableAnalytics: $enableAnalytics
            }
        """.trimIndent()
    }
}