package dev.aurakai.auraframefx.ai.agents;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
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
public final class GenKitMasterAgent_Factory implements Factory<GenKitMasterAgent> {
  private final Provider<Context> contextProvider;

  private final Provider<GenesisAgent> genesisAgentProvider;

  private final Provider<AuraAgent> auraAgentProvider;

  private final Provider<KaiAgent> kaiAgentProvider;

  private GenKitMasterAgent_Factory(Provider<Context> contextProvider,
      Provider<GenesisAgent> genesisAgentProvider, Provider<AuraAgent> auraAgentProvider,
      Provider<KaiAgent> kaiAgentProvider) {
    this.contextProvider = contextProvider;
    this.genesisAgentProvider = genesisAgentProvider;
    this.auraAgentProvider = auraAgentProvider;
    this.kaiAgentProvider = kaiAgentProvider;
  }

  @Override
  public GenKitMasterAgent get() {
    return newInstance(contextProvider.get(), genesisAgentProvider.get(), auraAgentProvider.get(), kaiAgentProvider.get());
  }

  public static GenKitMasterAgent_Factory create(Provider<Context> contextProvider,
      Provider<GenesisAgent> genesisAgentProvider, Provider<AuraAgent> auraAgentProvider,
      Provider<KaiAgent> kaiAgentProvider) {
    return new GenKitMasterAgent_Factory(contextProvider, genesisAgentProvider, auraAgentProvider, kaiAgentProvider);
  }

  public static GenKitMasterAgent newInstance(Context context, GenesisAgent genesisAgent,
      AuraAgent auraAgent, KaiAgent kaiAgent) {
    return new GenKitMasterAgent(context, genesisAgent, auraAgent, kaiAgent);
  }
}
