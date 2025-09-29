package dev.aurakai.auraframefx.oracle.drive.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import dev.aurakai.auraframefx.security.SecurityContext;
import dev.aurakai.genesis.security.CryptographyManager;
import javax.annotation.processing.Generated;
import okhttp3.OkHttpClient;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
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
public final class OracleDriveModule_Companion_ProvideOkHttpClientFactory implements Factory<OkHttpClient> {
  private final Provider<SecurityContext> securityContextProvider;

  private final Provider<CryptographyManager> cryptoManagerProvider;

  private OracleDriveModule_Companion_ProvideOkHttpClientFactory(
      Provider<SecurityContext> securityContextProvider,
      Provider<CryptographyManager> cryptoManagerProvider) {
    this.securityContextProvider = securityContextProvider;
    this.cryptoManagerProvider = cryptoManagerProvider;
  }

  @Override
  public OkHttpClient get() {
    return provideOkHttpClient(securityContextProvider.get(), cryptoManagerProvider.get());
  }

  public static OracleDriveModule_Companion_ProvideOkHttpClientFactory create(
      Provider<SecurityContext> securityContextProvider,
      Provider<CryptographyManager> cryptoManagerProvider) {
    return new OracleDriveModule_Companion_ProvideOkHttpClientFactory(securityContextProvider, cryptoManagerProvider);
  }

  public static OkHttpClient provideOkHttpClient(SecurityContext securityContext,
      CryptographyManager cryptoManager) {
    return Preconditions.checkNotNullFromProvides(OracleDriveModule.Companion.provideOkHttpClient(securityContext, cryptoManager));
  }
}
