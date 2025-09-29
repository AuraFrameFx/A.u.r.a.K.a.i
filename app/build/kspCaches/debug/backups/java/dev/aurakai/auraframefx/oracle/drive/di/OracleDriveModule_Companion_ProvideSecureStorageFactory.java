package dev.aurakai.auraframefx.oracle.drive.di;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class OracleDriveModule_Companion_ProvideSecureStorageFactory implements Factory<SecureStorage> {
  private final Provider<Context> contextProvider;

  private final Provider<CryptographyManager> cryptoManagerProvider;

  private OracleDriveModule_Companion_ProvideSecureStorageFactory(Provider<Context> contextProvider,
      Provider<CryptographyManager> cryptoManagerProvider) {
    this.contextProvider = contextProvider;
    this.cryptoManagerProvider = cryptoManagerProvider;
  }

  @Override
  public SecureStorage get() {
    return provideSecureStorage(contextProvider.get(), cryptoManagerProvider.get());
  }

  public static OracleDriveModule_Companion_ProvideSecureStorageFactory create(
      Provider<Context> contextProvider, Provider<CryptographyManager> cryptoManagerProvider) {
    return new OracleDriveModule_Companion_ProvideSecureStorageFactory(contextProvider, cryptoManagerProvider);
  }

  public static SecureStorage provideSecureStorage(Context context,
      CryptographyManager cryptoManager) {
    return Preconditions.checkNotNullFromProvides(OracleDriveModule.Companion.provideSecureStorage(context, cryptoManager));
  }
}
