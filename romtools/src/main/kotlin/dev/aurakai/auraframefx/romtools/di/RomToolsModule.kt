// File: romtools/src/main/kotlin/dev/aurakai/auraframefx/romtools/di/RomToolsModule.kt
package dev.aurakai.auraframefx.romtools.di

// Hilt imports temporarily commented out
// import dagger.Binds
// import dagger.Module
// import dagger.Provides
// import dagger.hilt.InstallIn
// import dagger.hilt.android.qualifiers.ApplicationContext
// import dagger.hilt.components.SingletonComponent
import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.aurakai.auraframefx.romtools.BackupManager
import dev.aurakai.auraframefx.romtools.BackupManagerImpl
import dev.aurakai.auraframefx.romtools.FlashManager
import dev.aurakai.auraframefx.romtools.FlashManagerImpl
import dev.aurakai.auraframefx.romtools.RecoveryManager
import dev.aurakai.auraframefx.romtools.RecoveryManagerImpl
import dev.aurakai.auraframefx.romtools.RomVerificationManager
import dev.aurakai.auraframefx.romtools.RomVerificationManagerImpl
import dev.aurakai.auraframefx.romtools.SystemModificationManager
import dev.aurakai.auraframefx.romtools.SystemModificationManagerImpl
import dev.aurakai.auraframefx.romtools.bootloader.BootloaderManager
import dev.aurakai.auraframefx.romtools.bootloader.BootloaderManagerImpl
import javax.inject.Singleton

/**
 */

        bootloaderManagerImpl: BootloaderManagerImpl

        recoveryManagerImpl: RecoveryManagerImpl

        systemModificationManagerImpl: SystemModificationManagerImpl

        flashManagerImpl: FlashManagerImpl

        romVerificationManagerImpl: RomVerificationManagerImpl

        backupManagerImpl: BackupManagerImpl

    companion object {

        fun provideRomToolsDataDirectory(
        ): String {
            return "${context.filesDir}/romtools"
        }

        fun provideRomToolsBackupDirectory(
        ): String {
            return "${context.getExternalFilesDir(null)}/backups"
        }

        fun provideRomToolsDownloadDirectory(
        ): String {
            return "${context.getExternalFilesDir(null)}/downloads"
        }

        fun provideRomToolsTempDirectory(
        ): String {
            return "${context.cacheDir}/romtools_temp"
        }
    }
}

@Retention(AnnotationRetention.BINARY)
annotation class RomToolsDataDir

/**
 * Qualifier for the backup directory for the ROM tools.
 */
@Retention(AnnotationRetention.BINARY)
annotation class RomToolsBackupDir

/**
 * Qualifier for the download directory for the ROM tools.
 */
@Retention(AnnotationRetention.BINARY)
annotation class RomToolsDownloadDir

/**
 * Qualifier for the temporary directory for the ROM tools.
 */
@Retention(AnnotationRetention.BINARY)
annotation class RomToolsTempDir
