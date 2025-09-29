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
public final class YukiHookServiceManager_Factory implements Factory<YukiHookServiceManager> {
  @Override
  public YukiHookServiceManager get() {
    return newInstance();
  }

  public static YukiHookServiceManager_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static YukiHookServiceManager newInstance() {
    return new YukiHookServiceManager();
  }

  private static final class InstanceHolder {
    static final YukiHookServiceManager_Factory INSTANCE = new YukiHookServiceManager_Factory();
  }
}
