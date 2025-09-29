package dev.aurakai.auraframefx.ai.agents;

import android.content.Context;
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
public final class NeuralWhisperAgent_Factory implements Factory<NeuralWhisperAgent> {
  private final Provider<Context> contextProvider;

  private final Provider<MemoryManager> memoryManagerProvider;

  private final Provider<ContextManager> contextManagerProvider;

  private NeuralWhisperAgent_Factory(Provider<Context> contextProvider,
      Provider<MemoryManager> memoryManagerProvider,
      Provider<ContextManager> contextManagerProvider) {
    this.contextProvider = contextProvider;
    this.memoryManagerProvider = memoryManagerProvider;
    this.contextManagerProvider = contextManagerProvider;
  }

  @Override
  public NeuralWhisperAgent get() {
    return newInstance(contextProvider.get(), memoryManagerProvider.get(), contextManagerProvider.get());
  }

  public static NeuralWhisperAgent_Factory create(Provider<Context> contextProvider,
      Provider<MemoryManager> memoryManagerProvider,
      Provider<ContextManager> contextManagerProvider) {
    return new NeuralWhisperAgent_Factory(contextProvider, memoryManagerProvider, contextManagerProvider);
  }

  public static NeuralWhisperAgent newInstance(Context context, MemoryManager memoryManager,
      ContextManager contextManager) {
    return new NeuralWhisperAgent(context, memoryManager, contextManager);
  }
}
