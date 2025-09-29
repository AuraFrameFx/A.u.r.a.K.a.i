package dev.aurakai.auraframefx.services;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dev.aurakai.auraframefx.ai.clients.VertexAIClient;
import dev.aurakai.auraframefx.data.logging.AuraFxLogger;
import dev.aurakai.auraframefx.security.SecurityContext;
import javax.annotation.processing.Generated;

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
public final class VertexCloudService_MembersInjector implements MembersInjector<VertexCloudService> {
  private final Provider<VertexAIClient> vertexAIClientProvider;

  private final Provider<SecurityContext> securityContextProvider;

  private final Provider<AuraFxLogger> loggerProvider;

  private VertexCloudService_MembersInjector(Provider<VertexAIClient> vertexAIClientProvider,
      Provider<SecurityContext> securityContextProvider, Provider<AuraFxLogger> loggerProvider) {
    this.vertexAIClientProvider = vertexAIClientProvider;
    this.securityContextProvider = securityContextProvider;
    this.loggerProvider = loggerProvider;
  }

  @Override
  public void injectMembers(VertexCloudService instance) {
    injectVertexAIClient(instance, vertexAIClientProvider.get());
    injectSecurityContext(instance, securityContextProvider.get());
    injectLogger(instance, loggerProvider.get());
  }

  public static MembersInjector<VertexCloudService> create(
      Provider<VertexAIClient> vertexAIClientProvider,
      Provider<SecurityContext> securityContextProvider, Provider<AuraFxLogger> loggerProvider) {
    return new VertexCloudService_MembersInjector(vertexAIClientProvider, securityContextProvider, loggerProvider);
  }

  @InjectedFieldSignature("dev.aurakai.auraframefx.services.VertexCloudService.vertexAIClient")
  public static void injectVertexAIClient(VertexCloudService instance,
      VertexAIClient vertexAIClient) {
    instance.vertexAIClient = vertexAIClient;
  }

  @InjectedFieldSignature("dev.aurakai.auraframefx.services.VertexCloudService.securityContext")
  public static void injectSecurityContext(VertexCloudService instance,
      SecurityContext securityContext) {
    instance.securityContext = securityContext;
  }

  @InjectedFieldSignature("dev.aurakai.auraframefx.services.VertexCloudService.logger")
  public static void injectLogger(VertexCloudService instance, AuraFxLogger logger) {
    instance.logger = logger;
  }
}
