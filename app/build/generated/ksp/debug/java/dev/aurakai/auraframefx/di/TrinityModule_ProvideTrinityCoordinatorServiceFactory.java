package dev.aurakai.auraframefx.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import dev.aurakai.auraframefx.ai.services.AuraAIService;
import dev.aurakai.auraframefx.ai.services.GenesisBridgeService;
import dev.aurakai.auraframefx.ai.services.KaiAIService;
import dev.aurakai.auraframefx.ai.services.TrinityCoordinatorService;
import dev.aurakai.auraframefx.data.logging.AuraFxLogger;
import dev.aurakai.auraframefx.security.SecurityContext;
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
public final class TrinityModule_ProvideTrinityCoordinatorServiceFactory implements Factory<TrinityCoordinatorService> {
  private final Provider<AuraAIService> auraAIServiceProvider;

  private final Provider<KaiAIService> kaiAIServiceProvider;

  private final Provider<GenesisBridgeService> genesisBridgeServiceProvider;

  private final Provider<SecurityContext> securityContextProvider;

  private final Provider<AuraFxLogger> loggerProvider;

  private TrinityModule_ProvideTrinityCoordinatorServiceFactory(
      Provider<AuraAIService> auraAIServiceProvider, Provider<KaiAIService> kaiAIServiceProvider,
      Provider<GenesisBridgeService> genesisBridgeServiceProvider,
      Provider<SecurityContext> securityContextProvider, Provider<AuraFxLogger> loggerProvider) {
    this.auraAIServiceProvider = auraAIServiceProvider;
    this.kaiAIServiceProvider = kaiAIServiceProvider;
    this.genesisBridgeServiceProvider = genesisBridgeServiceProvider;
    this.securityContextProvider = securityContextProvider;
    this.loggerProvider = loggerProvider;
  }

  @Override
  public TrinityCoordinatorService get() {
    return provideTrinityCoordinatorService(auraAIServiceProvider.get(), kaiAIServiceProvider.get(), genesisBridgeServiceProvider.get(), securityContextProvider.get(), loggerProvider.get());
  }

  public static TrinityModule_ProvideTrinityCoordinatorServiceFactory create(
      Provider<AuraAIService> auraAIServiceProvider, Provider<KaiAIService> kaiAIServiceProvider,
      Provider<GenesisBridgeService> genesisBridgeServiceProvider,
      Provider<SecurityContext> securityContextProvider, Provider<AuraFxLogger> loggerProvider) {
    return new TrinityModule_ProvideTrinityCoordinatorServiceFactory(auraAIServiceProvider, kaiAIServiceProvider, genesisBridgeServiceProvider, securityContextProvider, loggerProvider);
  }

  public static TrinityCoordinatorService provideTrinityCoordinatorService(
      AuraAIService auraAIService, KaiAIService kaiAIService,
      GenesisBridgeService genesisBridgeService, SecurityContext securityContext,
      AuraFxLogger logger) {
    return Preconditions.checkNotNullFromProvides(TrinityModule.INSTANCE.provideTrinityCoordinatorService(auraAIService, kaiAIService, genesisBridgeService, securityContext, logger));
  }
}
