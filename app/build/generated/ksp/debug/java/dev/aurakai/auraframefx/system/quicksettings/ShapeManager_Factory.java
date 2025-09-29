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
public final class ShapeManager_Factory implements Factory<ShapeManager> {
  @Override
  public ShapeManager get() {
    return newInstance();
  }

  public static ShapeManager_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static ShapeManager newInstance() {
    return new ShapeManager();
  }

  private static final class InstanceHolder {
    static final ShapeManager_Factory INSTANCE = new ShapeManager_Factory();
  }
}
