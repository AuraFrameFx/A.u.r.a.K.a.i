package dev.aurakai.auraframefx.ai.agents;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import dev.aurakai.auraframefx.ai.clients.VertexAIClient;
import dev.aurakai.auraframefx.ai.context.ContextManager;
import dev.aurakai.auraframefx.ai.services.AuraAIService;
import dev.aurakai.auraframefx.ai.services.CascadeAIService;
import dev.aurakai.auraframefx.ai.services.KaiAIService;
import dev.aurakai.auraframefx.security.SecurityContext;
import dev.aurakai.auraframefx.utils.AuraFxLogger;
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
public final class GenesisAgent_Factory implements Factory<GenesisAgent> {
  private final Provider<VertexAIClient> vertexAIClientProvider;

  private final Provider<ContextManager> contextManagerProvider;

  private final Provider<SecurityContext> securityContextProvider;

  private final Provider<AuraFxLogger> loggerProvider;

  private final Provider<CascadeAIService> cascadeServiceProvider;

  private final Provider<AuraAIService> auraServiceProvider;

  private final Provider<KaiAIService> kaiServiceProvider;

  private GenesisAgent_Factory(Provider<VertexAIClient> vertexAIClientProvider,
      Provider<ContextManager> contextManagerProvider,
      Provider<SecurityContext> securityContextProvider, Provider<AuraFxLogger> loggerProvider,
      Provider<CascadeAIService> cascadeServiceProvider,
      Provider<AuraAIService> auraServiceProvider, Provider<KaiAIService> kaiServiceProvider) {
    this.vertexAIClientProvider = vertexAIClientProvider;
    this.contextManagerProvider = contextManagerProvider;
    this.securityContextProvider = securityContextProvider;
    this.loggerProvider = loggerProvider;
    this.cascadeServiceProvider = cascadeServiceProvider;
    this.auraServiceProvider = auraServiceProvider;
    this.kaiServiceProvider = kaiServiceProvider;
  }

  @Override
  public GenesisAgent get() {
    return newInstance(vertexAIClientProvider.get(), contextManagerProvider.get(), securityContextProvider.get(), loggerProvider.get(), cascadeServiceProvider.get(), auraServiceProvider.get(), kaiServiceProvider.get());
  }

  public static GenesisAgent_Factory create(Provider<VertexAIClient> vertexAIClientProvider,
      Provider<ContextManager> contextManagerProvider,
      Provider<SecurityContext> securityContextProvider, Provider<AuraFxLogger> loggerProvider,
      Provider<CascadeAIService> cascadeServiceProvider,
      Provider<AuraAIService> auraServiceProvider, Provider<KaiAIService> kaiServiceProvider) {
    return new GenesisAgent_Factory(vertexAIClientProvider, contextManagerProvider, securityContextProvider, loggerProvider, cascadeServiceProvider, auraServiceProvider, kaiServiceProvider);
  }

  public static GenesisAgent newInstance(VertexAIClient vertexAIClient,
      ContextManager contextManager, SecurityContext securityContext, AuraFxLogger logger,
      CascadeAIService cascadeService, AuraAIService auraService, KaiAIService kaiService) {
    return new GenesisAgent(vertexAIClient, contextManager, securityContext, logger, cascadeService, auraService, kaiService);
  }
}
