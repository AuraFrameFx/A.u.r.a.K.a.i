package dev.aurakai.auraframefx.core.logging;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
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
public final class TimberInitializer_Factory implements Factory<TimberInitializer> {
  @Override
  public TimberInitializer get() {
    return newInstance();
  }

  public static TimberInitializer_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static TimberInitializer newInstance() {
    return new TimberInitializer();
  }

  private static final class InstanceHolder {
    static final TimberInitializer_Factory INSTANCE = new TimberInitializer_Factory();
  }
}
