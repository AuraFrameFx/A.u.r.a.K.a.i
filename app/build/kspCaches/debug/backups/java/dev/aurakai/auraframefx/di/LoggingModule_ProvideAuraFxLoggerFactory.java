package dev.aurakai.auraframefx.di;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import dev.aurakai.auraframefx.ai.services.KaiAIService;
import dev.aurakai.auraframefx.data.logging.AuraFxLogger;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class LoggingModule_ProvideAuraFxLoggerFactory implements Factory<AuraFxLogger> {
  private final Provider<Context> contextProvider;

  private final Provider<KaiAIService> kaiServiceProvider;

  private LoggingModule_ProvideAuraFxLoggerFactory(Provider<Context> contextProvider,
      Provider<KaiAIService> kaiServiceProvider) {
    this.contextProvider = contextProvider;
    this.kaiServiceProvider = kaiServiceProvider;
  }

  @Override
  public AuraFxLogger get() {
    return provideAuraFxLogger(contextProvider.get(), kaiServiceProvider.get());
  }

  public static LoggingModule_ProvideAuraFxLoggerFactory create(Provider<Context> contextProvider,
      Provider<KaiAIService> kaiServiceProvider) {
    return new LoggingModule_ProvideAuraFxLoggerFactory(contextProvider, kaiServiceProvider);
  }

  public static AuraFxLogger provideAuraFxLogger(Context context, KaiAIService kaiService) {
    return Preconditions.checkNotNullFromProvides(LoggingModule.INSTANCE.provideAuraFxLogger(context, kaiService));
  }
}
