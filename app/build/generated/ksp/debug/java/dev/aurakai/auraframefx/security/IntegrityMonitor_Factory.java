package dev.aurakai.auraframefx.security;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
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
public final class IntegrityMonitor_Factory implements Factory<IntegrityMonitor> {
  private final Provider<Context> contextProvider;

  private IntegrityMonitor_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public IntegrityMonitor get() {
    return newInstance(contextProvider.get());
  }

  public static IntegrityMonitor_Factory create(Provider<Context> contextProvider) {
    return new IntegrityMonitor_Factory(contextProvider);
  }

  public static IntegrityMonitor newInstance(Context context) {
    return new IntegrityMonitor(context);
  }
}
