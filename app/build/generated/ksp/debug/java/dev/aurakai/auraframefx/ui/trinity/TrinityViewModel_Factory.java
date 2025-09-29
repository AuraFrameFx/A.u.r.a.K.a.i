package dev.aurakai.auraframefx.ui.trinity;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import dev.aurakai.auraframefx.repository.TrinityRepository;
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
public final class TrinityViewModel_Factory implements Factory<TrinityViewModel> {
  private final Provider<TrinityRepository> repositoryProvider;

  private TrinityViewModel_Factory(Provider<TrinityRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public TrinityViewModel get() {
    return newInstance(repositoryProvider.get());
  }

  public static TrinityViewModel_Factory create(Provider<TrinityRepository> repositoryProvider) {
    return new TrinityViewModel_Factory(repositoryProvider);
  }

  public static TrinityViewModel newInstance(TrinityRepository repository) {
    return new TrinityViewModel(repository);
  }
}
