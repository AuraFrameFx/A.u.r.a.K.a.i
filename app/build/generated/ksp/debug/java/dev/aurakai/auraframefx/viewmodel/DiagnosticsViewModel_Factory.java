package dev.aurakai.auraframefx.viewmodel;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import dev.aurakai.auraframefx.data.logging.AuraFxLogger;
import dev.aurakai.auraframefx.data.network.CloudStatusMonitor;
import dev.aurakai.auraframefx.data.offline.OfflineDataManager;
import javax.annotation.processing.Generated;

@ScopeMetadata
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
public final class DiagnosticsViewModel_Factory implements Factory<DiagnosticsViewModel> {
  private final Provider<AuraFxLogger> auraFxLoggerProvider;

  private final Provider<CloudStatusMonitor> cloudStatusMonitorProvider;

  private final Provider<OfflineDataManager> offlineDataManagerProvider;

  private DiagnosticsViewModel_Factory(Provider<AuraFxLogger> auraFxLoggerProvider,
      Provider<CloudStatusMonitor> cloudStatusMonitorProvider,
      Provider<OfflineDataManager> offlineDataManagerProvider) {
    this.auraFxLoggerProvider = auraFxLoggerProvider;
    this.cloudStatusMonitorProvider = cloudStatusMonitorProvider;
    this.offlineDataManagerProvider = offlineDataManagerProvider;
  }

  @Override
  public DiagnosticsViewModel get() {
    return newInstance(auraFxLoggerProvider.get(), cloudStatusMonitorProvider.get(), offlineDataManagerProvider.get());
  }

  public static DiagnosticsViewModel_Factory create(Provider<AuraFxLogger> auraFxLoggerProvider,
      Provider<CloudStatusMonitor> cloudStatusMonitorProvider,
      Provider<OfflineDataManager> offlineDataManagerProvider) {
    return new DiagnosticsViewModel_Factory(auraFxLoggerProvider, cloudStatusMonitorProvider, offlineDataManagerProvider);
  }

  public static DiagnosticsViewModel newInstance(AuraFxLogger auraFxLogger,
      CloudStatusMonitor cloudStatusMonitor, OfflineDataManager offlineDataManager) {
    return new DiagnosticsViewModel(auraFxLogger, cloudStatusMonitor, offlineDataManager);
  }
}
