package dev.aurakai.auraframefx.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import dev.aurakai.auraframefx.ai.services.GenesisBridgeService;
import dev.aurakai.auraframefx.data.logging.AuraFxLogger;
import dev.aurakai.auraframefx.security.SecurityContext;
import dev.aurakai.auraframefx.security.SecurityMonitor;
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
public final class TrinityModule_ProvideSecurityMonitorFactory implements Factory<SecurityMonitor> {
  private final Provider<SecurityContext> securityContextProvider;

  private final Provider<GenesisBridgeService> genesisBridgeServiceProvider;

  private final Provider<AuraFxLogger> loggerProvider;

  private TrinityModule_ProvideSecurityMonitorFactory(
      Provider<SecurityContext> securityContextProvider,
      Provider<GenesisBridgeService> genesisBridgeServiceProvider,
      Provider<AuraFxLogger> loggerProvider) {
    this.securityContextProvider = securityContextProvider;
    this.genesisBridgeServiceProvider = genesisBridgeServiceProvider;
    this.loggerProvider = loggerProvider;
  }

  @Override
  public SecurityMonitor get() {
    return provideSecurityMonitor(securityContextProvider.get(), genesisBridgeServiceProvider.get(), loggerProvider.get());
  }

  public static TrinityModule_ProvideSecurityMonitorFactory create(
      Provider<SecurityContext> securityContextProvider,
      Provider<GenesisBridgeService> genesisBridgeServiceProvider,
      Provider<AuraFxLogger> loggerProvider) {
    return new TrinityModule_ProvideSecurityMonitorFactory(securityContextProvider, genesisBridgeServiceProvider, loggerProvider);
  }

  public static SecurityMonitor provideSecurityMonitor(SecurityContext securityContext,
      GenesisBridgeService genesisBridgeService, AuraFxLogger logger) {
    return Preconditions.checkNotNullFromProvides(TrinityModule.INSTANCE.provideSecurityMonitor(securityContext, genesisBridgeService, logger));
  }
}
