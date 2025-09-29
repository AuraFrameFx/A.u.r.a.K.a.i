package dev.aurakai.auraframefx.ai.agents;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import dev.aurakai.auraframefx.ai.memory.MemoryManager;
import dev.aurakai.auraframefx.security.IntegrityMonitor;
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
public final class AuraShieldAgent_Factory implements Factory<AuraShieldAgent> {
  private final Provider<Context> contextProvider;

  private final Provider<SecurityMonitor> securityMonitorProvider;

  private final Provider<IntegrityMonitor> integrityMonitorProvider;

  private final Provider<MemoryManager> memoryManagerProvider;

  private AuraShieldAgent_Factory(Provider<Context> contextProvider,
      Provider<SecurityMonitor> securityMonitorProvider,
      Provider<IntegrityMonitor> integrityMonitorProvider,
      Provider<MemoryManager> memoryManagerProvider) {
    this.contextProvider = contextProvider;
    this.securityMonitorProvider = securityMonitorProvider;
    this.integrityMonitorProvider = integrityMonitorProvider;
    this.memoryManagerProvider = memoryManagerProvider;
  }

  @Override
  public AuraShieldAgent get() {
    return newInstance(contextProvider.get(), securityMonitorProvider.get(), integrityMonitorProvider.get(), memoryManagerProvider.get());
  }

  public static AuraShieldAgent_Factory create(Provider<Context> contextProvider,
      Provider<SecurityMonitor> securityMonitorProvider,
      Provider<IntegrityMonitor> integrityMonitorProvider,
      Provider<MemoryManager> memoryManagerProvider) {
    return new AuraShieldAgent_Factory(contextProvider, securityMonitorProvider, integrityMonitorProvider, memoryManagerProvider);
  }

  public static AuraShieldAgent newInstance(Context context, SecurityMonitor securityMonitor,
      IntegrityMonitor integrityMonitor, MemoryManager memoryManager) {
    return new AuraShieldAgent(context, securityMonitor, integrityMonitor, memoryManager);
  }
}
