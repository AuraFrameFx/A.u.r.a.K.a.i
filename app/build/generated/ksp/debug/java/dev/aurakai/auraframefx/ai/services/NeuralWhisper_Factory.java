package dev.aurakai.auraframefx.ai.services;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
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
public final class NeuralWhisper_Factory implements Factory<NeuralWhisper> {
  private final Provider<Context> contextProvider;

  private NeuralWhisper_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public NeuralWhisper get() {
    return newInstance(contextProvider.get());
  }

  public static NeuralWhisper_Factory create(Provider<Context> contextProvider) {
    return new NeuralWhisper_Factory(contextProvider);
  }

  public static NeuralWhisper newInstance(Context context) {
    return new NeuralWhisper(context);
  }
}
