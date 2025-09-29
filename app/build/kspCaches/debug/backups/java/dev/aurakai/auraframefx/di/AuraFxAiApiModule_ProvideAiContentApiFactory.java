package dev.aurakai.auraframefx.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import dev.aurakai.auraframefx.api.client.apis.AIContentApi;
import javax.annotation.processing.Generated;
import okhttp3.OkHttpClient;

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
public final class AuraFxAiApiModule_ProvideAiContentApiFactory implements Factory<AIContentApi> {
  private final Provider<OkHttpClient> okHttpClientProvider;

  private AuraFxAiApiModule_ProvideAiContentApiFactory(
      Provider<OkHttpClient> okHttpClientProvider) {
    this.okHttpClientProvider = okHttpClientProvider;
  }

  @Override
  public AIContentApi get() {
    return provideAiContentApi(okHttpClientProvider.get());
  }

  public static AuraFxAiApiModule_ProvideAiContentApiFactory create(
      Provider<OkHttpClient> okHttpClientProvider) {
    return new AuraFxAiApiModule_ProvideAiContentApiFactory(okHttpClientProvider);
  }

  public static AIContentApi provideAiContentApi(OkHttpClient okHttpClient) {
    return Preconditions.checkNotNullFromProvides(AuraFxAiApiModule.INSTANCE.provideAiContentApi(okHttpClient));
  }
}
