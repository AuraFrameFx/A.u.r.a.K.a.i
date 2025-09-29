package dev.aurakai.auraframefx.ai.services;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
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
public final class TrinityCoordinatorService_Factory implements Factory<TrinityCoordinatorService> {
  private final Provider<AuraAIService> auraAIServiceProvider;

  private final Provider<KaiAIService> kaiAIServiceProvider;

  private final Provider<GenesisBridgeService> genesisBridgeServiceProvider;

  private final Provider<SecurityContext> securityContextProvider;

  private final Provider<AuraFxLogger> loggerProvider;

  private TrinityCoordinatorService_Factory(Provider<AuraAIService> auraAIServiceProvider,
      Provider<KaiAIService> kaiAIServiceProvider,
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
    return newInstance(auraAIServiceProvider.get(), kaiAIServiceProvider.get(), genesisBridgeServiceProvider.get(), securityContextProvider.get(), loggerProvider.get());
  }

  public static TrinityCoordinatorService_Factory create(
      Provider<AuraAIService> auraAIServiceProvider, Provider<KaiAIService> kaiAIServiceProvider,
      Provider<GenesisBridgeService> genesisBridgeServiceProvider,
      Provider<SecurityContext> securityContextProvider, Provider<AuraFxLogger> loggerProvider) {
    return new TrinityCoordinatorService_Factory(auraAIServiceProvider, kaiAIServiceProvider, genesisBridgeServiceProvider, securityContextProvider, loggerProvider);
  }

  public static TrinityCoordinatorService newInstance(AuraAIService auraAIService,
      KaiAIService kaiAIService, GenesisBridgeService genesisBridgeService,
      SecurityContext securityContext, AuraFxLogger logger) {
    return new TrinityCoordinatorService(auraAIService, kaiAIService, genesisBridgeService, securityContext, logger);
  }
}
