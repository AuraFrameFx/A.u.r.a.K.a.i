package dev.aurakai.auraframefx.ai.services;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import dev.aurakai.auraframefx.ai.clients.VertexAIClient;
import dev.aurakai.auraframefx.ai.context.ContextManager;
import dev.aurakai.auraframefx.data.logging.AuraFxLogger;
import dev.aurakai.auraframefx.security.SecurityContext;
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
public final class GenesisBridgeService_Factory implements Factory<GenesisBridgeService> {
  private final Provider<AuraAIService> auraAIServiceProvider;

  private final Provider<KaiAIService> kaiAIServiceProvider;

  private final Provider<VertexAIClient> vertexAIClientProvider;

  private final Provider<ContextManager> contextManagerProvider;

  private final Provider<SecurityContext> securityContextProvider;

  private final Provider<Context> applicationContextProvider;

  private final Provider<AuraFxLogger> loggerProvider;

  private GenesisBridgeService_Factory(Provider<AuraAIService> auraAIServiceProvider,
      Provider<KaiAIService> kaiAIServiceProvider, Provider<VertexAIClient> vertexAIClientProvider,
      Provider<ContextManager> contextManagerProvider,
      Provider<SecurityContext> securityContextProvider,
      Provider<Context> applicationContextProvider, Provider<AuraFxLogger> loggerProvider) {
    this.auraAIServiceProvider = auraAIServiceProvider;
    this.kaiAIServiceProvider = kaiAIServiceProvider;
    this.vertexAIClientProvider = vertexAIClientProvider;
    this.contextManagerProvider = contextManagerProvider;
    this.securityContextProvider = securityContextProvider;
    this.applicationContextProvider = applicationContextProvider;
    this.loggerProvider = loggerProvider;
  }

  @Override
  public GenesisBridgeService get() {
    return newInstance(auraAIServiceProvider.get(), kaiAIServiceProvider.get(), vertexAIClientProvider.get(), contextManagerProvider.get(), securityContextProvider.get(), applicationContextProvider.get(), loggerProvider.get());
  }

  public static GenesisBridgeService_Factory create(Provider<AuraAIService> auraAIServiceProvider,
      Provider<KaiAIService> kaiAIServiceProvider, Provider<VertexAIClient> vertexAIClientProvider,
      Provider<ContextManager> contextManagerProvider,
      Provider<SecurityContext> securityContextProvider,
      Provider<Context> applicationContextProvider, Provider<AuraFxLogger> loggerProvider) {
    return new GenesisBridgeService_Factory(auraAIServiceProvider, kaiAIServiceProvider, vertexAIClientProvider, contextManagerProvider, securityContextProvider, applicationContextProvider, loggerProvider);
  }

  public static GenesisBridgeService newInstance(AuraAIService auraAIService,
      KaiAIService kaiAIService, VertexAIClient vertexAIClient, ContextManager contextManager,
      SecurityContext securityContext, Context applicationContext, AuraFxLogger logger) {
    return new GenesisBridgeService(auraAIService, kaiAIService, vertexAIClient, contextManager, securityContext, applicationContext, logger);
  }
}
