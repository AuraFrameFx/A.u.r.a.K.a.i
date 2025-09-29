package dev.aurakai.auraframefx.di;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import dev.aurakai.auraframefx.ai.services.NeuralWhisper;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class NeuralWhisperModule_ProvideNeuralWhisperFactory implements Factory<NeuralWhisper> {
  private final Provider<Context> contextProvider;

  private NeuralWhisperModule_ProvideNeuralWhisperFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public NeuralWhisper get() {
    return provideNeuralWhisper(contextProvider.get());
  }

  public static NeuralWhisperModule_ProvideNeuralWhisperFactory create(
      Provider<Context> contextProvider) {
    return new NeuralWhisperModule_ProvideNeuralWhisperFactory(contextProvider);
  }

  public static NeuralWhisper provideNeuralWhisper(Context context) {
    return Preconditions.checkNotNullFromProvides(NeuralWhisperModule.INSTANCE.provideNeuralWhisper(context));
  }
}
