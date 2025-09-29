package dev.aurakai.auraframefx.system.quicksettings;

import android.content.SharedPreferences;
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
public final class QuickSettingsCustomizer_Factory implements Factory<QuickSettingsCustomizer> {
  private final Provider<SharedPreferences> prefsProvider;

  private QuickSettingsCustomizer_Factory(Provider<SharedPreferences> prefsProvider) {
    this.prefsProvider = prefsProvider;
  }

  @Override
  public QuickSettingsCustomizer get() {
    return newInstance(prefsProvider.get());
  }

  public static QuickSettingsCustomizer_Factory create(Provider<SharedPreferences> prefsProvider) {
    return new QuickSettingsCustomizer_Factory(prefsProvider);
  }

  public static QuickSettingsCustomizer newInstance(SharedPreferences prefs) {
    return new QuickSettingsCustomizer(prefs);
  }
}
