package dev.aurakai.auraframefx.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import dev.aurakai.auraframefx.ui.theme.ThemeService;
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
public final class ThemeModule_ProvideThemeServiceFactory implements Factory<ThemeService> {
  @Override
  public ThemeService get() {
    return provideThemeService();
  }

  public static ThemeModule_ProvideThemeServiceFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static ThemeService provideThemeService() {
    return Preconditions.checkNotNullFromProvides(ThemeModule.INSTANCE.provideThemeService());
  }

  private static final class InstanceHolder {
    static final ThemeModule_ProvideThemeServiceFactory INSTANCE = new ThemeModule_ProvideThemeServiceFactory();
  }
}
