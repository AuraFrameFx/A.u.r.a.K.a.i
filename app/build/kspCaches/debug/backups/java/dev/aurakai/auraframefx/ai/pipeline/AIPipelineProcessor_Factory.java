package dev.aurakai.auraframefx.ai.pipeline;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import dev.aurakai.auraframefx.ai.agents.GenesisAgent;
import dev.aurakai.auraframefx.ai.services.AuraAIService;
import dev.aurakai.auraframefx.ai.services.CascadeAIService;
import dev.aurakai.auraframefx.ai.services.KaiAIService;
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
public final class AIPipelineProcessor_Factory implements Factory<AIPipelineProcessor> {
  private final Provider<GenesisAgent> genesisAgentProvider;

  private final Provider<AuraAIService> auraServiceProvider;

  private final Provider<KaiAIService> kaiServiceProvider;

  private final Provider<CascadeAIService> cascadeServiceProvider;

  private AIPipelineProcessor_Factory(Provider<GenesisAgent> genesisAgentProvider,
      Provider<AuraAIService> auraServiceProvider, Provider<KaiAIService> kaiServiceProvider,
      Provider<CascadeAIService> cascadeServiceProvider) {
    this.genesisAgentProvider = genesisAgentProvider;
    this.auraServiceProvider = auraServiceProvider;
    this.kaiServiceProvider = kaiServiceProvider;
    this.cascadeServiceProvider = cascadeServiceProvider;
  }

  @Override
  public AIPipelineProcessor get() {
    return newInstance(genesisAgentProvider.get(), auraServiceProvider.get(), kaiServiceProvider.get(), cascadeServiceProvider.get());
  }

  public static AIPipelineProcessor_Factory create(Provider<GenesisAgent> genesisAgentProvider,
      Provider<AuraAIService> auraServiceProvider, Provider<KaiAIService> kaiServiceProvider,
      Provider<CascadeAIService> cascadeServiceProvider) {
    return new AIPipelineProcessor_Factory(genesisAgentProvider, auraServiceProvider, kaiServiceProvider, cascadeServiceProvider);
  }

  public static AIPipelineProcessor newInstance(GenesisAgent genesisAgent,
      AuraAIService auraService, KaiAIService kaiService, CascadeAIService cascadeService) {
    return new AIPipelineProcessor(genesisAgent, auraService, kaiService, cascadeService);
  }
}
