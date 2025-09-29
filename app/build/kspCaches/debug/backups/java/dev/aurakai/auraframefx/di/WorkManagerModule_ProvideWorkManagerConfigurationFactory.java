package dev.aurakai.auraframefx.di;

import androidx.hilt.work.HiltWorkerFactory;
import androidx.work.Configuration;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class WorkManagerModule_ProvideWorkManagerConfigurationFactory implements Factory<Configuration> {
  private final Provider<HiltWorkerFactory> workerFactoryProvider;

  private WorkManagerModule_ProvideWorkManagerConfigurationFactory(
      Provider<HiltWorkerFactory> workerFactoryProvider) {
    this.workerFactoryProvider = workerFactoryProvider;
  }

  @Override
  public Configuration get() {
    return provideWorkManagerConfiguration(workerFactoryProvider.get());
  }

  public static WorkManagerModule_ProvideWorkManagerConfigurationFactory create(
      Provider<HiltWorkerFactory> workerFactoryProvider) {
    return new WorkManagerModule_ProvideWorkManagerConfigurationFactory(workerFactoryProvider);
  }

  public static Configuration provideWorkManagerConfiguration(HiltWorkerFactory workerFactory) {
    return Preconditions.checkNotNullFromProvides(WorkManagerModule.INSTANCE.provideWorkManagerConfiguration(workerFactory));
  }
}
