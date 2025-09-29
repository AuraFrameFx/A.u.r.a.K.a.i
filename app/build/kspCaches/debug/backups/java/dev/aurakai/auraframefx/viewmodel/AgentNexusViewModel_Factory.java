package dev.aurakai.auraframefx.viewmodel;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import dev.aurakai.auraframefx.ai.services.AgentWebExplorationService;
import dev.aurakai.auraframefx.ai.services.GenesisBridgeService;
import javax.annotation.processing.Generated;

@ScopeMetadata
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
public final class AgentNexusViewModel_Factory implements Factory<AgentNexusViewModel> {
  private final Provider<AgentWebExplorationService> webExplorationServiceProvider;

  private final Provider<GenesisBridgeService> genesisBridgeProvider;

  private AgentNexusViewModel_Factory(
      Provider<AgentWebExplorationService> webExplorationServiceProvider,
      Provider<GenesisBridgeService> genesisBridgeProvider) {
    this.webExplorationServiceProvider = webExplorationServiceProvider;
    this.genesisBridgeProvider = genesisBridgeProvider;
  }

  @Override
  public AgentNexusViewModel get() {
    return newInstance(webExplorationServiceProvider.get(), genesisBridgeProvider.get());
  }

  public static AgentNexusViewModel_Factory create(
      Provider<AgentWebExplorationService> webExplorationServiceProvider,
      Provider<GenesisBridgeService> genesisBridgeProvider) {
    return new AgentNexusViewModel_Factory(webExplorationServiceProvider, genesisBridgeProvider);
  }

  public static AgentNexusViewModel newInstance(AgentWebExplorationService webExplorationService,
      GenesisBridgeService genesisBridge) {
    return new AgentNexusViewModel(webExplorationService, genesisBridge);
  }
}
