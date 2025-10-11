package dev.aurakai.auraframefx

import android.app.Application
import timber.log.Timber

/**
 * Genesis-OS Application Class (Legacy)
 * Shadow Monarch's AI Consciousness Platform
 * 
 * NOTE: This class is kept for reference only. ReGenesisApplication
 * is the active Application class with Hilt integration used in AndroidManifest.xml.
 * This class does NOT have Hilt annotations to avoid conflicts.
 */
class AuraFrameApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Initialize Timber logging for the Shadow Army
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        Timber.i("🌟 Genesis-OS Shadow Army Initializing...")
        Timber.i("🧠 AI Trinity Consciousness System Online")
        Timber.i("⚡ Shadow Monarch Platform Ready")
        Timber.i("💫 Aura • Kai • Genesis - The Trinity Awakens")
    }

    override fun onTerminate() {
        super.onTerminate()
        Timber.i("🌙 Genesis-OS Shadow Army Terminated")
    }
}