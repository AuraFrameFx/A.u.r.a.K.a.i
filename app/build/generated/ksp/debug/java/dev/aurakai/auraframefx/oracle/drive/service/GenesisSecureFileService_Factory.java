package dev.aurakai.auraframefx.oracle.drive.service;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
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
public final class GenesisSecureFileService_Factory implements Factory<GenesisSecureFileService> {
  private final Provider<Context> contextProvider;

  private final Provider<CryptographyManager> cryptoManagerProvider;

  private final Provider<SecureStorage> secureStorageProvider;

  private GenesisSecureFileService_Factory(Provider<Context> contextProvider,
      Provider<CryptographyManager> cryptoManagerProvider,
      Provider<SecureStorage> secureStorageProvider) {
    this.contextProvider = contextProvider;
    this.cryptoManagerProvider = cryptoManagerProvider;
    this.secureStorageProvider = secureStorageProvider;
  }

  @Override
  public GenesisSecureFileService get() {
    return newInstance(contextProvider.get(), cryptoManagerProvider.get(), secureStorageProvider.get());
  }

  public static GenesisSecureFileService_Factory create(Provider<Context> contextProvider,
      Provider<CryptographyManager> cryptoManagerProvider,
      Provider<SecureStorage> secureStorageProvider) {
    return new GenesisSecureFileService_Factory(contextProvider, cryptoManagerProvider, secureStorageProvider);
  }

  public static GenesisSecureFileService newInstance(Context context,
      CryptographyManager cryptoManager, SecureStorage secureStorage) {
    return new GenesisSecureFileService(context, cryptoManager, secureStorage);
  }
}
