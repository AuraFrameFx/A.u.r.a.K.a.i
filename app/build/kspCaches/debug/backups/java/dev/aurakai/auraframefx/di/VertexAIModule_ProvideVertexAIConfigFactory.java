package dev.aurakai.auraframefx.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import dev.aurakai.auraframefx.ai.VertexAIConfig;
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
public final class VertexAIModule_ProvideVertexAIConfigFactory implements Factory<VertexAIConfig> {
  @Override
  public VertexAIConfig get() {
    return provideVertexAIConfig();
  }

  public static VertexAIModule_ProvideVertexAIConfigFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static VertexAIConfig provideVertexAIConfig() {
    return Preconditions.checkNotNullFromProvides(VertexAIModule.INSTANCE.provideVertexAIConfig());
  }

  private static final class InstanceHolder {
    static final VertexAIModule_ProvideVertexAIConfigFactory INSTANCE = new VertexAIModule_ProvideVertexAIConfigFactory();
  }
}
