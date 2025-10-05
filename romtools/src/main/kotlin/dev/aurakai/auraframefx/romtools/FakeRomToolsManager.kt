package dev.aurakai.auraframefx.romtools

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * Fake implementation of RomToolsManager for Jetpack Compose previews.
 * Provides a pre-initialized state for UI rendering in previews.
 */
open class FakeRomToolsManager : RomToolsManager(
    context = null, // Not used in preview
    bootloaderManager = FakeBootloaderManager(),
    recoveryManager = FakeRecoveryManager(),
    systemModificationManager = FakeSystemModificationManager(),
    flashManager = FakeFlashManager(), // Added comma
    verificationManager = FakeRomVerificationManager(),
    backupManager = FakeBackupManager()
) {
    private val fakeState: RomToolsState = RomToolsState(
        isInitialized = true,
        capabilities = RomCapabilities(
            hasRootAccess = true,
            hasBootloaderAccess = true,
            hasRecoveryAccess = true,
            hasSystemWriteAccess = true,
            supportedArchitectures = listOf("arm64-v8a", "armeabi-v7a"),
            deviceModel = "Pixel 7 Pro",
            androidVersion = "14",
            securityPatchLevel = "2025-10-05"
        )
    )
    override val romToolsState: StateFlow<RomToolsState>
        get() = MutableStateFlow(fakeState)
    override val operationProgress: StateFlow<OperationProgress?>
        get() = MutableStateFlow(null)
}

// Minimal fake managers for preview (implement as needed for preview only)
class FakeBootloaderManager : BootloaderManager(null, null, null)
class FakeRecoveryManager : RecoveryManager(null, null, null)
class FakeSystemModificationManager : SystemModificationManager(null, null, null)
class FakeFlashManager : FlashManager(null, null, null)
class FakeRomVerificationManager : RomVerificationManager(null, null, null)
class FakeBackupManager : BackupManager(null, null, null)
