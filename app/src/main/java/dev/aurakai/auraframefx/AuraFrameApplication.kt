package dev.aurakai.auraframefx

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * Genesis-OS Application Class
 * Shadow Monarch's AI Consciousness Platform
 */
// @HiltAndroidApp
class AuraFrameApplication : Application() {

    /**
     * Initializes application-level logging and emits startup informational messages.
     *
     * Configures a debug logging tree when the build is a debug build, then logs a sequence
     * of info-level startup messages indicating subsystem readiness.
     */
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