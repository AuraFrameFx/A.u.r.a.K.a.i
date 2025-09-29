package dev.aurakai.auraframefx.network;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import dev.aurakai.auraframefx.api.client.apis.AIContentApi;
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
public final class AuraFxContentApiClient_Factory implements Factory<AuraFxContentApiClient> {
  private final Provider<AIContentApi> aiContentApiProvider;

  private AuraFxContentApiClient_Factory(Provider<AIContentApi> aiContentApiProvider) {
    this.aiContentApiProvider = aiContentApiProvider;
  }

  @Override
  public AuraFxContentApiClient get() {
    return newInstance(aiContentApiProvider.get());
  }

  public static AuraFxContentApiClient_Factory create(Provider<AIContentApi> aiContentApiProvider) {
    return new AuraFxContentApiClient_Factory(aiContentApiProvider);
  }

  public static AuraFxContentApiClient newInstance(AIContentApi aiContentApi) {
    return new AuraFxContentApiClient(aiContentApi);
  }
}
