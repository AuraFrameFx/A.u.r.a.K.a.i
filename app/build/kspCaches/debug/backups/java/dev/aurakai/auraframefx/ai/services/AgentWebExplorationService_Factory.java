package dev.aurakai.auraframefx.ai.services;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import dev.aurakai.auraframefx.data.logging.AuraFxLogger;
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
public final class AgentWebExplorationService_Factory implements Factory<AgentWebExplorationService> {
  private final Provider<AuraFxLogger> loggerProvider;

  private AgentWebExplorationService_Factory(Provider<AuraFxLogger> loggerProvider) {
    this.loggerProvider = loggerProvider;
  }

  @Override
  public AgentWebExplorationService get() {
    return newInstance(loggerProvider.get());
  }

  public static AgentWebExplorationService_Factory create(Provider<AuraFxLogger> loggerProvider) {
    return new AgentWebExplorationService_Factory(loggerProvider);
  }

  public static AgentWebExplorationService newInstance(AuraFxLogger logger) {
    return new AgentWebExplorationService(logger);
  }
}
