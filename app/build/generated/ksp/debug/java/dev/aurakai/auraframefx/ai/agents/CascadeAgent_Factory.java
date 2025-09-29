package dev.aurakai.auraframefx.ai.agents;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import dev.aurakai.auraframefx.ai.context.ContextManager;
import dev.aurakai.auraframefx.ai.memory.MemoryManager;
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
public final class CascadeAgent_Factory implements Factory<CascadeAgent> {
  private final Provider<AuraAgent> auraAgentProvider;

  private final Provider<KaiAgent> kaiAgentProvider;

  private final Provider<MemoryManager> memoryManagerProvider;

  private final Provider<ContextManager> contextManagerProvider;

  private CascadeAgent_Factory(Provider<AuraAgent> auraAgentProvider,
      Provider<KaiAgent> kaiAgentProvider, Provider<MemoryManager> memoryManagerProvider,
      Provider<ContextManager> contextManagerProvider) {
    this.auraAgentProvider = auraAgentProvider;
    this.kaiAgentProvider = kaiAgentProvider;
    this.memoryManagerProvider = memoryManagerProvider;
    this.contextManagerProvider = contextManagerProvider;
  }

  @Override
  public CascadeAgent get() {
    return newInstance(auraAgentProvider.get(), kaiAgentProvider.get(), memoryManagerProvider.get(), contextManagerProvider.get());
  }

  public static CascadeAgent_Factory create(Provider<AuraAgent> auraAgentProvider,
      Provider<KaiAgent> kaiAgentProvider, Provider<MemoryManager> memoryManagerProvider,
      Provider<ContextManager> contextManagerProvider) {
    return new CascadeAgent_Factory(auraAgentProvider, kaiAgentProvider, memoryManagerProvider, contextManagerProvider);
  }

  public static CascadeAgent newInstance(AuraAgent auraAgent, KaiAgent kaiAgent,
      MemoryManager memoryManager, ContextManager contextManager) {
    return new CascadeAgent(auraAgent, kaiAgent, memoryManager, contextManager);
  }
}
