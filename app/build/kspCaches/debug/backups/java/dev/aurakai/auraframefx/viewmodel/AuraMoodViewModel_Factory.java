package dev.aurakai.auraframefx.viewmodel;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
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
public final class AuraMoodViewModel_Factory implements Factory<AuraMoodViewModel> {
  @Override
  public AuraMoodViewModel get() {
    return newInstance();
  }

  public static AuraMoodViewModel_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static AuraMoodViewModel newInstance() {
    return new AuraMoodViewModel();
  }

  private static final class InstanceHolder {
    static final AuraMoodViewModel_Factory INSTANCE = new AuraMoodViewModel_Factory();
  }
}
