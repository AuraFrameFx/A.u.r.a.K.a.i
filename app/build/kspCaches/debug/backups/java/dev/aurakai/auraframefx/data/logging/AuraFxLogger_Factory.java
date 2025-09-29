package dev.aurakai.auraframefx.data.logging;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
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
public final class AuraFxLogger_Factory implements Factory<AuraFxLogger> {
  private final Provider<Context> contextProvider;

  private final Provider<KaiAIService> kaiServiceProvider;

  private AuraFxLogger_Factory(Provider<Context> contextProvider,
      Provider<KaiAIService> kaiServiceProvider) {
    this.contextProvider = contextProvider;
    this.kaiServiceProvider = kaiServiceProvider;
  }

  @Override
  public AuraFxLogger get() {
    return newInstance(contextProvider.get(), kaiServiceProvider.get());
  }

  public static AuraFxLogger_Factory create(Provider<Context> contextProvider,
      Provider<KaiAIService> kaiServiceProvider) {
    return new AuraFxLogger_Factory(contextProvider, kaiServiceProvider);
  }

  public static AuraFxLogger newInstance(Context context, KaiAIService kaiService) {
    return new AuraFxLogger(context, kaiService);
  }
}
