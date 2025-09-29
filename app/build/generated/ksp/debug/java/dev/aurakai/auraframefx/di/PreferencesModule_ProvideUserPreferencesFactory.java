package dev.aurakai.auraframefx.di;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import dev.aurakai.auraframefx.data.UserPreferences;
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
public final class PreferencesModule_ProvideUserPreferencesFactory implements Factory<UserPreferences> {
  private final Provider<Context> _contextProvider;

  private PreferencesModule_ProvideUserPreferencesFactory(Provider<Context> _contextProvider) {
    this._contextProvider = _contextProvider;
  }

  @Override
  public UserPreferences get() {
    return provideUserPreferences(_contextProvider.get());
  }

  public static PreferencesModule_ProvideUserPreferencesFactory create(
      Provider<Context> _contextProvider) {
    return new PreferencesModule_ProvideUserPreferencesFactory(_contextProvider);
  }

  public static UserPreferences provideUserPreferences(Context _context) {
    return Preconditions.checkNotNullFromProvides(PreferencesModule.INSTANCE.provideUserPreferences(_context));
  }
}
