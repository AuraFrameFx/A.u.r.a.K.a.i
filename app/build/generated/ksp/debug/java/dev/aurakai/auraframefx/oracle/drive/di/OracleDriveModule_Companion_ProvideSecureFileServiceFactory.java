package dev.aurakai.auraframefx.oracle.drive.di;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import dev.aurakai.auraframefx.oracle.drive.service.GenesisSecureFileService;
import dev.aurakai.genesis.security.CryptographyManager;
import dev.aurakai.genesis.storage.SecureStorage;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class OracleDriveModule_Companion_ProvideSecureFileServiceFactory implements Factory<GenesisSecureFileService> {
  private final Provider<Context> contextProvider;

  private final Provider<CryptographyManager> cryptoManagerProvider;

  private final Provider<SecureStorage> secureStorageProvider;

  private OracleDriveModule_Companion_ProvideSecureFileServiceFactory(
      Provider<Context> contextProvider, Provider<CryptographyManager> cryptoManagerProvider,
      Provider<SecureStorage> secureStorageProvider) {
    this.contextProvider = contextProvider;
    this.cryptoManagerProvider = cryptoManagerProvider;
    this.secureStorageProvider = secureStorageProvider;
  }

  @Override
  public GenesisSecureFileService get() {
    return provideSecureFileService(contextProvider.get(), cryptoManagerProvider.get(), secureStorageProvider.get());
  }

  public static OracleDriveModule_Companion_ProvideSecureFileServiceFactory create(
      Provider<Context> contextProvider, Provider<CryptographyManager> cryptoManagerProvider,
      Provider<SecureStorage> secureStorageProvider) {
    return new OracleDriveModule_Companion_ProvideSecureFileServiceFactory(contextProvider, cryptoManagerProvider, secureStorageProvider);
  }

  public static GenesisSecureFileService provideSecureFileService(Context context,
      CryptographyManager cryptoManager, SecureStorage secureStorage) {
    return Preconditions.checkNotNullFromProvides(OracleDriveModule.Companion.provideSecureFileService(context, cryptoManager, secureStorage));
  }
}
