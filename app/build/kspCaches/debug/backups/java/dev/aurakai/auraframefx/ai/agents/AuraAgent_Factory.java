package dev.aurakai.auraframefx.ai.agents;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import dev.aurakai.auraframefx.ai.clients.VertexAIClient;
import dev.aurakai.auraframefx.ai.context.ContextManager;
import dev.aurakai.auraframefx.ai.services.AuraAIService;
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
public final class AuraAgent_Factory implements Factory<AuraAgent> {
  private final Provider<VertexAIClient> vertexAIClientProvider;

  private final Provider<AuraAIService> auraAIServiceProvider;

  private final Provider<ContextManager> contextManagerParamProvider;

  private final Provider<SecurityContext> securityContextProvider;

  private final Provider<AuraFxLogger> loggerProvider;

  private AuraAgent_Factory(Provider<VertexAIClient> vertexAIClientProvider,
      Provider<AuraAIService> auraAIServiceProvider,
      Provider<ContextManager> contextManagerParamProvider,
      Provider<SecurityContext> securityContextProvider, Provider<AuraFxLogger> loggerProvider) {
    this.vertexAIClientProvider = vertexAIClientProvider;
    this.auraAIServiceProvider = auraAIServiceProvider;
    this.contextManagerParamProvider = contextManagerParamProvider;
    this.securityContextProvider = securityContextProvider;
    this.loggerProvider = loggerProvider;
  }

  @Override
  public AuraAgent get() {
    return newInstance(vertexAIClientProvider.get(), auraAIServiceProvider.get(), contextManagerParamProvider.get(), securityContextProvider.get(), loggerProvider.get());
  }

  public static AuraAgent_Factory create(Provider<VertexAIClient> vertexAIClientProvider,
      Provider<AuraAIService> auraAIServiceProvider,
      Provider<ContextManager> contextManagerParamProvider,
      Provider<SecurityContext> securityContextProvider, Provider<AuraFxLogger> loggerProvider) {
    return new AuraAgent_Factory(vertexAIClientProvider, auraAIServiceProvider, contextManagerParamProvider, securityContextProvider, loggerProvider);
  }

  public static AuraAgent newInstance(VertexAIClient vertexAIClient, AuraAIService auraAIService,
      ContextManager contextManagerParam, SecurityContext securityContext, AuraFxLogger logger) {
    return new AuraAgent(vertexAIClient, auraAIService, contextManagerParam, securityContext, logger);
  }
}
