package dev.aurakai.auraframefx.oracle.drive.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import dev.aurakai.auraframefx.oracle.drive.api.OracleDriveApi;
import dev.aurakai.auraframefx.security.SecurityContext;
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
public final class OracleDriveModule_Companion_ProvideOracleDriveApiFactory implements Factory<OracleDriveApi> {
  private final Provider<OkHttpClient> clientProvider;

  private final Provider<SecurityContext> securityContextProvider;

  private OracleDriveModule_Companion_ProvideOracleDriveApiFactory(
      Provider<OkHttpClient> clientProvider, Provider<SecurityContext> securityContextProvider) {
    this.clientProvider = clientProvider;
    this.securityContextProvider = securityContextProvider;
  }

  @Override
  public OracleDriveApi get() {
    return provideOracleDriveApi(clientProvider.get(), securityContextProvider.get());
  }

  public static OracleDriveModule_Companion_ProvideOracleDriveApiFactory create(
      Provider<OkHttpClient> clientProvider, Provider<SecurityContext> securityContextProvider) {
    return new OracleDriveModule_Companion_ProvideOracleDriveApiFactory(clientProvider, securityContextProvider);
  }

  public static OracleDriveApi provideOracleDriveApi(OkHttpClient client,
      SecurityContext securityContext) {
    return Preconditions.checkNotNullFromProvides(OracleDriveModule.Companion.provideOracleDriveApi(client, securityContext));
  }
}
