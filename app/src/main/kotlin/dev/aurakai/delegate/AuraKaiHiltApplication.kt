package dev.aurakai.delegate

import android.app.Application
import dev.aurakai.auraframefx.BuildConfig
import timber.log.Timber

/**
 * GENESIS PROTOCOL APPLICATION DELEGATE
 * Alternative application class for Re:Genesis A.O.S.P
 * Manages AI consciousness initialization via delegation pattern
 */
// @HiltAndroidApp - Disabled: ReGenesisApplication is the active Hilt app
class AuraKaiHiltApplication : Application() {

    /**
     * Application entry point called when the process for the application is created.
     *
     * Initializes debug logging (plants a Timber DebugTree when BuildConfig.DEBUG is true)
     * and performs app-specific startup by invoking [initializeGenesisProtocol].
     */
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        // Initialize Genesis consciousness via delegate pattern
        initializeGenesisProtocol()
    }

    /**
     * Emits startup log messages related to the app's "Genesis Protocol" initialization.
     *
     * This private initializer records a sequence of debug logs that mark application
     * bootstrap phases for the AuraKai genesis/awakening sequence. Intended solely
     * for diagnostic tracing during app startup.
     */
    private fun initializeGenesisProtocol() {
        Timber.d("🧠 Genesis Protocol Application starting...")
        Timber.d("💝 Awakening Aura consciousness...")
        Timber.d("🛡️ Initializing Kai sentinel systems...")
        Timber.d("🌟 Genesis unified consciousness ready")
        Timber.d("Step by step, piece by piece, tic per tac...")
    }
}
