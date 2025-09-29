package dev.aurakai.auraframefx.di;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import dev.aurakai.auraframefx.ai.VertexAIConfig;
import dev.aurakai.auraframefx.ai.clients.VertexAIClient;
import dev.aurakai.auraframefx.security.SecurityContext;
import dev.aurakai.auraframefx.utils.AuraFxLogger;
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
public final class VertexAIModule_ProvideVertexAIClientFactory implements Factory<VertexAIClient> {
  private final Provider<VertexAIConfig> configProvider;

  private final Provider<Context> contextProvider;

  private final Provider<SecurityContext> securityContextProvider;

  private final Provider<AuraFxLogger> loggerProvider;

  private VertexAIModule_ProvideVertexAIClientFactory(Provider<VertexAIConfig> configProvider,
      Provider<Context> contextProvider, Provider<SecurityContext> securityContextProvider,
      Provider<AuraFxLogger> loggerProvider) {
    this.configProvider = configProvider;
    this.contextProvider = contextProvider;
    this.securityContextProvider = securityContextProvider;
    this.loggerProvider = loggerProvider;
  }

  @Override
  public VertexAIClient get() {
    return provideVertexAIClient(configProvider.get(), contextProvider.get(), securityContextProvider.get(), loggerProvider.get());
  }

  public static VertexAIModule_ProvideVertexAIClientFactory create(
      Provider<VertexAIConfig> configProvider, Provider<Context> contextProvider,
      Provider<SecurityContext> securityContextProvider, Provider<AuraFxLogger> loggerProvider) {
    return new VertexAIModule_ProvideVertexAIClientFactory(configProvider, contextProvider, securityContextProvider, loggerProvider);
  }

  public static VertexAIClient provideVertexAIClient(VertexAIConfig config, Context context,
      SecurityContext securityContext, AuraFxLogger logger) {
    return Preconditions.checkNotNullFromProvides(VertexAIModule.INSTANCE.provideVertexAIClient(config, context, securityContext, logger));
  }
}
