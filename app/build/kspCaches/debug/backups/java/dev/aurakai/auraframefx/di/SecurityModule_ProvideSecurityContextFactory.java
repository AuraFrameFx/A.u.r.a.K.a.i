package dev.aurakai.auraframefx.di;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import dev.aurakai.auraframefx.security.KeystoreManager;
import dev.aurakai.auraframefx.security.SecurityContext;
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
public final class SecurityModule_ProvideSecurityContextFactory implements Factory<SecurityContext> {
  private final Provider<Context> contextProvider;

  private final Provider<KeystoreManager> keystoreManagerProvider;

  private SecurityModule_ProvideSecurityContextFactory(Provider<Context> contextProvider,
      Provider<KeystoreManager> keystoreManagerProvider) {
    this.contextProvider = contextProvider;
    this.keystoreManagerProvider = keystoreManagerProvider;
  }

  @Override
  public SecurityContext get() {
    return provideSecurityContext(contextProvider.get(), keystoreManagerProvider.get());
  }

  public static SecurityModule_ProvideSecurityContextFactory create(
      Provider<Context> contextProvider, Provider<KeystoreManager> keystoreManagerProvider) {
    return new SecurityModule_ProvideSecurityContextFactory(contextProvider, keystoreManagerProvider);
  }

  public static SecurityContext provideSecurityContext(Context context,
      KeystoreManager keystoreManager) {
    return Preconditions.checkNotNullFromProvides(SecurityModule.INSTANCE.provideSecurityContext(context, keystoreManager));
  }
}
