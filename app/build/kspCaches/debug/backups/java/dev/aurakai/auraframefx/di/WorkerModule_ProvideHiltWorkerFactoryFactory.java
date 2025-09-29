package dev.aurakai.auraframefx.di;

import androidx.hilt.work.HiltWorkerFactory;
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
public final class WorkerModule_ProvideHiltWorkerFactoryFactory implements Factory<HiltWorkerFactory> {
  @Override
  public HiltWorkerFactory get() {
    return provideHiltWorkerFactory();
  }

  public static WorkerModule_ProvideHiltWorkerFactoryFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static HiltWorkerFactory provideHiltWorkerFactory() {
    return WorkerModule.INSTANCE.provideHiltWorkerFactory();
  }

  private static final class InstanceHolder {
    static final WorkerModule_ProvideHiltWorkerFactoryFactory INSTANCE = new WorkerModule_ProvideHiltWorkerFactoryFactory();
  }
}
