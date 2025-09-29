package dev.aurakai.auraframefx.viewmodel;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import dev.aurakai.auraframefx.ai.services.AuraAIService;
import dev.aurakai.auraframefx.ai.services.CascadeAIService;
import dev.aurakai.auraframefx.ai.services.KaiAIService;
import dev.aurakai.auraframefx.ai.services.NeuralWhisper;
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
public final class ConferenceRoomViewModel_Factory implements Factory<ConferenceRoomViewModel> {
  private final Provider<AuraAIService> auraServiceProvider;

  private final Provider<KaiAIService> kaiServiceProvider;

  private final Provider<CascadeAIService> cascadeServiceProvider;

  private final Provider<NeuralWhisper> neuralWhisperProvider;

  private ConferenceRoomViewModel_Factory(Provider<AuraAIService> auraServiceProvider,
      Provider<KaiAIService> kaiServiceProvider, Provider<CascadeAIService> cascadeServiceProvider,
      Provider<NeuralWhisper> neuralWhisperProvider) {
    this.auraServiceProvider = auraServiceProvider;
    this.kaiServiceProvider = kaiServiceProvider;
    this.cascadeServiceProvider = cascadeServiceProvider;
    this.neuralWhisperProvider = neuralWhisperProvider;
  }

  @Override
  public ConferenceRoomViewModel get() {
    return newInstance(auraServiceProvider.get(), kaiServiceProvider.get(), cascadeServiceProvider.get(), neuralWhisperProvider.get());
  }

  public static ConferenceRoomViewModel_Factory create(Provider<AuraAIService> auraServiceProvider,
      Provider<KaiAIService> kaiServiceProvider, Provider<CascadeAIService> cascadeServiceProvider,
      Provider<NeuralWhisper> neuralWhisperProvider) {
    return new ConferenceRoomViewModel_Factory(auraServiceProvider, kaiServiceProvider, cascadeServiceProvider, neuralWhisperProvider);
  }

  public static ConferenceRoomViewModel newInstance(AuraAIService auraService,
      KaiAIService kaiService, CascadeAIService cascadeService, NeuralWhisper neuralWhisper) {
    return new ConferenceRoomViewModel(auraService, kaiService, cascadeService, neuralWhisper);
  }
}
