package dev.aurakai.auraframefx.auth;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
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
public final class OAuthService_Factory implements Factory<OAuthService> {
  private final Provider<Context> contextProvider;

  private final Provider<TokenManager> tokenManagerProvider;

  private OAuthService_Factory(Provider<Context> contextProvider,
      Provider<TokenManager> tokenManagerProvider) {
    this.contextProvider = contextProvider;
    this.tokenManagerProvider = tokenManagerProvider;
  }

  @Override
  public OAuthService get() {
    return newInstance(contextProvider.get(), tokenManagerProvider.get());
  }

  public static OAuthService_Factory create(Provider<Context> contextProvider,
      Provider<TokenManager> tokenManagerProvider) {
    return new OAuthService_Factory(contextProvider, tokenManagerProvider);
  }

  public static OAuthService newInstance(Context context, TokenManager tokenManager) {
    return new OAuthService(context, tokenManager);
  }
}
