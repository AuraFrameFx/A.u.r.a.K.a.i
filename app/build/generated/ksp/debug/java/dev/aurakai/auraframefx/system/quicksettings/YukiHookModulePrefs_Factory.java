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
public final class YukiHookModulePrefs_Factory implements Factory<YukiHookModulePrefs> {
  @Override
  public YukiHookModulePrefs get() {
    return newInstance();
  }

  public static YukiHookModulePrefs_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static YukiHookModulePrefs newInstance() {
    return new YukiHookModulePrefs();
  }

  private static final class InstanceHolder {
    static final YukiHookModulePrefs_Factory INSTANCE = new YukiHookModulePrefs_Factory();
  }
}
