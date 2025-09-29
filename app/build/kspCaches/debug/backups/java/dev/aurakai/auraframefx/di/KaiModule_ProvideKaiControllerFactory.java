package dev.aurakai.auraframefx.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import dev.aurakai.auraframefx.ui.KaiController;
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
public final class KaiModule_ProvideKaiControllerFactory implements Factory<KaiController> {
  @Override
  public KaiController get() {
    return provideKaiController();
  }

  public static KaiModule_ProvideKaiControllerFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static KaiController provideKaiController() {
    return KaiModule.INSTANCE.provideKaiController();
  }

  private static final class InstanceHolder {
    static final KaiModule_ProvideKaiControllerFactory INSTANCE = new KaiModule_ProvideKaiControllerFactory();
  }
}
