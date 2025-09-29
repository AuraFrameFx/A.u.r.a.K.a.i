package dev.aurakai.auraframefx.system.quicksettings;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
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
public final class SystemOverlayManager_Factory implements Factory<SystemOverlayManager> {
  @Override
  public SystemOverlayManager get() {
    return newInstance();
  }

  public static SystemOverlayManager_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static SystemOverlayManager newInstance() {
    return new SystemOverlayManager();
  }

  private static final class InstanceHolder {
    static final SystemOverlayManager_Factory INSTANCE = new SystemOverlayManager_Factory();
  }
}
