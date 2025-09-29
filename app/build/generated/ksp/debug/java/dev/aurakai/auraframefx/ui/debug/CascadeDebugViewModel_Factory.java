package dev.aurakai.auraframefx.ui.debug;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import dev.aurakai.auraframefx.ai.agents.CascadeAgent;
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
public final class CascadeDebugViewModel_Factory implements Factory<CascadeDebugViewModel> {
  private final Provider<CascadeAgent> cascadeAgentProvider;

  private CascadeDebugViewModel_Factory(Provider<CascadeAgent> cascadeAgentProvider) {
    this.cascadeAgentProvider = cascadeAgentProvider;
  }

  @Override
  public CascadeDebugViewModel get() {
    return newInstance(cascadeAgentProvider.get());
  }

  public static CascadeDebugViewModel_Factory create(Provider<CascadeAgent> cascadeAgentProvider) {
    return new CascadeDebugViewModel_Factory(cascadeAgentProvider);
  }

  public static CascadeDebugViewModel newInstance(CascadeAgent cascadeAgent) {
    return new CascadeDebugViewModel(cascadeAgent);
  }
}
