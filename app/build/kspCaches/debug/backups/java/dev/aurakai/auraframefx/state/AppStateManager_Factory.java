package dev.aurakai.auraframefx.state;

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
public final class AppStateManager_Factory implements Factory<AppStateManager> {
  @Override
  public AppStateManager get() {
    return newInstance();
  }

  public static AppStateManager_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static AppStateManager newInstance() {
    return new AppStateManager();
  }

  private static final class InstanceHolder {
    static final AppStateManager_Factory INSTANCE = new AppStateManager_Factory();
  }
}
