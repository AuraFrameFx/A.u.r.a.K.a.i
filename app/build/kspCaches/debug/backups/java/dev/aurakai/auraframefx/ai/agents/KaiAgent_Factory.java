package dev.aurakai.auraframefx.ai.agents;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import dev.aurakai.auraframefx.ai.clients.VertexAIClient;
import dev.aurakai.auraframefx.ai.context.ContextManager;
import dev.aurakai.auraframefx.security.SecurityContext;
import dev.aurakai.auraframefx.system.monitor.SystemMonitor;
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
public final class KaiAgent_Factory implements Factory<KaiAgent> {
  private final Provider<VertexAIClient> vertexAIClientProvider;

  private final Provider<ContextManager> contextManagerParamProvider;

  private final Provider<SecurityContext> securityContextProvider;

  private final Provider<SystemMonitor> systemMonitorProvider;

  private final Provider<AuraFxLogger> loggerProvider;

  private KaiAgent_Factory(Provider<VertexAIClient> vertexAIClientProvider,
      Provider<ContextManager> contextManagerParamProvider,
      Provider<SecurityContext> securityContextProvider,
      Provider<SystemMonitor> systemMonitorProvider, Provider<AuraFxLogger> loggerProvider) {
    this.vertexAIClientProvider = vertexAIClientProvider;
    this.contextManagerParamProvider = contextManagerParamProvider;
    this.securityContextProvider = securityContextProvider;
    this.systemMonitorProvider = systemMonitorProvider;
    this.loggerProvider = loggerProvider;
  }

  @Override
  public KaiAgent get() {
    return newInstance(vertexAIClientProvider.get(), contextManagerParamProvider.get(), securityContextProvider.get(), systemMonitorProvider.get(), loggerProvider.get());
  }

  public static KaiAgent_Factory create(Provider<VertexAIClient> vertexAIClientProvider,
      Provider<ContextManager> contextManagerParamProvider,
      Provider<SecurityContext> securityContextProvider,
      Provider<SystemMonitor> systemMonitorProvider, Provider<AuraFxLogger> loggerProvider) {
    return new KaiAgent_Factory(vertexAIClientProvider, contextManagerParamProvider, securityContextProvider, systemMonitorProvider, loggerProvider);
  }

  public static KaiAgent newInstance(VertexAIClient vertexAIClient,
      ContextManager contextManagerParam, SecurityContext securityContext,
      SystemMonitor systemMonitor, AuraFxLogger logger) {
    return new KaiAgent(vertexAIClient, contextManagerParam, securityContext, systemMonitor, logger);
  }
}
