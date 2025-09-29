package dev.aurakai.auraframefx.system.lockscreen;

import android.content.Context;
import android.content.SharedPreferences;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import dev.aurakai.auraframefx.ui.theme.ThemeManager;
import dev.aurakai.auraframefx.utils.AuraFxLogger;
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
public final class LockScreenCustomizer_Factory implements Factory<LockScreenCustomizer> {
  private final Provider<Context> contextProvider;

  private final Provider<SharedPreferences> prefsProvider;

  private final Provider<ThemeManager> themeManagerProvider;

  private final Provider<AuraFxLogger> loggerProvider;

  private LockScreenCustomizer_Factory(Provider<Context> contextProvider,
      Provider<SharedPreferences> prefsProvider, Provider<ThemeManager> themeManagerProvider,
      Provider<AuraFxLogger> loggerProvider) {
    this.contextProvider = contextProvider;
    this.prefsProvider = prefsProvider;
    this.themeManagerProvider = themeManagerProvider;
    this.loggerProvider = loggerProvider;
  }

  @Override
  public LockScreenCustomizer get() {
    return newInstance(contextProvider.get(), prefsProvider.get(), themeManagerProvider.get(), loggerProvider.get());
  }

  public static LockScreenCustomizer_Factory create(Provider<Context> contextProvider,
      Provider<SharedPreferences> prefsProvider, Provider<ThemeManager> themeManagerProvider,
      Provider<AuraFxLogger> loggerProvider) {
    return new LockScreenCustomizer_Factory(contextProvider, prefsProvider, themeManagerProvider, loggerProvider);
  }

  public static LockScreenCustomizer newInstance(Context context, SharedPreferences prefs,
      ThemeManager themeManager, AuraFxLogger logger) {
    return new LockScreenCustomizer(context, prefs, themeManager, logger);
  }
}
