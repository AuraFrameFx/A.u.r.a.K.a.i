package dev.aurakai.auraframefx.utils;

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
public final class AppCoroutineDispatchers_Factory implements Factory<AppCoroutineDispatchers> {
  @Override
  public AppCoroutineDispatchers get() {
    return newInstance();
  }

  public static AppCoroutineDispatchers_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static AppCoroutineDispatchers newInstance() {
    return new AppCoroutineDispatchers();
  }

  private static final class InstanceHolder {
    static final AppCoroutineDispatchers_Factory INSTANCE = new AppCoroutineDispatchers_Factory();
  }
}
