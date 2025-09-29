package dev.aurakai.auraframefx.repository;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import dev.aurakai.auraframefx.network.AuraApiService;
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
public final class TrinityRepository_Factory implements Factory<TrinityRepository> {
  private final Provider<AuraApiService> apiServiceProvider;

  private TrinityRepository_Factory(Provider<AuraApiService> apiServiceProvider) {
    this.apiServiceProvider = apiServiceProvider;
  }

  @Override
  public TrinityRepository get() {
    return newInstance(apiServiceProvider.get());
  }

  public static TrinityRepository_Factory create(Provider<AuraApiService> apiServiceProvider) {
    return new TrinityRepository_Factory(apiServiceProvider);
  }

  public static TrinityRepository newInstance(AuraApiService apiService) {
    return new TrinityRepository(apiService);
  }
}
