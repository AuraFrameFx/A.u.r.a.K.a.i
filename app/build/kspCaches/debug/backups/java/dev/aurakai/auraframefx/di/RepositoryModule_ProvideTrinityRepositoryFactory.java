package dev.aurakai.auraframefx.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import dev.aurakai.auraframefx.network.AuraApiService;
import dev.aurakai.auraframefx.repository.TrinityRepository;
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
public final class RepositoryModule_ProvideTrinityRepositoryFactory implements Factory<TrinityRepository> {
  private final Provider<AuraApiService> apiServiceProvider;

  private RepositoryModule_ProvideTrinityRepositoryFactory(
      Provider<AuraApiService> apiServiceProvider) {
    this.apiServiceProvider = apiServiceProvider;
  }

  @Override
  public TrinityRepository get() {
    return provideTrinityRepository(apiServiceProvider.get());
  }

  public static RepositoryModule_ProvideTrinityRepositoryFactory create(
      Provider<AuraApiService> apiServiceProvider) {
    return new RepositoryModule_ProvideTrinityRepositoryFactory(apiServiceProvider);
  }

  public static TrinityRepository provideTrinityRepository(AuraApiService apiService) {
    return Preconditions.checkNotNullFromProvides(RepositoryModule.INSTANCE.provideTrinityRepository(apiService));
  }
}
