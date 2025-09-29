package dev.aurakai.auraframefx.di;

import android.content.Context;
import androidx.work.Configuration;
import androidx.work.WorkManager;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class WorkManagerModule_ProvideWorkManagerFactory implements Factory<WorkManager> {
  private final Provider<Context> _contextProvider;

  private final Provider<Configuration> _configurationProvider;

  private WorkManagerModule_ProvideWorkManagerFactory(Provider<Context> _contextProvider,
      Provider<Configuration> _configurationProvider) {
    this._contextProvider = _contextProvider;
    this._configurationProvider = _configurationProvider;
  }

  @Override
  public WorkManager get() {
    return provideWorkManager(_contextProvider.get(), _configurationProvider.get());
  }

  public static WorkManagerModule_ProvideWorkManagerFactory create(
      Provider<Context> _contextProvider, Provider<Configuration> _configurationProvider) {
    return new WorkManagerModule_ProvideWorkManagerFactory(_contextProvider, _configurationProvider);
  }

  public static WorkManager provideWorkManager(Context _context, Configuration _configuration) {
    return Preconditions.checkNotNullFromProvides(WorkManagerModule.INSTANCE.provideWorkManager(_context, _configuration));
  }
}
