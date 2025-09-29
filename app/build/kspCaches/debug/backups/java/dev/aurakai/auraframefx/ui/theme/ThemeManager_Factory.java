package dev.aurakai.auraframefx.ui.theme;

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
public final class ThemeManager_Factory implements Factory<ThemeManager> {
  private final Provider<Context> contextProvider;

  private ThemeManager_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public ThemeManager get() {
    return newInstance(contextProvider.get());
  }

  public static ThemeManager_Factory create(Provider<Context> contextProvider) {
    return new ThemeManager_Factory(contextProvider);
  }

  public static ThemeManager newInstance(Context context) {
    return new ThemeManager(context);
  }
}
