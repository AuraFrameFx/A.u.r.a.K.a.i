package dev.aurakai.auraframefx.network;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import dev.aurakai.auraframefx.auth.TokenManager;
import javax.annotation.processing.Generated;

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
public final class AuthInterceptor_Factory implements Factory<AuthInterceptor> {
  private final Provider<TokenManager> tokenManagerProvider;

  private final Provider<AuthApi> authApiProvider;

  private AuthInterceptor_Factory(Provider<TokenManager> tokenManagerProvider,
      Provider<AuthApi> authApiProvider) {
    this.tokenManagerProvider = tokenManagerProvider;
    this.authApiProvider = authApiProvider;
  }

  @Override
  public AuthInterceptor get() {
    return newInstance(tokenManagerProvider.get(), authApiProvider.get());
  }

  public static AuthInterceptor_Factory create(Provider<TokenManager> tokenManagerProvider,
      Provider<AuthApi> authApiProvider) {
    return new AuthInterceptor_Factory(tokenManagerProvider, authApiProvider);
  }

  public static AuthInterceptor newInstance(TokenManager tokenManager, AuthApi authApi) {
    return new AuthInterceptor(tokenManager, authApi);
  }
}
