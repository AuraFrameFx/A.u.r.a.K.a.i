package dev.aurakai.auraframefx.theme;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import dev.aurakai.auraframefx.ai.services.AuraAIService;
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
  private final Provider<AuraAIService> auraAIServiceProvider;

  private ThemeManager_Factory(Provider<AuraAIService> auraAIServiceProvider) {
    this.auraAIServiceProvider = auraAIServiceProvider;
  }

  @Override
  public ThemeManager get() {
    return newInstance(auraAIServiceProvider.get());
  }

  public static ThemeManager_Factory create(Provider<AuraAIService> auraAIServiceProvider) {
    return new ThemeManager_Factory(auraAIServiceProvider);
  }

  public static ThemeManager newInstance(AuraAIService auraAIService) {
    return new ThemeManager(auraAIService);
  }
}
