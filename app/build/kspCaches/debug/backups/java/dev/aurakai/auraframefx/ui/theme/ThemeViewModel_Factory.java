package dev.aurakai.auraframefx.ui.theme;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
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
public final class ThemeViewModel_Factory implements Factory<ThemeViewModel> {
  private final Provider<ThemeService> themeServiceProvider;

  private ThemeViewModel_Factory(Provider<ThemeService> themeServiceProvider) {
    this.themeServiceProvider = themeServiceProvider;
  }

  @Override
  public ThemeViewModel get() {
    return newInstance(themeServiceProvider.get());
  }

  public static ThemeViewModel_Factory create(Provider<ThemeService> themeServiceProvider) {
    return new ThemeViewModel_Factory(themeServiceProvider);
  }

  public static ThemeViewModel newInstance(ThemeService themeService) {
    return new ThemeViewModel(themeService);
  }
}
