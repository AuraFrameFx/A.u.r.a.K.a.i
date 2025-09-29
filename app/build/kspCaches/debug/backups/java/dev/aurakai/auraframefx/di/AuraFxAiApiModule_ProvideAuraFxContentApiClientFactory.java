package dev.aurakai.auraframefx.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import dev.aurakai.auraframefx.api.client.apis.AIContentApi;
import dev.aurakai.auraframefx.network.AuraFxContentApiClient;
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
public final class AuraFxAiApiModule_ProvideAuraFxContentApiClientFactory implements Factory<AuraFxContentApiClient> {
  private final Provider<AIContentApi> aiContentApiProvider;

  private AuraFxAiApiModule_ProvideAuraFxContentApiClientFactory(
      Provider<AIContentApi> aiContentApiProvider) {
    this.aiContentApiProvider = aiContentApiProvider;
  }

  @Override
  public AuraFxContentApiClient get() {
    return provideAuraFxContentApiClient(aiContentApiProvider.get());
  }

  public static AuraFxAiApiModule_ProvideAuraFxContentApiClientFactory create(
      Provider<AIContentApi> aiContentApiProvider) {
    return new AuraFxAiApiModule_ProvideAuraFxContentApiClientFactory(aiContentApiProvider);
  }

  public static AuraFxContentApiClient provideAuraFxContentApiClient(AIContentApi aiContentApi) {
    return Preconditions.checkNotNullFromProvides(AuraFxAiApiModule.INSTANCE.provideAuraFxContentApiClient(aiContentApi));
  }
}
