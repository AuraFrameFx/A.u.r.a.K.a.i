package dev.aurakai.auraframefx.security;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import dev.aurakai.auraframefx.ai.services.GenesisBridgeService;
import dev.aurakai.auraframefx.data.logging.AuraFxLogger;
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
public final class SecurityMonitor_Factory implements Factory<SecurityMonitor> {
  private final Provider<SecurityContext> securityContextProvider;

  private final Provider<GenesisBridgeService> genesisBridgeServiceProvider;

  private final Provider<AuraFxLogger> loggerProvider;

  private SecurityMonitor_Factory(Provider<SecurityContext> securityContextProvider,
      Provider<GenesisBridgeService> genesisBridgeServiceProvider,
      Provider<AuraFxLogger> loggerProvider) {
    this.securityContextProvider = securityContextProvider;
    this.genesisBridgeServiceProvider = genesisBridgeServiceProvider;
    this.loggerProvider = loggerProvider;
  }

  @Override
  public SecurityMonitor get() {
    return newInstance(securityContextProvider.get(), genesisBridgeServiceProvider.get(), loggerProvider.get());
  }

  public static SecurityMonitor_Factory create(Provider<SecurityContext> securityContextProvider,
      Provider<GenesisBridgeService> genesisBridgeServiceProvider,
      Provider<AuraFxLogger> loggerProvider) {
    return new SecurityMonitor_Factory(securityContextProvider, genesisBridgeServiceProvider, loggerProvider);
  }

  public static SecurityMonitor newInstance(SecurityContext securityContext,
      GenesisBridgeService genesisBridgeService, AuraFxLogger logger) {
    return new SecurityMonitor(securityContext, genesisBridgeService, logger);
  }
}
