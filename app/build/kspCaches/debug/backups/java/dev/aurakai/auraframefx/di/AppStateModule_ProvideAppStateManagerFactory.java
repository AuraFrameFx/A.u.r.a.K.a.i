package dev.aurakai.auraframefx.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import dev.aurakai.auraframefx.state.AppStateManager;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("javax.inject.Named")
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
public final class AppStateModule_ProvideAppStateManagerFactory implements Factory<AppStateManager> {
  private final Provider<Object> _dataStoreProvider;

  private AppStateModule_ProvideAppStateManagerFactory(Provider<Object> _dataStoreProvider) {
    this._dataStoreProvider = _dataStoreProvider;
  }

  @Override
  public AppStateManager get() {
    return provideAppStateManager(_dataStoreProvider.get());
  }

  public static AppStateModule_ProvideAppStateManagerFactory create(
      Provider<Object> _dataStoreProvider) {
    return new AppStateModule_ProvideAppStateManagerFactory(_dataStoreProvider);
  }

  public static AppStateManager provideAppStateManager(Object _dataStore) {
    return Preconditions.checkNotNullFromProvides(AppStateModule.INSTANCE.provideAppStateManager(_dataStore));
  }
}
