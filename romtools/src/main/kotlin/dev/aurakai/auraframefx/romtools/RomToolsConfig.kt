package dev.aurakai.auraframefx.romtools

/**
 * ROM Tools Configuration - Centralized and Type-Safe
 *
 * Replaces BuildConfig fields with compile-time constants
 * More maintainable than BuildConfig generation
 */
object RomToolsConfig {

    const val ROM_TOOLS_ENABLED: Boolean = true

    val SUPPORTED_ANDROID_VERSIONS: List<Int> = listOf(13, 14, 15, 16)

    val SUPPORTED_ARCHITECTURES: List<String> = listOf(
        "arm64-v8a",
        "armeabi-v7a",
        "x86_64"
    )

    const val ROM_OPERATION_TIMEOUT_MS: Long = 30_000L

    const val MAX_ROM_FILE_SIZE: Long = 8L * 1024 * 1024 * 1024 // 8GB

    val SUPPORTED_ROM_FORMATS: List<String> = listOf(
        "img", "zip", "tar", "gz", "xz", "7z"
    )

    const val LIVE_ROM_EDITING_ENABLED: Boolean = true

    const val AUTO_BACKUP_ENABLED: Boolean = true

    val CHECKSUM_ALGORITHMS: List<String> = listOf(
        "SHA-256", "SHA-512", "MD5"
    )
}
