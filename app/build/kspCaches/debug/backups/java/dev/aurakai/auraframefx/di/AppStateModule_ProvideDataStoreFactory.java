package dev.aurakai.auraframefx.di;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata({
    "javax.inject.Named",
    "dagger.hilt.android.qualifiers.ApplicationContext"
})
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
public final class AppStateModule_ProvideDataStoreFactory implements Factory<Object> {
  private final Provider<Context> _contextProvider;

  private AppStateModule_ProvideDataStoreFactory(Provider<Context> _contextProvider) {
    this._contextProvider = _contextProvider;
  }

  @Override
  public Object get() {
    return provideDataStore(_contextProvider.get());
  }

  public static AppStateModule_ProvideDataStoreFactory create(Provider<Context> _contextProvider) {
    return new AppStateModule_ProvideDataStoreFactory(_contextProvider);
  }

  public static Object provideDataStore(Context _context) {
    return Preconditions.checkNotNullFromProvides(AppStateModule.INSTANCE.provideDataStore(_context));
  }
}
